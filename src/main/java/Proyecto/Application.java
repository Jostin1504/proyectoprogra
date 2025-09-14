package Proyecto;

import Proyecto.Logic.Service;
import Proyecto.Logic.Usuario;
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
            try {
                doRun();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }

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

    private static void doRun() throws Exception {
        JFrame window = new JFrame("Recetas");
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);
        window.setTitle("Recetas - " + Sesion.getUsuario().getCedula() + " (" + Sesion.getUsuario().getRol() + ")");

        Proyecto.Presentation.Medico.Model medicoModel = new Proyecto.Presentation.Medico.Model();
        Proyecto.Presentation.Medico.View medicoView = new Proyecto.Presentation.Medico.View();
        Proyecto.Presentation.Medico.Controller medicoController = new Proyecto.Presentation.Medico.Controller(medicoView, medicoModel);

        Proyecto.Presentation.Farmaceuta.Model farmaceutaModel = new Proyecto.Presentation.Farmaceuta.Model();
        Proyecto.Presentation.Farmaceuta.View farmaceutaView = new Proyecto.Presentation.Farmaceuta.View();
        Proyecto.Presentation.Farmaceuta.Controller farmaceutaController = new Proyecto.Presentation.Farmaceuta.Controller(farmaceutaView, farmaceutaModel);

        Proyecto.Presentation.Paciente.Model pacienteModel = new Proyecto.Presentation.Paciente.Model();
        Proyecto.Presentation.Paciente.View pacienteView = new Proyecto.Presentation.Paciente.View();
        Proyecto.Presentation.Paciente.Controller pacienteController = new Proyecto.Presentation.Paciente.Controller(pacienteView, pacienteModel);

        Proyecto.Presentation.Prescripcion.Model prescripcionModel = new Proyecto.Presentation.Prescripcion.Model();
        Proyecto.Presentation.Prescripcion.View prescripcionView = new Proyecto.Presentation.Prescripcion.View();
        Proyecto.Presentation.Prescripcion.Controller prescripcionController = new Proyecto.Presentation.Prescripcion.Controller(prescripcionView, prescripcionModel);

        Proyecto.Presentation.Medicamentos.Model medicamentosModel = new Proyecto.Presentation.Medicamentos.Model();
        Proyecto.Presentation.Medicamentos.View medicamentosView = new Proyecto.Presentation.Medicamentos.View();
        Proyecto.Presentation.Medicamentos.Controller medicamentosController = new Proyecto.Presentation.Medicamentos.Controller(medicamentosView, medicamentosModel);


        Proyecto.Presentation.AcercaDe.View acercaDeView = new Proyecto.Presentation.AcercaDe.View();

        switch (Sesion.getUsuario().getRol()){
            case "ADM":
                tabbedPane.addTab("Medicos", medicoView.getMainPanelMedico());
                tabbedPane.addTab("Farmaceutas",  farmaceutaView.getMainPanelFarmaceuta());
                tabbedPane.addTab("Pacientes", pacienteView.getMainPanelPaciente());
                tabbedPane.addTab("Medicamentos", medicamentosView.getMainPanelMedicamento());
                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());
                break;
            case "MED":
                //IGUAL AQUÍ
                tabbedPane.addTab("Prescribir",  prescripcionView.getMainPanelPrescripcion());

                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                break;
            case "FAR":


                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());
                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                break;
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 700);  // O usa window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

