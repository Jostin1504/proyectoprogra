package Proyecto.Logic;
import java.util.ArrayList;
import java.util.List;

public class Recetas {
    public String fechaRetiro;
    public Paciente paciente;
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
    public String getFechaRetiro() {return fechaRetiro;}
    public void setFechaRetiro(String fechaRetiro) {this.fechaRetiro = fechaRetiro;}
}
