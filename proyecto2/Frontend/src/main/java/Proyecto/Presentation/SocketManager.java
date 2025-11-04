package Proyecto.Presentation;

import Proyecto.logic.Service;
import java.util.ArrayList;
import java.util.List;

public class SocketManager {
    private static SocketManager instance;
    private SocketListener socketListener;
    private List<ThreadListener> listeners;

    private SocketManager() {
        listeners = new ArrayList<>();
    }

    public static SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    public void initialize() throws Exception {
        if (socketListener == null) {
            System.out.println("=== Inicializando SocketListener ÚNICO ===");
            socketListener = new SocketListener(new ThreadListener() {
                @Override
                public void deliver_message(String message) {
                    System.out.println("SocketManager recibió: " + message);
                    // Propagar a todos los listeners registrados
                    for (ThreadListener listener : listeners) {
                        listener.deliver_message(message);
                    }
                }
            }, Service.instance().getSid());
            socketListener.start();
            System.out.println("✅ SocketListener único iniciado");
        }
    }

    public void addListener(ThreadListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            System.out.println("Listener añadido. Total: " + listeners.size());
        }
    }

    public void removeListener(ThreadListener listener) {
        listeners.remove(listener);
        System.out.println("Listener removido. Total: " + listeners.size());
    }

    public void stop() {
        if (socketListener != null) {
            socketListener.stop();
            System.out.println("SocketListener detenido");
        }
    }
}