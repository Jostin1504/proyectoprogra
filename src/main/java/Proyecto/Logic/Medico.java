package Proyecto.Logic;

public class Medico {
    protected String nombre;
    protected String cedula;

    public Medico(String nombre, String cedula, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
}
