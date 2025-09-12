package Proyecto.Logic;

public class Recetas {
    public String fecha_inicio;
    public String fecha_final;
    public String nombreDeMedicamento;
    public String estado;

    public Recetas(String fecha_final, String fecha_inicio, String nombreDeMedicamento, String estado) {
        this.fecha_final = fecha_final;
        this.fecha_inicio = fecha_inicio;
        this.nombreDeMedicamento = nombreDeMedicamento;
        this.estado = estado;
    }

    public Recetas() {
        fecha_final = "";
        fecha_inicio = "";
        nombreDeMedicamento = "";
        estado = "";
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getNombreDeMedicamento() {
        return nombreDeMedicamento;
    }

    public void setNombreDeMedicamento(String nombreDeMedicamento) {
        this.nombreDeMedicamento = nombreDeMedicamento;
    }
}
