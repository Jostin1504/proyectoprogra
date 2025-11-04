
package Proyecto.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    ServerSocket ss;
    List<Worker> workers;
    Map<String, Usuario> usuariosActivos;

    public Server() {
        usuariosActivos = Collections.synchronizedMap(new HashMap<>());
        try {
            ss = new ServerSocket(Protocol.PORT);
            workers = Collections.synchronizedList(new ArrayList<Worker>());
            System.out.println("Servidor iniciado...");
        } catch (IOException ex) { System.out.println(ex);}
    }
    public void run() {
        Service service = new Service();
        boolean continuar = true;
        Socket s;
        Worker worker;
        String sid;  // Session Id
        while (continuar) {
            try {
                s = ss.accept();
                System.out.println("Conexion Establecida...");
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                int type= is.readInt();
                switch (type) {
                    case Protocol.SYNC:
                        sid=s.getRemoteSocketAddress().toString();
                        System.out.println("SYNCH: "+sid);
                        worker = new Worker(this, s, os, is, sid, Service.instance());
                        workers.add(worker);
                        System.out.println("Quedan: " + workers.size());
                        worker.start();
                        os.writeObject(sid); // send Session Id back
                        break;
                    case Protocol.ASYNC:
                        sid=(String)is.readObject();
                        System.out.println("=== ASYNC RECIBIDO ===");
                        System.out.println("SID: " + sid);
                        System.out.println("Buscando worker con ese SID...");
                        join(s, os, is, sid);
                        System.out.println("=== JOIN completado ===");
                        break;
                }
                os.flush();
            }
            catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }

    public void remove(Worker w) {
        workers.remove(w);
        System.out.println("Quedan: " +workers.size());
    }

    public void join(Socket as, ObjectOutputStream aos, ObjectInputStream ais, String sid){
        System.out.println(">>> join() - Buscando worker con SID: " + sid);
        System.out.println(">>> Total workers: " + workers.size());

        for(Worker w : workers){
            System.out.println("    Comparando con worker: " + w.sid);
            if(w.sid.equals(sid)){
                System.out.println("    ✅ Worker encontrado!");
                w.setAs(as, aos, ais);

                System.out.println("    Enviando " + usuariosActivos.size() + " usuarios activos");
                for(Usuario usuario : usuariosActivos.values()) {
                    String message = "USER_CONNECTED:" + usuario.getCedula() + ":" +
                            usuario.getNombre() + ":" + usuario.getRol();
                    System.out.println("    Enviando: " + message);
                    w.deliver_message(message);
                }
                return;
            }
        }
        System.out.println("    ❌ Worker NO encontrado!");
    }

    public void addActiveUser(String sid, Usuario usuario) {
        usuariosActivos.put(sid, usuario);
        System.out.println("   Usuario añadido: " + usuario.getNombre() + " (" + sid + ")");
        System.out.println("   Total usuarios activos: " + usuariosActivos.size());
        System.out.println("   Total workers: " + workers.size());
        notifyUserConnected(usuario);
    }

    public void deliver_message(Worker from, String message){
        for(Worker w:workers){
            if (w!=from) w.deliver_message(message);
        }
    }

    public void removeActiveUser(String sid) {
        Usuario usuario = usuariosActivos.remove(sid);
        if (usuario != null) {
            notifyUserDisconnected(usuario);
        }
    }

    public List<Usuario> getActiveUsers() {
        return new ArrayList<>(usuariosActivos.values());
    }

    private void notifyUserConnected(Usuario usuario) {
        String message = "USER_CONNECTED:" + usuario.getCedula() + ":" + usuario.getNombre() + ":" + usuario.getRol();
        System.out.println("📢 Notificando conexión: " + message);
        int notified = 0;
        for(Worker w : workers){
            if(w.as != null) {
                w.deliver_message(message);
                notified++;
            } else {
                System.out.println("   Worker " + w.sid + " no tiene socket async");
            }
        }
        System.out.println("   Notificados: " + notified + " workers");
    }

    private void notifyUserDisconnected(Usuario usuario) {
        String message = "USER_DISCONNECTED:" + usuario.getCedula() + ":" + usuario.getNombre();
        deliver_message(null, message);
        System.out.println("Notificando desconexión: " + message);
    }

}