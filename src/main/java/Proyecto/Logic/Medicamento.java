package Proyecto.Logic;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "medicamento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicamento {
    @XmlElement
    protected String codigo;
    @XmlElement
    protected String nombre;
    @XmlElement
    protected String presentacion;

    public Medicamento(String nombre, String presentacion,  int cantidad, int duracion,  String indicaciones) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        codigo = nombre.substring(0, 2).toUpperCase() + Integer.toString(cantidad);
    }

    public Medicamento() {
        codigo = "";
        nombre = "";
        presentacion = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }


    public String getPresentacion() {
        return presentacion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

}
