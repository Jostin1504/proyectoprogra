package Proyecto.Logic;

import Proyecto.Data.data;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private data datos;

    public Service() {
        datos = new data();
    }

    public data getDatos() {
        return datos;
    }

    // --- MÉTODOS PARA LOGIN ---
    public Usuario validarCredenciales(String id, String clave) {
        List<Usuario> todosUsuarios = obtenerTodosLosUsuarios();

        for (Usuario usuario : todosUsuarios) {
            if (usuario.getCedula().equals(id) && usuario.getClave().equals(clave)) {
                return usuario;
            }
        }
        return null; // No encontrado
    }

    public String getTipoUsuario(Usuario usuario) {
        if (usuario instanceof Medico) {
            return "MEDICO";
        } else if (usuario instanceof Farmaceuta) {
            return "FARMACEUTA";
        } else if (usuario instanceof Administrador) {
            return "ADMINISTRADOR";
        }
        return "DESCONOCIDO";
    }

    public boolean cambiarClave(String id, String claveActual, String claveNueva) {
        Usuario usuario = validarCredenciales(id, claveActual);
        if (usuario != null) {
            usuario.setClave(claveNueva);
            return true;
        }
        return false;
    }

    // --- MÉTODOS AUXILIARES ---
    private List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> todosUsuarios = new ArrayList<>();
        todosUsuarios.addAll(datos.getMedicos());
        todosUsuarios.addAll(datos.getFarmaceutas());
        todosUsuarios.addAll(datos.getAdministradores());
        return todosUsuarios;
    }

    // --- GETTERS PARA ACCESO A DATOS ---
    public List<Medico> getMedicos() {
        return datos.getMedicos();
    }

    public List<Farmaceuta> getFarmaceutas() {
        return datos.getFarmaceutas();
    }

    public List<Administrador> getAdministradores() {
        return datos.getAdministradores();
    }

    public List<Paciente> getPacientes() {
        return datos.getPacientes();
    }

    public List<Medicamento> getMedicamentos() {
        return datos.getMedicamentos();
    }

    // --- METODOS MEDICOS ---

    //encontrar medicos en data
    public Medico buscarMedico(String nom){
        for(int i = 0; i < getMedicos().size(); i++){
            if (getMedicos().get(i).getNombre().equals(nom)){
                return getMedicos().get(i);
            }
        }
        return null;
    }
    public void anadirMedico(Medico medico){
        datos.getMedicos().add(medico);
    }
    public void eliminarMedico(Medico medico){
        datos.getMedicos().remove(medico);
    }

    // --- METODOS FARMACEUTAS ---
    public Farmaceuta buscarFarmaceuta(String nom){
        for(int i = 0; i < getFarmaceutas().size(); i++){
            if (getFarmaceutas().get(i).getNombre().equals(nom)){
                return getFarmaceutas().get(i);
            }
        }
        return null;
    }
    public void anadirFarmaceuta(Farmaceuta farmaceuta){
        datos.getFarmaceutas().add(farmaceuta);
    }
    public void eliminarFarmaceuta(Farmaceuta farmaceuta){
        datos.getFarmaceutas().remove(farmaceuta);
    }

    // --- METODOS PACIENTES ---
    public Paciente buscarPaciente(String nom){
        for(int i = 0; i < getPacientes().size(); i++){
            if (getPacientes().get(i).getNombre().equals(nom)){
                return getPacientes().get(i);
            }
        }
        return null;
    }
    public void anadirPaciente(Paciente paciente){
        datos.getPacientes().add(paciente);
    }
    public void eliminarPaciente(Paciente paciente){
        datos.getPacientes().remove(paciente);
    }

    // --- METODOS MEDICAMENTO ---
    public void anadirMedicamento(Medicamento medicamento){
        datos.getMedicamentos().add(medicamento);
    }

    public void anadirAdministrador(Administrador administrador){
        datos.getAdministradores().add(administrador);
    }
}