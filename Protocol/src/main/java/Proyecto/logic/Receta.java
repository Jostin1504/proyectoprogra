package Proyecto.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receta implements Serializable {
    public String fechaRetiro;
    public String fechaCreacion;
    public String idPaciente;
    public String estado;

    public List<Medicamento> medicamentos;

    public Receta(String fechaRetiro, String idPaciente, String fechaCreacion) {
        this.fechaRetiro = fechaRetiro;
        this.idPaciente = idPaciente;
        this.fechaCreacion = fechaCreacion;
        this.medicamentos = new ArrayList<>();
        this.estado = "Confeccionada";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Receta() {
        fechaRetiro = "";
        fechaCreacion = "";
        idPaciente = "";
        medicamentos = new ArrayList<>();
        estado = "Confeccionada";
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void añadirMed(Medicamento med) {
        if (this.medicamentos == null) {
            this.medicamentos = new ArrayList<>();
        }
        this.medicamentos.add(med);
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
