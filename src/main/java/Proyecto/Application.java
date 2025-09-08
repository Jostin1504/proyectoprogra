package Proyecto;

import Proyecto.Presentation.Hospital.Login.*;
import Proyecto.Presentation.Hospital.Medico.*;

import javax.swing.*;
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

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Sistema de prescripciones");
        window.setContentPane(loginView.getLogPanel());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}
