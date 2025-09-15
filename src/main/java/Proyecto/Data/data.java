package Proyecto.Data;

import Proyecto.Logic.*;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class data {
    @XmlElementWrapper(name = "medicos")
    @XmlElement(name = "medico")
    private List<Medico> medicos;

    @XmlElementWrapper(name = "farmaceutas")
    @XmlElement(name = "farmaceuta")
    private List<Farmaceuta> farmaceutas;

    private List<Administrador> administradores;

    @XmlElementWrapper(name = "pacientes")
    @XmlElement(name = "paciente")
    private List<Paciente> pacientes;

    @XmlElementWrapper(name = "medicamentos")
    @XmlElement(name = "medicamento")
    private List<Medicamento> medicamentos;

    @XmlTransient
    private List<Usuario> usuarios;

    public data() {
        medicos = new ArrayList<>();
        farmaceutas = new ArrayList<>();
        administradores = new ArrayList<>();
        pacientes = new ArrayList<>();
        medicamentos = new ArrayList<>();
        usuarios = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        // Médicos
        medicos.add(new Medico("Juan Pérez","111","MED", "Cardiología"));
        medicos.add(new Medico("Dra. María González", "112","MED", "Pediatría"));
        medicos.add(new Medico("Dr. Santiago Rodríguez", "113","MED", "Medicina General"));

        // Farmaceutas
        farmaceutas.add(new Farmaceuta("Ana García", "222", "FAR"));
        farmaceutas.add(new Farmaceuta("Luis Martínez", "223", "FAR"));

        // Administradores
        administradores.add(new Administrador("Carlos López", "333", "ADM"));
        administradores.add(new Administrador("Patricia Jiménez", "334", "ADM"));

        // Pacientes
        pacientes.add(new Paciente("José Vargas", "101", "1980-05-15", "8888-1111"));
        pacientes.add(new Paciente("Carmen Solís", "102", "1990-12-03", "8888-2222"));
        pacientes.add(new Paciente("Roberto Mora", "103", "1975-08-20", "8888-3333"));
        Paciente paciente1 = new Paciente("José Vargas", "101", "1980-05-15", "8888-1111");
        Paciente paciente2 = new Paciente("Carmen Solís", "102", "1990-12-03", "8888-2222");
        Paciente paciente3 = new Paciente("Roberto Mora", "103", "1975-08-20", "8888-3333");

        // Medicamentos
        medicamentos.add(new Medicamento("Acetaminofén", "500 mg", 70, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Acetaminofén", "100 mg", 80, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Ibuprofeno", "400 mg", 40, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Amoxicilina", "250 mg", 30, 15, "Una cada 6 horas"));

        Recetas receta1 = new Recetas("2024-01-15", "101", "2024-01-10");
        receta1.añadirMed(new Medicamento("Acetaminofén", "500 mg", 30, 7, "Una cada 8 horas"));
        receta1.añadirMed(new Medicamento("Ibuprofeno", "400 mg", 20, 5, "Una cada 12 horas si hay dolor"));
        paciente1.agregarReceta(receta1);

        usuarios.addAll(medicos);
        usuarios.addAll(farmaceutas);
        usuarios.addAll(administradores);
    }

    public List<Medico> getMedicos() {return medicos;}

    public List<Farmaceuta> getFarmaceutas() {
        return farmaceutas;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public List<Usuario> getUsuarios() {return usuarios;}
}
