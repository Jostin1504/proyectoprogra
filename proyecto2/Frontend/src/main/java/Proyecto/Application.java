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
        System.out.println("Iniciando aplicación...");

        // Ejecutar en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("Mostrando ventana de login...");
                doLogin();

                System.out.println("Login completado. Usuario logueado: " + Sesion.isLoggedIn());

                if(Sesion.isLoggedIn()){
                    try {
                        doRun();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public static final Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
    public static final int MODE_CREATE = 1;
    public static final int MODE_EDIT = 2;

    private static void doLogin(){
        Proyecto.Presentation.Login.View loginView = new Proyecto.Presentation.Login.View(null, true);
        loginView.setTitle("Hospital - Login");
        loginView.pack();
        loginView.setLocationRelativeTo(null);

        Proyecto.Presentation.Login.Model loginModel = new Proyecto.Presentation.Login.Model();
        Proyecto.Presentation.Login.Controller loginController = new Proyecto.Presentation.Login.Controller(loginView, loginModel);

        // Agregar listener para cerrar la aplicación si se cierra el login sin autenticarse
        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!Sesion.isLoggedIn()) {
                    System.exit(0);
                }
            }
        });

        loginView.setVisible(true);
    }

    private static void doRun() throws Exception {
        JFrame window = new JFrame("Recetas");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);
        window.setTitle("Recetas - " + Sesion.getUsuario().getCedula() + " (" + Sesion.getUsuario().getRol() + ")");

        // Inicializar controladores según el rol
        Proyecto.Presentation.AcercaDe.View acercaDeView = new Proyecto.Presentation.AcercaDe.View();

        switch (Sesion.getUsuario().getRol()){
            case "ADM":
                Proyecto.Presentation.Medico.Model medicoModel = new Proyecto.Presentation.Medico.Model();
                Proyecto.Presentation.Medico.View medicoView = new Proyecto.Presentation.Medico.View();
                Proyecto.Presentation.Medico.Controller medicoController = new Proyecto.Presentation.Medico.Controller(medicoView, medicoModel);

                Proyecto.Presentation.Farmaceuta.Model farmaceutaModel = new Proyecto.Presentation.Farmaceuta.Model();
                Proyecto.Presentation.Farmaceuta.View farmaceutaView = new Proyecto.Presentation.Farmaceuta.View();
                Proyecto.Presentation.Farmaceuta.Controller farmaceutaController = new Proyecto.Presentation.Farmaceuta.Controller(farmaceutaView, farmaceutaModel);

                Proyecto.Presentation.Paciente.Model pacienteModel = new Proyecto.Presentation.Paciente.Model();
                Proyecto.Presentation.Paciente.View pacienteView = new Proyecto.Presentation.Paciente.View();
                Proyecto.Presentation.Paciente.Controller pacienteController = new Proyecto.Presentation.Paciente.Controller(pacienteView, pacienteModel);

                Proyecto.Presentation.Medicamentos.Model medicamentosModel = new Proyecto.Presentation.Medicamentos.Model();
                Proyecto.Presentation.Medicamentos.View medicamentosView = new Proyecto.Presentation.Medicamentos.View();
                Proyecto.Presentation.Medicamentos.Controller medicamentosController = new Proyecto.Presentation.Medicamentos.Controller(medicamentosView, medicamentosModel);

                Proyecto.Presentation.Dashboard.Model dashboardModelAdm = new Proyecto.Presentation.Dashboard.Model();
                Proyecto.Presentation.Dashboard.View dashboardViewAdm = new Proyecto.Presentation.Dashboard.View();
                Proyecto.Presentation.Dashboard.Controller dashboardControllerAdm = new Proyecto.Presentation.Dashboard.Controller(dashboardViewAdm, dashboardModelAdm);

                Proyecto.Presentation.Historico.Model historicoModelAdm = new Proyecto.Presentation.Historico.Model();
                Proyecto.Presentation.Historico.View historicoViewAdm = new Proyecto.Presentation.Historico.View();
                Proyecto.Presentation.Historico.Controller historicoControllerAdm = new Proyecto.Presentation.Historico.Controller(historicoViewAdm, historicoModelAdm);

                tabbedPane.addTab("Medicos", medicoView.getMainPanelMedico());
                tabbedPane.addTab("Farmaceutas", farmaceutaView.getMainPanelFarmaceuta());
                tabbedPane.addTab("Pacientes", pacienteView.getMainPanelPaciente());
                tabbedPane.addTab("Medicamentos", medicamentosView.getMainPanelMedicamento());
                tabbedPane.addTab("Dashboard", dashboardViewAdm.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dashboardControllerAdm.stop();
                        Service.instance().stop();
                    }
                });
                break;

            case "MED":
                Proyecto.Presentation.Prescripcion.Model prescripcionModel = new Proyecto.Presentation.Prescripcion.Model();
                Proyecto.Presentation.Prescripcion.View prescripcionView = new Proyecto.Presentation.Prescripcion.View();
                Proyecto.Presentation.Prescripcion.Controller prescripcionController = new Proyecto.Presentation.Prescripcion.Controller(prescripcionView, prescripcionModel);

                Proyecto.Presentation.Dashboard.Model dashboardModelMed = new Proyecto.Presentation.Dashboard.Model();
                Proyecto.Presentation.Dashboard.View dashboardViewMed = new Proyecto.Presentation.Dashboard.View();
                Proyecto.Presentation.Dashboard.Controller dashboardControllerMed = new Proyecto.Presentation.Dashboard.Controller(dashboardViewMed, dashboardModelMed);

                Proyecto.Presentation.Historico.Model historicoModelMed = new Proyecto.Presentation.Historico.Model();
                Proyecto.Presentation.Historico.View historicoViewMed = new Proyecto.Presentation.Historico.View();
                Proyecto.Presentation.Historico.Controller historicoControllerMed = new Proyecto.Presentation.Historico.Controller(historicoViewMed, historicoModelMed);


                tabbedPane.addTab("Prescribir", prescripcionView.getMainPanelPrescripcion());
                tabbedPane.addTab("Dashboard", dashboardViewMed.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dashboardControllerMed.stop();
                        Service.instance().stop();
                    }
                });
                break;

            case "FAR":
                Proyecto.Presentation.Despacho.Model despachoModel = new Proyecto.Presentation.Despacho.Model();
                Proyecto.Presentation.Despacho.View despachoView = new Proyecto.Presentation.Despacho.View();
                Proyecto.Presentation.Despacho.Controller despachoController = new Proyecto.Presentation.Despacho.Controller(despachoView, despachoModel);

                Proyecto.Presentation.Dashboard.Model dashboardModelFar = new Proyecto.Presentation.Dashboard.Model();
                Proyecto.Presentation.Dashboard.View dashboardViewFar = new Proyecto.Presentation.Dashboard.View();
                Proyecto.Presentation.Dashboard.Controller dashboardControllerFar = new Proyecto.Presentation.Dashboard.Controller(dashboardViewFar, dashboardModelFar);

                Proyecto.Presentation.Historico.Model historicoModelFar = new Proyecto.Presentation.Historico.Model();
                Proyecto.Presentation.Historico.View historicoViewFar = new Proyecto.Presentation.Historico.View();
                Proyecto.Presentation.Historico.Controller historicoControllerFar = new Proyecto.Presentation.Historico.Controller(historicoViewFar, historicoModelFar);


                tabbedPane.addTab("Despacho", despachoView.getMainPanelDespacho());
                tabbedPane.addTab("Dashboard", dashboardViewFar.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        despachoController.stop();
                        dashboardControllerFar.stop();
                        Service.instance().stop();
                    }
                });
                break;
        }

        window.setSize(1200, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

