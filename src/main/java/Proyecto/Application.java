package Proyecto;

import Proyecto.Logic.Service;
import Proyecto.Logic.Usuario;
import Proyecto.Presentation.Paciente.Controller;
import Proyecto.Presentation.Prescripcion.Model;
import Proyecto.Presentation.Prescripcion.View;
import Proyecto.Presentation.Sesion;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        String sig = null;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {
            ex.printStackTrace();
        };
        doLogin();
        if(Sesion.isLoggedIn()){
            System.out.println("hola");
            doRun();
        }
    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    private static void doLogin(){
        Proyecto.Presentation.Login.View loginView = new Proyecto.Presentation.Login.View(null, true);
        loginView.setTitle("Hospital");
        loginView.pack();
        loginView.setLocationRelativeTo(null);
        Proyecto.Presentation.Login.Model loginModel = new Proyecto.Presentation.Login.Model();
        Proyecto.Presentation.Login.Controller loginController = new Proyecto.Presentation.Login.Controller(loginView, loginModel);
        loginView.setVisible(true);
    }

    private static void doRun(){
        JFrame window = new JFrame("Recetas");
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);
        window.setTitle("Recetas - " + Sesion.getUsuario().getCedula() + " (" + Sesion.getUsuario().getRol() + ")");
        switch (Sesion.getUsuario().getRol()){
            case "ADM":
                Proyecto.Presentation.Medico.View medicoView = new Proyecto.Presentation.Medico.View();
                Proyecto.Presentation.Medico.Model medicoModel = new Proyecto.Presentation.Medico.Model();
                Proyecto.Presentation.Medico.Controller medicoController = new Proyecto.Presentation.Medico.Controller(medicoView, medicoModel);
                tabbedPane.addTab("Medicos", medicoView.getMainPanelMedico());

                Proyecto.Presentation.Farmaceuta.Model farmaceutaModel = new Proyecto.Presentation.Farmaceuta.Model();
                Proyecto.Presentation.Farmaceuta.View farmaceutaView = new Proyecto.Presentation.Farmaceuta.View();
                Proyecto.Presentation.Farmaceuta.Controller farmaceutaController = new Proyecto.Presentation.Farmaceuta.Controller(farmaceutaView, farmaceutaModel);
                tabbedPane.addTab("Farmaceutas",  farmaceutaView.getMainPanelFarmaceuta());

                Proyecto.Presentation.Paciente.Model pacienteModel = new Proyecto.Presentation.Paciente.Model();
                Proyecto.Presentation.Paciente.View pacienteView = new Proyecto.Presentation.Paciente.View();
                Controller pacienteController = new Controller(pacienteView, pacienteModel);
                tabbedPane.addTab("Pacientes", pacienteView.getMainPanelPaciente());

                //PARA TODAS ESTAS FALTA CREAR LOS TRES MVC IGUAL Q EN LOS DE ARRIBA
                //tabbedPane.addTab("Medicamentos");
                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                //tabbedPane.addTab("Acerca de...");
                break;
            case "MED":
                //IGUAL AQUÍ
                Model prescripcionModel = new Model();
                View prescripcionView = new View();
                Proyecto.Presentation.Prescripcion.Controller prescripcionController = new Proyecto.Presentation.Prescripcion.Controller(prescripcionView, prescripcionModel);
                tabbedPane.addTab("Prescribir",  prescripcionView.getMainPanelPrescripcion());

                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                //tabbedPane.addTab("Acerca de...");
                break;
            case "FAR":
                //todavia no se cuales van aquí
                break;
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 700);  // O usa window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

