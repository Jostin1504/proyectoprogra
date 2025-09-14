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
    public Paciente paciente;
    @XmlElement
    public List<Medicamento> medicamentos;

    public Recetas(String fecha_final, Paciente p, String fecha_inicio) {
        this.fechaRetiro = fecha_final;
        this.paciente = p;
        this.medicamentos = new ArrayList<>();
    }

    public Recetas() {
        fechaRetiro = "";
        paciente = new Paciente();
        medicamentos = new ArrayList<>();
    }

    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    public List<Medicamento> getMedicamentos() {return medicamentos;}
    public void setMedicamentos(List<Medicamento> medicamentos) {this.medicamentos = medicamentos;}
    public void añadirMed(Medicamento med){medicamentos.add(med);}
    public String getFechaRetiro() {return fechaRetiro;}
    public void setFechaRetiro(String fechaRetiro) {this.fechaRetiro = fechaRetiro;}
}
