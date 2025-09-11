package Proyecto.Presentation.Login;

import Proyecto.Logic.Service;
import Proyecto.Logic.Usuario;
import Proyecto.Presentation.Sesion;

import javax.swing.*;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void login(Usuario usuario)throws Exception{
        Usuario logged = Service.instance().read(usuario);
        if (!logged.getClave().equals(usuario.getClave()) || !logged.getCedula().equals(usuario.getCedula())) {
            throw new Exception("Usuario o clave incorrectos");
        }
        Sesion.setUsuario(logged);
        view.dispose();
    }

    public void read(String id) throws Exception {
        Usuario e = new Usuario("", id, " ");

        try {
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
            Usuario b = new Usuario();
            b.setCedula(id);
            model.setCurrent(b);
            throw ex;
        }
    }

   /* public void entrar() {
        try {
            String id = view.getId();
            String clave = view.getClave();

            Usuario usuario = encontrarUsuario(id, clave);

            if (usuario != null) {
                String tipoUsuario = getRol(usuario);
                model.setMensaje("Login exitoso. Bienvenido " + usuario.getNombre());
                Service.instance().ent();
                navegarSegunTipoUsuario(tipoUsuario);
            } else {
                model.setMensaje("Credenciales incorrectas");
                model.setCurrent(new Usuario());
            }
        } catch (Exception e) {
            model.setMensaje("Error durante el login: " + e.getMessage());
        }
    }*/

    public void cambiarClave() {
        try {
            String id = view.getId();

            if (id.trim().isEmpty()) {
                model.setMensaje("Debe ingresar su ID para cambiar la clave");
                return;
            }

            // pide la clave actual
            String claveActual = JOptionPane.showInputDialog(
                    view.getLogPanel(),
                    "Ingrese su clave actual:",
                    "Cambiar Clave",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (claveActual == null || claveActual.trim().isEmpty()) {
                model.setMensaje("Operación cancelada");
                return;
            }

            // pide la clave nueva
            String claveNueva = JOptionPane.showInputDialog(
                    view.getLogPanel(),
                    "Ingrese su nueva clave:",
                    "Cambiar Clave",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (claveNueva == null || claveNueva.trim().isEmpty()) {
                model.setMensaje("Operación cancelada");
                return;
            }

            String confirmarClave = JOptionPane.showInputDialog(
                    view.getLogPanel(),
                    "Confirme su nueva clave:",
                    "Cambiar Clave",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (confirmarClave == null || !claveNueva.equals(confirmarClave)) {
                model.setMensaje("Las claves no coinciden");
                return;
            }

            boolean cambioExitoso = cambiarClave(id, claveActual, claveNueva);

            if (cambioExitoso) {
                model.setMensaje("Clave cambiada exitosamente");
                clear();
            } else {
                model.setMensaje("No se pudo cambiar la clave. Verifique sus credenciales actuales");
            }

        } catch (Exception e) {
            model.setMensaje("Error al cambiar la clave: " + e.getMessage());
        }
    }

    private void navegarSegunTipoUsuario(String tipoUsuario) {
        switch (tipoUsuario) {
            case "MEDICO":
                JOptionPane.showMessageDialog(
                        view.getLogPanel(),
                        "aqui va la interfaz de medico",
                        "Login Exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                break;

            case "FARMACEUTA":
                JOptionPane.showMessageDialog(
                        view.getLogPanel(),
                        "aqui va la interfaz de farmaceuta",
                        "Login Exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                break;

            case "ADMINISTRADOR":
                JOptionPane.showMessageDialog(
                        view.getLogPanel(),
                        "aqui va la interfaz de administrador",
                        "Login Exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                break;

            default:
                model.setMensaje("Tipo de usuario no reconocido");
                break;
        }
    }

    public void clear(){
        model.setCurrent(new Usuario());
    }

    public void salir() {
        int opcion = JOptionPane.showConfirmDialog(
                view.getLogPanel(),
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    public boolean cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        return Service.instance().cambiarClave(id, claveActual, claveNueva);
    }
}
