package Proyecto.Logic;

import Proyecto.Data.data;
import jakarta.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private data datos;

    public Service() {
        cargarDatos();
    }

    public data getDatos() {
        return datos;
    }

    private void cargarDatos() {
        try {
            File file = new File("data.xml");
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(data.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                datos = (data) unmarshaller.unmarshal(file);
            } else {
                datos = new data();
            }

        } catch (Exception e) {
            e.printStackTrace();
            datos = new data();
        }
    }

    public void guardarDatos() {
        try {
            jakarta.xml.bind.JAXBContext context = jakarta.xml.bind.JAXBContext.newInstance(data.class);
            jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(datos, new File("data.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public List<Recetas> getRecetas() {return datos.getRecetas(); }

    // --- METODOS MEDICOS ---
    public Medico buscarMedico(String e) throws Exception {
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
            guardarDatos();
        } else {
            throw new Exception("Medico ya existe");
        }
    }

    public boolean eliminarMedico(Medico medico){
        boolean aux = false;
        if(datos.getMedicos().remove(medico)){
            aux = true;
            guardarDatos();
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
        guardarDatos();
    }

    public void eliminarFarmaceuta(Farmaceuta farmaceuta){
        datos.getFarmaceutas().remove(farmaceuta);
        guardarDatos();
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
            throw new Exception("Paciente no existe");
        }
    }

    public List<Paciente> buscarPacienteNombre(Paciente e) throws Exception {
        return datos.getPacientes().stream()
                .filter(i -> i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Paciente::getNombre))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarMedNombre(Medicamento e) throws Exception {
        return datos.getMedicamentos().stream()
                .filter(i -> i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Medicamento::getNombre))
                .collect(Collectors.toList());
    }

    public List<Paciente> buscarPacienteCedula(Paciente e) throws Exception {
        return datos.getPacientes().stream()
                .filter(i -> i.getId().toLowerCase().contains(e.getId().toLowerCase()))
                .sorted(Comparator.comparing(Paciente::getId))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarMedCodigo(Medicamento e) throws Exception {
        return datos.getMedicamentos().stream()
                .filter(i -> i.getCodigo().toLowerCase().contains(e.getCodigo().toLowerCase()))
                .sorted(Comparator.comparing(Medicamento::getCodigo))
                .collect(Collectors.toList());
    }

    public void anadirPaciente(Paciente paciente){
        datos.getPacientes().add(paciente);
        guardarDatos();
    }

    public void eliminarPaciente(Paciente paciente){
        datos.getPacientes().remove(paciente);
        guardarDatos();
    }

    public void actualizarPaciente(Paciente paciente) throws Exception {
        Paciente e = buscarPaciente(paciente.getId());
        if (e != null) {
            e.setNombre(paciente.getNombre());
            e.setFechanac(paciente.getFechanac());
            e.setNumTelefono(paciente.getNumTelefono());
            e.setRecetas(paciente.getRecetas());
            guardarDatos();
        } else {
            throw new Exception("Paciente no existe");
        }
    }

    // --- METODOS MEDICAMENTO ---
    public void anadirMedicamento(Medicamento medicamento){
        datos.getMedicamentos().add(medicamento);
        guardarDatos();
    }

    public void eliminarMedicamento(Medicamento medicamento){
        datos.getMedicamentos().remove(medicamento);
        guardarDatos();
    }

    public Medicamento buscarMedicamento(String e) throws Exception{
        Medicamento result = datos.getMedicamentos().stream()
                .filter(i -> i.getCodigo().equals(e))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public void anadirAdministrador(Administrador administrador){
        datos.getAdministradores().add(administrador);
        guardarDatos();
    }

    // --- METODOS PARA RECETAS ---
    public List<Recetas> getRecetasPaciente(String idPaciente) throws Exception {
        Paciente paciente = buscarPaciente(idPaciente);
        return paciente.getRecetas();
    }

    public List<Recetas> getAllRecetas() {
        List<Recetas> todasLasRecetas = new ArrayList<>();
        for (Paciente paciente : datos.getPacientes()) {
            todasLasRecetas.addAll(paciente.getRecetas());
        }
        return todasLasRecetas;
    }

    public String ent(){
        return "entrar";
    }
}