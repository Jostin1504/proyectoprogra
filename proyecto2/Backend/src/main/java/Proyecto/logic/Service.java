package Proyecto.logic;

import Proyecto.data.*;

import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private MedicamentoDao medicamentoDao;
    private PacienteDao pacienteDao;
    private RecetaDao recetaDao;
    private UsuarioDao usuarioDao;

    public Service() {
        try {
            medicamentoDao = new MedicamentoDao();
            pacienteDao = new PacienteDao();
            recetaDao = new RecetaDao();
            usuarioDao = new UsuarioDao();
        } catch (Exception e) {
            System.exit(-1);
        }
    }
    public void stop(){
        try {
            Database.instance().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        Usuario usuarioEncontrado = usuarioDao.read(id);
        if (!usuarioEncontrado.getClave().equals(claveActual)) {
            throw new Exception("La clave actual es incorrecta");
        }
        usuarioEncontrado.setClave(claveNueva);
        usuarioDao.update(usuarioEncontrado);
        return true;
    }

    public Usuario read(Usuario e) throws Exception {
        return usuarioDao.read(e.getCedula());
    }

    public Receta readReceta(int id)throws Exception {
        return recetaDao.read(id);
    }
    // --- GETTERS PARA ACCESO A DATOS ---
    public List<Medico> getMedicos() {
        return usuarioDao.findAllMedicos();
    }
    public List<Farmaceuta> getFarmaceutas() {
        return usuarioDao.findAllFarmaceutas();
    }

    public List<Paciente> getPacientes() {
        return pacienteDao.findAll();
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentoDao.findAll();
    }

    public List<Receta> getRecetas() {
        return recetaDao.findAll();
    }

    // --- METODOS MEDICOS ---
    public Medico buscarMedico(String e) throws Exception {
        return (Medico) usuarioDao.read(e);
    }

    public void anadirMedico(Medico e) throws Exception{
        usuarioDao.create(e);
    }

    public void eliminarMedico(Medico medico) throws Exception {
        usuarioDao.delete(medico);
    }

    // --- METODOS FARMACEUTAS ---
    public Farmaceuta buscarFarmaceuta(String e)throws Exception {
        return (Farmaceuta) usuarioDao.read(e);
    }

    public void anadirFarmaceuta(Farmaceuta e) throws Exception {
        usuarioDao.create(e);
    }

    public void eliminarFarmaceuta(Farmaceuta e) throws Exception{
        usuarioDao.delete(e);
    }

    // --- METODOS PACIENTES ---
    public Paciente buscarPaciente(String e) throws Exception {
        return pacienteDao.read(e);
    }

    public List<Paciente> buscarPacienteNombre(Paciente e) throws Exception {
        return pacienteDao.findByNombre(e.getNombre());
    }

    public List<Paciente> buscarPacienteCedula(Paciente e) throws Exception {
        return pacienteDao.findById(e.getId());
    }



    public void anadirPaciente(Paciente paciente) throws Exception {
        pacienteDao.create(paciente);
    }

    public void eliminarPaciente(Paciente paciente) throws Exception {
        pacienteDao.delete(paciente);
    }

    public void actualizarPaciente(Paciente paciente) throws Exception {
        pacienteDao.update(paciente);
    }

    // --- METODOS MEDICAMENTO ---
    public void anadirMedicamento(Medicamento medicamento) throws Exception {
        medicamentoDao.create(medicamento);
    }

    public Medicamento buscarMedicamento(String e) throws Exception{
        return medicamentoDao.read(e);
    }

    public void agregarReceta(Receta recetas) throws Exception {
        recetaDao.create(recetas);
    }

    public void updateReceta(Receta r, int id) throws Exception {
        recetaDao.update(r, id);
    }

    public void update(Usuario u) throws Exception {
        usuarioDao.update(u);
    }

    public void updateMed(Medicamento u) throws Exception {
        medicamentoDao.update(u);
    }

    public void deleteReceta(int id)  throws Exception {
        recetaDao.delete(id);
    }

    public void delete(Usuario u) throws Exception {
        usuarioDao.delete(u);
    }

    public void deleteMed(Medicamento u) throws Exception {
         medicamentoDao.delete(u);
    }

    public List<Receta> buscarRecetasPorPaciente(String idPaciente) throws Exception {
        return recetaDao.findByPaciente(idPaciente);
    }

    public List<Receta> buscarRecetasPorEstado(String estado) throws Exception {
        return recetaDao.findByEstado(estado);
    }

    public List<Receta> obtenerTodasRecetas() throws Exception {
        return recetaDao.findAll();
    }

    public List<Medicamento> buscarMedNombre(String nombre) throws Exception {
        return medicamentoDao.findByNombre(nombre);
    }

    public Medico buscarMedicoNombre(String nombre) throws Exception {
        return usuarioDao.findMedicoByNombre(nombre);
    }

    public Farmaceuta buscarFarNombre(String nombre) throws Exception {
        return usuarioDao.findFarByNombre(nombre);
    }

    public List<Medicamento> buscarMedCodigo(String codigo) throws Exception {
        return medicamentoDao.findByCodigo(codigo);
    }

    public List<Medicamento> obtenerMedicamentos() throws Exception {
        return medicamentoDao.findAll();
    }
}