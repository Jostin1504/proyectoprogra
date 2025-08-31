package Proyecto.Logic;

public class Paciente {
    protected String nombre;
    protected String id;
    protected String fechanac;
    protected String numTelefono;

    public Paciente(String nombre, String id, String  fechanac, String numTelefono) {
        this.nombre = nombre;
        this.id = id;
        this.fechanac = fechanac;
        this.numTelefono = numTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getFechanac() {
        return fechanac;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }
}
