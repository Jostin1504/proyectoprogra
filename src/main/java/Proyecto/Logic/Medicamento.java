package Proyecto.Logic;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "medicamento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicamento {
    @XmlID
    protected String codigo;
    @XmlElement
    protected String nombre;
    @XmlElement
    protected String presentacion;

    protected String indicaciones;
    protected int duracion;
    protected int cantidad;

    public Medicamento(String nombre, String presentacion,  int cantidad, int duracion,  String indicaciones) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        codigo = nombre.substring(0, 2).toUpperCase() + Integer.toString(cantidad);
        this.duracion = duracion;
        this.indicaciones = indicaciones;
        this.cantidad = cantidad;
    }

    public Medicamento() {
        codigo = "";
        nombre = "";
        presentacion = "";
        indicaciones = "";
        duracion = 0;
        cantidad = 0;
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
    public String getIndicaciones() {return indicaciones;}
    public int getDuracion() {return duracion;}

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public void setIndicaciones(String indicaciones) {this.indicaciones = indicaciones;}
    public void setDuracion(int duracion) {this.duracion = duracion;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
}
