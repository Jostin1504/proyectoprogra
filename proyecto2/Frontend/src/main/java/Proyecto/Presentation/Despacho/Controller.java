package Proyecto.Presentation.Despacho;

import Proyecto.Presentation.ThreadListener;
import Proyecto.Presentation.SocketListener;
import javax.swing.SwingUtilities;

import Proyecto.logic.Paciente;
import Proyecto.logic.Service;

public class Controller implements ThreadListener {
    private SocketListener socketListener;
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setRecetas(model.getCurrent().getRecetas());
        try {
            socketListener = new SocketListener(this, Service.instance().getSid());
            socketListener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            // Refrescar lista de recetas si hay un paciente actual
            if (model.getCurrent() != null && model.getCurrent().getId() != null) {
                try {
                    buscarPaciente(model.getCurrent().getId());
                    System.out.println("Despacho actualizado: " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        if (socketListener != null) {
            socketListener.stop();
        }
    }

    public void guardarEstado(String e){
        model.getReceta().setEstado(e);
    }
}
