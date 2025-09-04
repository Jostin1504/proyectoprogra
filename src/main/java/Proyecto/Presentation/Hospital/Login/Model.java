package Proyecto.Presentation.Hospital.Login;

import Proyecto.Logic.*;
import Proyecto.Data.data;
import Proyecto.Presentation.Hospital.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    private Usuario current;
    private data datos;
    private String mensaje;
    private Service service;
    public static final String CURRENT = "current";
    public static final String MENSAJE = "mensaje";

    public Model() {
        current = new Usuario();
        datos = new data(); // Instancia de la capa de datos
        mensaje = "";
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(MENSAJE);
    }

    public Usuario getCurrent() {
        return current;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        firePropertyChange(MENSAJE);
    }

    public Usuario validarCredenciales(String id, String clave) {
        return service.validarCredenciales(id, clave);
    }

    public String getTipoUsuario(Usuario usuario) {
        return service.getTipoUsuario(usuario);
    }

    public boolean cambiarClave(String id, String claveActual, String claveNueva) {
        return service.cambiarClave(id, claveActual, claveNueva);
    }

    public Service getService() {
        return service;
    }
}