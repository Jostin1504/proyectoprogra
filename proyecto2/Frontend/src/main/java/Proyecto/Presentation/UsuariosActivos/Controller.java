package Proyecto.Presentation.UsuariosActivos;

import Proyecto.Presentation.*;
import Proyecto.logic.*;
import javax.swing.SwingUtilities;
import java.util.List;

public class Controller implements ThreadListener {
    private View view;
    private Model model;
    private SocketListener socketListener;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        cargarUsuariosActivos();

        try {
            socketListener = new SocketListener(this, Service.instance().getSid());
            socketListener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarUsuariosActivos() {
        try {
            List<Usuario> usuarios = Service.instance().getActiveUsers();
            model.setActiveUsers(usuarios);
            System.out.println("Usuarios activos cargados: " + usuarios.size());
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios activos: " + e.getMessage());
        }
    }

    @Override
    public void deliver_message(String message) {
        SwingUtilities.invokeLater(() -> {
            // Solo procesar mensajes de usuarios
            if (!message.startsWith("USER_CONNECTED:") && !message.startsWith("USER_DISCONNECTED:")) {
                return; // Ignorar otros mensajes
            }

            if (message.startsWith("USER_CONNECTED:")) {
                String[] parts = message.split(":");
                if (parts.length >= 3) {
                    String cedula = parts[1];
                    String nombre = parts[2];

                    Usuario usuario = new Usuario();
                    usuario.setCedula(cedula);
                    usuario.setNombre(nombre);
                    model.addUser(usuario);
                }
            } else if (message.startsWith("USER_DISCONNECTED:")) {
                String[] parts = message.split(":");
                if (parts.length >= 2) {
                    String cedula = parts[1];
                    model.removeUser(cedula);
                }
            }
        });
    }

    public void stop() {
        if (socketListener != null) {
            socketListener.stop();
        }
    }
}
