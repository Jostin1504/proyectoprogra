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



    public boolean cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        try {
            Usuario us = new Usuario("", id, "");
            Usuario usuarioEncontrado = read(us);
            if (!usuarioEncontrado.getClave().equals(claveActual)) {
                throw new Exception("La clave actual es incorrecta");
            }
            usuarioEncontrado.setClave(claveNueva);
            Usuario verificacion = read(us);
            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    public Usuario read(Usuario e) throws Exception {
        Usuario result = datos.getUsuarios().stream()
                .filter(i -> i.getCedula().equals(e.getCedula()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Usuario no existe");
        }
    }

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
    public Medico buscarMedico(String e) throws  Exception {
        Medico result = datos.getMedicos().stream()
                .filter(i -> i.getCedula().equals(e))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Usuario no existe");
        }
    }
    public void anadirMedico(Medico e) throws Exception{
        Medico result = datos.getMedicos().stream()
                .filter(i -> i.getCedula().equals(e.getCedula()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            datos.getMedicos().add(e);
        } else {
            throw new Exception("Medico ya existe");
        }
    }
    public boolean eliminarMedico(Medico medico){
        boolean aux = false;
        if(datos.getMedicos().remove(medico)){
            aux = true;
        }
        return aux;
    }

    // --- METODOS FARMACEUTAS ---
    public Farmaceuta buscarFarmaceuta(String e)throws Exception {
        Farmaceuta result = datos.getFarmaceutas().stream()
                .filter(i -> i.getCedula().equals(e))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Usuario no existe");
        }
    }
    public void anadirFarmaceuta(Farmaceuta farmaceuta){
        datos.getFarmaceutas().add(farmaceuta);
    }
    public void eliminarFarmaceuta(Farmaceuta farmaceuta){
        datos.getFarmaceutas().remove(farmaceuta);
    }

    // --- METODOS PACIENTES ---
    public Paciente buscarPaciente(String e) throws Exception {
        Paciente result = datos.getPacientes().stream()
                .filter(i -> i.getId().equals(e))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Usuario no existe");
        }
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

    public void eliminarMedicamento(Medicamento medicamento){
        datos.getMedicamentos().remove(medicamento);
    }
    public Medicamento buscarMedicamento(String nom){
        for(int i = 0; i < getMedicamentos().size(); i++){
            if (getMedicamentos().get(i).getNombre().equals(nom)){
                return getMedicamentos().get(i);
            }
        }
        return null;
    }

    public Number getCantidadTotalMedicamento(Medicamento medicamento){
        int cont = 0;
        for (int i = 0; i < getMedicamentos().size(); i++){
            if (getMedicamentos().get(i).getNombre().equals(medicamento.getNombre())){
                cont = cont + getMedicamentos().get(i).getCantidad();
            }
        }
        return cont;
    }

    public void anadirAdministrador(Administrador administrador){
        datos.getAdministradores().add(administrador);
    }

    public String ent(){
        return "entrar";
    }
}