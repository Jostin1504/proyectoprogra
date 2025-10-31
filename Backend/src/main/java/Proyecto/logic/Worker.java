package Proyecto.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Worker {
    Server srv;
    Socket s;
    ObjectOutputStream os;
    ObjectInputStream is;
    Service service;

    String sid; // Session Id
    Socket as; // Asynchronous Socket
    ObjectOutputStream aos;
    ObjectInputStream ais;

    public Worker(Server srv, Socket s, ObjectOutputStream os, ObjectInputStream is, String sid, Service service) {
        this.srv = srv;
        this.s = s;
        this.os = os;
        this.is = is;
        this.service = service;
        this.sid = sid;
    }
    public void setAs(Socket as, ObjectOutputStream aos, ObjectInputStream ais) {
        this.as = as;
        this.aos = aos;
        this.ais = ais;
    }

    boolean continuar;

    public void start() {
        try {
            System.out.println("Worker atendiendo peticiones...");
            Thread t = new Thread(new Runnable() {
                public void run() {
                    listen();
                }
            });
            continuar = true;
            t.start();
        } catch (Exception ex) {
        }
    }

    public void stop() {
        continuar = false;
        System.out.println("Conexion cerrada...");
    }

    public void listen() {
        int method;
        while (continuar) {
            try {
                method = is.readInt();
                System.out.println("Operacion: " + method);
                switch (method) {
                    case Protocol.RECETA_CREATE:
                        try {
                            service.agregarReceta((Receta) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this,"Receta creada");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_READ:
                        try {
                            int id = is.readInt();
                            Receta r = service.readReceta(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(r);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_UPDATE:
                        try {
                            int id = is.readInt();
                            service.updateReceta((Receta) is.readObject(), id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_DELETE:
                        try {
                            int id = is.readInt();
                            service.deleteReceta(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_FIND_ALL:
                        try {
                            List<Receta> le = service.obtenerTodasRecetas();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_FIND_BY_ESTADO:
                        try {
                            List<Receta> le = service.buscarRecetasPorEstado();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.DISCONNECT:
                        stop();
                        srv.remove(this);
                        break;
                }
                os.flush();
            } catch (IOException e) {
                stop();
            }
        }
    }

    public synchronized void deliver_message(String message) {
        if (as != null) {
            try {
                aos.writeInt(Protocol.DELIVER_MESSAGE);
                aos.writeObject(message);
                aos.flush();
            } catch (Exception e) {
            }
        }
    }

}
