package Proyecto.logic;

import java.io.Serializable;

public class Usuario implements Serializable {
    protected String nombre;
    protected String cedula;
    protected String clave;
    protected String rol;

    public Usuario(String nombre, String cedula, String rol) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.clave = cedula;
        this.rol = rol;
    }

    public Usuario() {
        nombre = "";
        cedula = "";
        clave = "";
        rol = "";
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getClave() { return clave; }
    public String getRol() { return rol; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setClave(String clave) { this.clave = clave; }
    public void setRol(String rol) { this.rol = rol; }

}
