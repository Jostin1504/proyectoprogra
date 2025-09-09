package Proyecto.Logic;

public class Medicamento {
    protected String codigo;
    protected String nombre;
    protected int presentacion; //cantidad

    public Medicamento(String codigo, String nombre, int presentacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.presentacion = presentacion;
    }

    public Medicamento() {
        codigo = "";
        nombre = "";
        presentacion = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPresentacion() {
        return presentacion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPresentacion(int presentacion) {
        this.presentacion = presentacion;
    }
}
