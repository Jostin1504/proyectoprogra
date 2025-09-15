package Proyecto.Logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "recetas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recetas {
    public String fechaRetiro;
    @XmlElementWrapper(name = "medicamentos")
    @XmlElement(name = "medicamento")
    public List<Medicamento> medicamentos;
    @XmlID
    public String estado;

    public Recetas(String fecha_final) {
        this.fechaRetiro = fecha_final;
        this.medicamentos = new ArrayList<>();
        this.estado = "En proceso";
    }

    public Recetas() {
        fechaRetiro = "";
        medicamentos = new ArrayList<>();
        estado = "En proceso";
    }

    public List<Medicamento> getMedicamentos() {return medicamentos;}
    public void setMedicamentos(List<Medicamento> medicamentos) {this.medicamentos = medicamentos;}
    public void agregarMedicamento(Medicamento medicamento) {medicamentos.add(medicamento);}
    public String getFechaRetiro() {return fechaRetiro;}
    public void setFechaRetiro(String fechaRetiro) {this.fechaRetiro = fechaRetiro;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
