package Proyecto;

import javax.swing.border.Border;

import Proyecto.Presentation.UsuariosActivos.Controller;
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

        switch (Sesion.getUsuario().getRol()) {
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

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelAdm = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewAdm = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerAdm = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewAdm, usuariosActivosModelAdm);

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelHistorico = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewHistorico = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerHistorico = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewHistorico, usuariosActivosModelHistorico);

                // Para Médicos
                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelMed = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewMed = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerMed = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewMed, usuariosActivosModelMed);

                // Para Farmaceutas
                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelFar = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewFar = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerFar = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewFar, usuariosActivosModelFar);

                // Para Pacientes
                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelPac = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewPac = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerPac = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewPac, usuariosActivosModelPac);

                // Para Medicamentos
                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelMeds = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewMeds = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerMeds = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewMeds, usuariosActivosModelMeds);

                // Para Dashboard
                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelDash = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewDash = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerDash = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewDash, usuariosActivosModelDash);

                // AÑADIR A TABS CON SUS RESPECTIVAS INSTANCIAS
                JPanel historicoWithUsers = new JPanel(new BorderLayout());
                historicoWithUsers.add(historicoViewAdm.getMainPanelHistorico(), BorderLayout.CENTER);
                historicoWithUsers.add(usuariosActivosViewHistorico.getMainPanel(), BorderLayout.EAST);

                JPanel MedWithUsers = new JPanel(new BorderLayout());
                MedWithUsers.add(medicoView.getMainPanelMedico(), BorderLayout.CENTER);
                MedWithUsers.add(usuariosActivosViewMed.getMainPanel(), BorderLayout.EAST);

                JPanel FarWithUsers = new JPanel(new BorderLayout());
                FarWithUsers.add(farmaceutaView.getMainPanelFarmaceuta(), BorderLayout.CENTER);
                FarWithUsers.add(usuariosActivosViewFar.getMainPanel(), BorderLayout.EAST);

                JPanel PacWithUsers = new JPanel(new BorderLayout());
                PacWithUsers.add(pacienteView.getMainPanelPaciente(), BorderLayout.CENTER);
                PacWithUsers.add(usuariosActivosViewPac.getMainPanel(), BorderLayout.EAST);

                JPanel MedsWithUsers = new JPanel(new BorderLayout());
                MedsWithUsers.add(medicamentosView.getMainPanelMedicamento(), BorderLayout.CENTER);
                MedsWithUsers.add(usuariosActivosViewMeds.getMainPanel(), BorderLayout.EAST);


                // Añadir tabs
                tabbedPane.addTab("Histórico", historicoWithUsers);
                tabbedPane.addTab("Medicos", MedWithUsers);
                tabbedPane.addTab("Farmaceutas", FarWithUsers);
                tabbedPane.addTab("Pacientes", PacWithUsers);
                tabbedPane.addTab("Medicamentos", MedsWithUsers);
                tabbedPane.addTab("Dashboard", dashboardViewAdm.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        usuariosActivosControllerHistorico.stop();
                        usuariosActivosControllerMed.stop();
                        usuariosActivosControllerFar.stop();
                        usuariosActivosControllerPac.stop();
                        usuariosActivosControllerMeds.stop();
                        usuariosActivosControllerDash.stop();
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

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelHistoricoMed = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewHistoricoMed = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerHistoricoMed = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewHistoricoMed, usuariosActivosModelHistoricoMed);

                JPanel historicoWithUsersMed = new JPanel(new BorderLayout());
                historicoWithUsersMed.add(historicoViewMed.getMainPanelHistorico(), BorderLayout.CENTER);
                historicoWithUsersMed.add(usuariosActivosViewHistoricoMed.getMainPanel(), BorderLayout.EAST);

                tabbedPane.addTab("Histórico", historicoWithUsersMed);

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelPresMed = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewPresMed = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerPresMed = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewPresMed, usuariosActivosModelPresMed);

                JPanel presWithUsersMed = new JPanel(new BorderLayout());
                presWithUsersMed.add(prescripcionView.getMainPanelPrescripcion(), BorderLayout.CENTER);
                presWithUsersMed.add(usuariosActivosViewPresMed.getMainPanel(), BorderLayout.EAST);

                tabbedPane.addTab("Prescribir", presWithUsersMed);

                tabbedPane.addTab("Dashboard", dashboardViewMed.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        usuariosActivosControllerHistoricoMed.stop();
                        usuariosActivosControllerPresMed.stop();
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

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelHistoricoFar = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewHistoricoFar = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerHistoricoFar = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewHistoricoFar, usuariosActivosModelHistoricoFar);

                JPanel historicoWithUsersFar = new JPanel(new BorderLayout());
                historicoWithUsersFar.add(historicoViewFar.getMainPanelHistorico(), BorderLayout.CENTER);
                historicoWithUsersFar.add(usuariosActivosViewHistoricoFar.getMainPanel(), BorderLayout.EAST);

                tabbedPane.addTab("Histórico", historicoWithUsersFar);

                Proyecto.Presentation.UsuariosActivos.Model usuariosActivosModelDesFar = new Proyecto.Presentation.UsuariosActivos.Model();
                Proyecto.Presentation.UsuariosActivos.View usuariosActivosViewDesFar = new Proyecto.Presentation.UsuariosActivos.View();
                Proyecto.Presentation.UsuariosActivos.Controller usuariosActivosControllerDesFar = new Proyecto.Presentation.UsuariosActivos.Controller(usuariosActivosViewDesFar, usuariosActivosModelDesFar);

                JPanel desWithUsersFar = new JPanel(new BorderLayout());
                desWithUsersFar.add(despachoView.getMainPanelDespacho(), BorderLayout.CENTER);
                desWithUsersFar.add(usuariosActivosViewDesFar.getMainPanel(), BorderLayout.EAST);

                tabbedPane.addTab("Despacho", desWithUsersFar);

                tabbedPane.addTab("Dashboard", dashboardViewFar.getMainPanelDashboard());
                tabbedPane.addTab("Acerca de...", acercaDeView.getMainPanelAcercaDe());

                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        usuariosActivosControllerHistoricoFar.stop();
                        usuariosActivosControllerDesFar.stop();
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

