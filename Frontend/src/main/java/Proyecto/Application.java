package Proyecto;

import javax.swing.border.Border;
import Proyecto.logic.Service;
import Proyecto.logic.*;
import Proyecto.Presentation.Sesion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public static final Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
    public static final int MODE_CREATE = 1;
    public static final int MODE_EDIT = 2;

    private static void doLogin(){
        Proyecto.Presentation.Login.View loginView = new Proyecto.Presentation.Login.View(null, true);
        loginView.setTitle("Hospital");
        loginView.pack();
        loginView.setLocationRelativeTo(null);
        Proyecto.Presentation.Login.Model loginModel = new Proyecto.Presentation.Login.Model();
        Proyecto.Presentation.Login.Controller loginController = new Proyecto.Presentation.Login.Controller(loginView, loginModel);
        loginView.setVisible(true);

        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!Sesion.isLoggedIn()) {
                    System.exit(0);
                }
            }
        });
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

        Proyecto.Presentation.Despacho.Model despachoModel = new Proyecto.Presentation.Despacho.Model();
        Proyecto.Presentation.Despacho.View despachoView = new Proyecto.Presentation.Despacho.View();
        Proyecto.Presentation.Despacho.Controller despachoController = new Proyecto.Presentation.Despacho.Controller(despachoView, despachoModel);

        Proyecto.Presentation.Dashboard.Model dashboardModel = new Proyecto.Presentation.Dashboard.Model();
        Proyecto.Presentation.Dashboard.View dashboardView = new Proyecto.Presentation.Dashboard.View();
        Proyecto.Presentation.Dashboard.Controller dashboardController =  new Proyecto.Presentation.Dashboard.Controller(dashboardView, dashboardModel);

        switch (Sesion.getUsuario().getRol()){
            case "ADM":
                tabbedPane.addTab("Medicos", medicoView.getMainPanelMedico());
                tabbedPane.addTab("Farmaceutas",  farmaceutaView.getMainPanelFarmaceuta());
                tabbedPane.addTab("Pacientes", pacienteView.getMainPanelPaciente());
                tabbedPane.addTab("Medicamentos", medicamentosView.getMainPanelMedicamento());
                tabbedPane.addTab("Dashboard", dashboardView.getMainPanelDashboard());

                //tabbedPane.addTab("Historico");
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());
                break;
            case "MED":
                //IGUAL AQUÍ
                tabbedPane.addTab("Prescribir",  prescripcionView.getMainPanelPrescripcion());
                tabbedPane.addTab("Dashboard", dashboardView.getMainPanelDashboard());

                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                break;
            case "FAR":
                tabbedPane.addTab("Despacho", despachoView.getMainPanelDespacho());
                tabbedPane.addTab("Dashboard", dashboardView.getMainPanelDashboard());

                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                //tabbedPane.addTab("Dashboard");
                //tabbedPane.addTab("Historico");
                break;
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1200, 600);  // O usa window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        window.setVisible(true);
        window.setAlwaysOnTop(false);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (dashboardController != null) {
                    dashboardController.stop();
                }
                if (despachoController != null) {
                    despachoController.stop();
                }
                Service.instance().stop();
            }
        });
        window.setVisible(true);
    }

}

