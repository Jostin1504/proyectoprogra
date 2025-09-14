package Proyecto.Logic;

import Proyecto.Logic.Usuario;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "medico")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico extends Usuario {
    @XmlElement
    private String especialidad;


    public Medico(String nombre, String cedula, String rol, String especialidad) {
        super(nombre, cedula, rol);
        this.especialidad = especialidad;
    }

    public Medico() {
        super();
        especialidad = "";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
