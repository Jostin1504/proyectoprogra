package Proyecto.Presentation.Despacho;

import Proyecto.Presentation.*;
import javax.swing.SwingUtilities;
import Proyecto.logic.*;
import java.util.List;
import Proyecto.Presentation.SocketManager;

public class Controller implements ThreadListener {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setRecetas(model.getCurrent().getRecetas());

        // ✅ REGISTRARSE EN EL SOCKETMANAGER
        SocketManager.getInstance().addListener(this);
    }

    public void clear(){
        model.setCurrent(new Paciente());
    }

    public void buscarPaciente(String id){
        try {
            Paciente paciente = Service.instance().buscarPaciente(id);
            model.setCurrent(paciente);
            model.setRecetas(paciente.getRecetas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente getPaciente(String id){
        try {
            return Service.instance().buscarPaciente(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deliver_message(String message) {
        SwingUtilities.invokeLater(() -> {
            if (message.startsWith("USER_CONNECTED:") || message.startsWith("USER_DISCONNECTED:")) {
                return;
            }
            if (model.getCurrent() != null && model.getCurrent().getId() != null) {
                try {
                    buscarPaciente(model.getCurrent().getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        // ✅ DESREGISTRARSE
        SocketManager.getInstance().removeListener(this);
    }

    public void guardarEstado(String nuevoEstado) throws Exception {
        if (model.getReceta() == null) {
            throw new Exception("No hay receta seleccionada");
        }
        model.getReceta().setEstado(nuevoEstado);
        Service.instance().actualizarReceta(model.getReceta().getId(), model.getReceta());
        if (model.getCurrent() != null && model.getCurrent().getId() != null) {
            String idPaciente = model.getCurrent().getId();
            List<Receta> recetasActualizadas = Service.instance().buscarRecetasPorPaciente(idPaciente);
            model.setRecetas(recetasActualizadas);
        }
    }

    private int getRecetaId(Receta receta) {
        return model.getRecetas().indexOf(receta);
    }
}
