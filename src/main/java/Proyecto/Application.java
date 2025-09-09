package Proyecto;

import Proyecto.Logic.Service;
import Proyecto.Logic.Usuario;
import Proyecto.Presentation.Hospital.Login.*;
import Proyecto.Presentation.Hospital.Medico.*;
import Proyecto.Presentation.Hospital.Paciente.Controller;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        Proyecto.Presentation.Hospital.Login.View loginView = new Proyecto.Presentation.Hospital.Login.View();
        Proyecto.Presentation.Hospital.Login.Model loginModel = new Proyecto.Presentation.Hospital.Login.Model();
        Proyecto.Presentation.Hospital.Login.Controller loginController = new Proyecto.Presentation.Hospital.Login.Controller(loginView, loginModel);

        Proyecto.Presentation.Hospital.Medico.View medicoView = new Proyecto.Presentation.Hospital.Medico.View();
        Proyecto.Presentation.Hospital.Medico.Model medicoModel = new Proyecto.Presentation.Hospital.Medico.Model();
        Proyecto.Presentation.Hospital.Medico.Controller medicoController = new Proyecto.Presentation.Hospital.Medico.Controller(medicoView, medicoModel);

        Proyecto.Presentation.Hospital.Farmaceuta.Model farmaceutaModel = new Proyecto.Presentation.Hospital.Farmaceuta.Model();
        Proyecto.Presentation.Hospital.Farmaceuta.View farmaceutaView = new Proyecto.Presentation.Hospital.Farmaceuta.View();
        Proyecto.Presentation.Hospital.Farmaceuta.Controller farmaceutaController = new Proyecto.Presentation.Hospital.Farmaceuta.Controller(farmaceutaView, farmaceutaModel);

        Proyecto.Presentation.Hospital.Paciente.Model pacienteModel = new Proyecto.Presentation.Hospital.Paciente.Model();
        Proyecto.Presentation.Hospital.Paciente.View pacienteView = new Proyecto.Presentation.Hospital.Paciente.View();
        Proyecto.Presentation.Hospital.Paciente.Controller pacienteController = new Proyecto.Presentation.Hospital.Paciente.Controller(pacienteView, pacienteModel);

        Proyecto.Presentation.Hospital.Prescripcion.Model prescripcionModel = new Proyecto.Presentation.Hospital.Prescripcion.Model();
        Proyecto.Presentation.Hospital.Prescripcion.View prescripcionView = new Proyecto.Presentation.Hospital.Prescripcion.View();
        Proyecto.Presentation.Hospital.Prescripcion.Controller prescripcionController = new Proyecto.Presentation.Hospital.Prescripcion.Controller(prescripcionView, prescripcionModel);

        JTabbedPane pantalla = new JTabbedPane();

        pantalla.add("Medico", medicoView.getMainPanelMedico());
        pantalla.add("Paciente", pacienteView.getMainPanelPaciente());

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Sistema de prescripciones");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.setContentPane(loginView.getLogPanel());

        JFrame window2 = new JFrame();
        window2.setSize(600,400);
        window2.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window2.setTitle("Sistema de prescripciones");
        window2.setLocationRelativeTo(null);
        window2.setVisible(false);

        window2.setContentPane(pantalla);
    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    /*private void navegarSegunTipoUsuario(String tipoUsuario, Usuario usuario) {
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
    }*/
}

