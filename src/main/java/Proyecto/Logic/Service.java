package Proyecto.Logic;

import Proyecto.Data.data;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private data datos;

    public Service() {
        datos = new data();
    }

    // --- MÉTODOS PARA LOGIN ---
    public Usuario validarCredenciales(String id, String clave) {
        List<Usuario> todosUsuarios = obtenerTodosLosUsuarios();

        for (Usuario usuario : todosUsuarios) {
            if (usuario.getCedula().equals(id) && usuario.getClave().equals(clave)) {
                return usuario;
            }
        }
        return null; // No encontrado
    }

    public String getTipoUsuario(Usuario usuario) {
        if (usuario instanceof Medico) {
            return "MEDICO";
        } else if (usuario instanceof Farmaceuta) {
            return "FARMACEUTA";
        } else if (usuario instanceof Administrador) {
            return "ADMINISTRADOR";
        }
        return "DESCONOCIDO";
    }

    public boolean cambiarClave(String id, String claveActual, String claveNueva) {
        Usuario usuario = validarCredenciales(id, claveActual);
        if (usuario != null) {
            usuario.setClave(claveNueva);
            return true;
        }
        return false;
    }

    // --- MÉTODOS AUXILIARES ---
    private List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> todosUsuarios = new ArrayList<>();
        todosUsuarios.addAll(datos.getMedicos());
        todosUsuarios.addAll(datos.getFarmaceutas());
        todosUsuarios.addAll(datos.getAdministradores());
        return todosUsuarios;
    }

    // --- GETTERS PARA ACCESO A DATOS ---
    public List<Medico> getMedicos() {
        return datos.getMedicos();
    }

    public List<Farmaceuta> getFarmaceutas() {
        return datos.getFarmaceutas();
    }

    public List<Administrador> getAdministradores() {
        return datos.getAdministradores();
    }

    public List<Paciente> getPacientes() {
        return datos.getPacientes();
    }

    public List<Medicamento> getMedicamentos() {
        return datos.getMedicamentos();
    }
}