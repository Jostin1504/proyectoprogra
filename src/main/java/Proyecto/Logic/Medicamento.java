package Proyecto.Logic;

public class Medicamento {
    protected String codigo;
    protected String nombre;
    protected String presentacion;
    protected int cantidad;
    protected int duracion;
    protected String indicaciones;
    protected String estado;

    public Medicamento(String nombre, String presentacion,  int cantidad, int duracion,  String indicaciones) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.duracion = duracion;
        this.indicaciones = indicaciones;
        codigo = nombre.substring(0, 2).toUpperCase() + Integer.toString(cantidad);
        estado = "";
    }

    public Medicamento() {
        codigo = "";
        nombre = "";
        presentacion = "";
        cantidad = 0;
        duracion = 0;
        indicaciones = "";
        estado = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
