package Proyecto.Data;

import Proyecto.Logic.*;
import java.util.ArrayList;
import java.util.List;

public class data {
    private final List<Medico> medicos;
    private final List<Farmaceuta> farmaceutas;
    private final List<Administrador> administradores;
    private final List<Paciente> pacientes;
    private final List<Medicamento> medicamentos;
    private final List<Usuario> usuarios;

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

        // Medicamentos
        medicamentos.add(new Medicamento("Acetaminofén", "500 mg", 70, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Acetaminofén", "100 mg", 80, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Ibuprofeno", "400 mg", 40, 15, "Una cada 6 horas"));
        medicamentos.add(new Medicamento("Amoxicilina", "250 mg", 30, 15, "Una cada 6 horas"));

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
