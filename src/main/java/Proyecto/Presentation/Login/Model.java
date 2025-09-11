package Proyecto.Presentation.Login;

import Proyecto.Logic.*;
import Proyecto.Data.data;
import Proyecto.Presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    private Usuario current;
    private String mensaje;
    public static final String CURRENT = "current";
    public static final String MENSAJE = "mensaje";

    public Model() {
        current = new Usuario();
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
}