package Proyecto.Presentation.Despacho;


import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel mainPanelDespacho;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case :

                break;
            case :

                break;
        }
        this.mainPanelDespacho.revalidate();
    }

    public JPanel getMainPanelDespacho() {
        return mainPanelDespacho;
    }
}
