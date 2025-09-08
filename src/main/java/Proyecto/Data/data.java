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

    public data() {
        medicos = new ArrayList<>();
        farmaceutas = new ArrayList<>();
        administradores = new ArrayList<>();
        pacientes = new ArrayList<>();
        medicamentos = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        // Médicos
        medicos.add(new Medico("Dr. Juan Pérez", "111", "Cardiología"));
        medicos.add(new Medico("Dra. María González", "112", "Pediatría"));
        medicos.add(new Medico("Dr. Santiago Rodríguez", "113", "Medicina General"));

        // Farmaceutas
        farmaceutas.add(new Farmaceuta("Ana García", "222"));
        farmaceutas.add(new Farmaceuta("Luis Martínez", "223"));

        // Administradores
        administradores.add(new Administrador("Carlos López", "333"));
        administradores.add(new Administrador("Patricia Jiménez", "334"));

        // Pacientes
        pacientes.add(new Paciente("José Vargas", "101", "1980-05-15", "8888-1111"));
        pacientes.add(new Paciente("Carmen Solís", "102", "1990-12-03", "8888-2222"));
        pacientes.add(new Paciente("Roberto Mora", "103", "1975-08-20", "8888-3333"));

        // Medicamentos
        medicamentos.add(new Medicamento("ACE001", "Acetaminofén", 500));
        medicamentos.add(new Medicamento("ACE002", "Acetaminofén", 100));
        medicamentos.add(new Medicamento("IBU001", "Ibuprofeno", 400));
        medicamentos.add(new Medicamento("AMX001", "Amoxicilina", 250));
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

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

}
