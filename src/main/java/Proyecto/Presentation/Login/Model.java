package Proyecto.Presentation.Login;

import Proyecto.Logic.*;
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
        propertyChangeSupport.firePropertyChange(CURRENT, null, current);
    }

    public Usuario getCurrent() {
        return current;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setCurrent(Usuario current) {
        Usuario oldCurrent = this.current;
        this.current = current;
        propertyChangeSupport.firePropertyChange(CURRENT, oldCurrent, current);
    }

    public void setMensaje(String mensaje) {
        String oldMensaje = this.mensaje;
        this.mensaje = mensaje;
        propertyChangeSupport.firePropertyChange(MENSAJE, oldMensaje, mensaje);
    }
}