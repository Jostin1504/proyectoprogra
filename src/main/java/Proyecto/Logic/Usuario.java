package Proyecto.Logic;

public class Usuario {
    protected String nombre;
    protected String cedula;
    protected String clave;

    public Usuario(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.clave = cedula;
    }

    public Usuario() {
        nombre = "";
        cedula = "";
        clave = "";
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getClave() { return clave; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setClave(String clave) { this.clave = clave; }
}
