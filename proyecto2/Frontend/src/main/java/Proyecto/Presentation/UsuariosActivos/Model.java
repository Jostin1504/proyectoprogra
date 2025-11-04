package Proyecto.Presentation.UsuariosActivos;

import Proyecto.Presentation.AbstractModel;
import Proyecto.logic.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private List<Usuario> activeUsers;

    public static final String ACTIVE_USERS = "ActiveUsers";

    public Model() {
        this.activeUsers = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(ACTIVE_USERS);
    }

    public List<Usuario> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<Usuario> activeUsers) {
        this.activeUsers = activeUsers;
        firePropertyChange(ACTIVE_USERS);
    }

    public void addUser(Usuario usuario) {

        // Verificar que no exista ya
        boolean existe = activeUsers.stream()
                .anyMatch(u -> u.getCedula().equals(usuario.getCedula()));

        if (!existe) {
            activeUsers.add(usuario);
            firePropertyChange(ACTIVE_USERS);
        } else {
            System.out.println("Usuario ya existe en la lista");
        }
    }

    public void removeUser(String cedula) {
        activeUsers.removeIf(u -> u.getCedula().equals(cedula));
        firePropertyChange(ACTIVE_USERS);
    }
}
