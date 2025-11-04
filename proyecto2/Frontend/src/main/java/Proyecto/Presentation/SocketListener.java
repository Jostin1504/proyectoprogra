/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Presentation;

import Proyecto.logic.Protocol;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketListener {
    ThreadListener listener;
    String sid; // Session Id
    Socket as; // Asynchronous Socket
    ObjectOutputStream aos;
    ObjectInputStream ais;

    public SocketListener(ThreadListener listener, String sid) throws Exception {
        System.out.println("=== CREANDO SocketListener ===");
        System.out.println("SID: " + sid);
        this.listener = listener;

        System.out.println("Conectando socket asíncrono a " + Protocol.SERVER + ":" + Protocol.PORT);
        as = new Socket(Protocol.SERVER, Protocol.PORT);
        this.sid = sid;

        System.out.println("Creando streams...");
        aos = new ObjectOutputStream(as.getOutputStream());
        ais = new ObjectInputStream(as.getInputStream());

        System.out.println("Enviando ASYNC...");
        aos.writeInt(Protocol.ASYNC);
        aos.writeObject(sid);
        aos.flush();
        System.out.println("✅ SocketListener creado correctamente");
    }

    boolean condition = true;
    private Thread t;

    public void start() {
        System.out.println("=== INICIANDO SocketListener.listen() ===");
        t = new Thread(new Runnable() {
            public void run() {
                listen();
            }
        });
        condition = true;
        t.start();
    }

    public void stop() { condition = false; }

    public void listen() {
        System.out.println(">>> SocketListener escuchando mensajes...");
        int method;
        while (condition) {
            try {
                method = ais.readInt();
                System.out.println(">>> Mensaje recibido: " + method);
                switch (method) {
                    case Protocol.DELIVER_MESSAGE:
                        try {
                            String message = (String) ais.readObject();
                            System.out.println(">>> Contenido: " + message);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    listener.deliver_message(message);
                                }
                            });
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            } catch (IOException ex) {
                System.out.println(">>> SocketListener terminado: " + ex.getMessage());
                condition = false;
            }
        }
        try {
            as.shutdownOutput();
            as.close();
        } catch (IOException e) {}
    }

}
