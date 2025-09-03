package Proyecto.Presentation.Hospital.Login;

import Proyecto.Logic.Usuario;
import Proyecto.Presentation.Hospital.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel{
    Usuario current;
    List<Usuario> usuarios;

    public static final String CURRENT = "current";
    public static final String USUARIOS = "usuarios";

    public Model() {
        current = new Usuario();
        usuarios = new ArrayList<Usuario>();
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(USUARIOS);
    }

    public Usuario getCurrent() {
        return current;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
        firePropertyChange(CURRENT);
        firePropertyChange(USUARIOS);
    }
}
