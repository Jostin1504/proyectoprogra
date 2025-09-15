package Proyecto.Logic;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "receta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recetas {
    @XmlElement
    public String fechaRetiro;

    @XmlElement
    public String fechaCreacion;

    @XmlElement
    public String idPaciente;

    @XmlElement
    public String estado;

    @XmlElementWrapper(name = "medicamentos")
    @XmlElement(name = "medicamento")
    public List<Medicamento> medicamentos;

    public Recetas(String fechaRetiro, String idPaciente, String fechaCreacion) {
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

    public Recetas() {
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
