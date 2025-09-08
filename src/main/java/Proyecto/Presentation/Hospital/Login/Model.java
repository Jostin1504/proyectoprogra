package Proyecto.Presentation.Hospital.Login;

import Proyecto.Logic.*;
import Proyecto.Data.data;
import Proyecto.Presentation.Hospital.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    private Usuario current;
    private String mensaje;
    public static final String CURRENT = "current";
    public static final String MENSAJE = "mensaje";

    public Model() {
        current = new Usuario();
        data datos = new data(); // Instancia de la capa de datos
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