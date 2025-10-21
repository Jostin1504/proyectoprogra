package Proyecto.Logic;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


public class Paciente {
    protected String nombre;
    protected String id;
    protected String fechanac;
    protected String numTelefono;
    protected List<Receta> recetas;

    public Paciente(String nombre, String id, String fechanac, String numTelefono) {
        this.nombre = nombre;
        this.id = id;
        this.fechanac = fechanac;
        this.numTelefono = numTelefono;
        this.recetas = new ArrayList<>();
    }

    public Paciente() {
        nombre = "";
        id = "";
        fechanac = "";
        numTelefono = "";
        recetas = new ArrayList<>();
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

    public List<Receta> getRecetas() {
        return recetas;
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

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public void agregarReceta(Receta receta) {
        if (this.recetas == null) {
            this.recetas = new ArrayList<>();
        }
        this.recetas.add(receta);
    }

    public void eliminarReceta(Receta receta) {
        if (this.recetas != null) {
            this.recetas.remove(receta);
        }
    }
}
