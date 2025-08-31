package Proyecto.Logic;

public class Medico {
    protected String nombre;
    protected String cedula;
    protected String clave;
    //incluir la clase especialidad/departamento

    public Medico(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.clave = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
