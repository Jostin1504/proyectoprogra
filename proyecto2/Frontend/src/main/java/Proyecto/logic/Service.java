package Proyecto.logic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Service {
    private static Service theInstance;
    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    Socket s;
    ObjectOutputStream os;
    ObjectInputStream is;

    String sid; // Session Id

    public Service() {
        try {
            System.out.println("Intentando conectar al servidor en " + Protocol.SERVER + ":" + Protocol.PORT);
            s = new Socket(Protocol.SERVER, Protocol.PORT);
            System.out.println("Conexión establecida, creando streams...");

            os = new ObjectOutputStream(s.getOutputStream());
            is = new ObjectInputStream(s.getInputStream());

            System.out.println("Enviando solicitud SYNC...");
            os.writeInt(Protocol.SYNC);
            os.flush();

            System.out.println("Esperando Session ID...");
            sid=(String)is.readObject(); // Stores returned Session Id
            System.out.println("Session ID recibido: " + sid);

        } catch (Exception e) {
            System.err.println("Error conectando al servidor: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public synchronized String getSid() {
        return sid;
    }

    // ==================== RECETAS ====================

    public synchronized void agregarReceta(Receta r) throws Exception {
        if (r.getMedicamentos() != null) {
            for (int i = 0; i < r.getMedicamentos().size(); i++) {
                Medicamento m = r.getMedicamentos().get(i);
                System.out.println("  Medicamento " + (i+1) + ": " + m.getNombre() +
                        " (Código: " + m.getCodigo() +
                        ", Cantidad: " + m.getCantidad() +
                        ", Duración: " + m.getDuracion() + " días)");
            }
        }

        os.writeInt(Protocol.RECETA_CREATE);
        os.writeObject(r);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            System.out.println("Receta creada exitosamente");
        } else {
            throw new Exception("Error al crear receta");
        }
    }

    public synchronized Receta leerReceta(int id) throws Exception {
        os.writeInt(Protocol.RECETA_READ);
        os.writeInt(id);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Receta) is.readObject();
        }
        else throw new Exception("Receta no existe");
    }

    public synchronized void actualizarReceta(int id, Receta r) throws Exception {
        os.writeInt(Protocol.RECETA_UPDATE);
        os.writeInt(id);
        os.writeObject(r);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Error al actualizar receta");
    }

    public synchronized List<Usuario> getActiveUsers() throws Exception {
        os.writeInt(Protocol.GET_ACTIVE_USERS);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Usuario>) is.readObject();
        }
        else throw new Exception("Error al obtener usuarios activos");
    }

    public synchronized void eliminarReceta(int id) throws Exception {
        os.writeInt(Protocol.RECETA_DELETE);
        os.writeInt(id);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Error al eliminar receta");
    }

    public synchronized List<Receta> buscarTodasRecetas() throws Exception {
        os.writeInt(Protocol.RECETA_FIND_ALL);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Receta>) is.readObject();
        }
        else throw new Exception("Error al buscar recetas");
    }

    public synchronized List<Receta> buscarRecetasPorEstado(String estado) throws Exception {
        os.writeInt(Protocol.RECETA_FIND_BY_ESTADO);
        os.writeObject(estado);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Receta>) is.readObject();
        }
        else throw new Exception("Error al buscar recetas por estado");
    }

    public synchronized List<Receta> buscarRecetasPorPaciente(String idPaciente) throws Exception {
        os.writeInt(Protocol.RECETA_FIND_BY_PACIENTE);
        os.writeObject(idPaciente);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Receta>) is.readObject();
        }
        else throw new Exception("Error al buscar recetas del paciente");
    }

    // ==================== MEDICAMENTOS ====================

    public synchronized void anadirMedicamento(Medicamento m) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_CREATE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Medicamento ya existe");
    }

    public synchronized Medicamento buscarMedicamento(String codigo) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_READ);
        os.writeObject(codigo);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Medicamento) is.readObject();
        }
        else throw new Exception("Medicamento no existe");
    }

    public synchronized void actualizarMedicamento(Medicamento m) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_UPDATE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Error al actualizar medicamento");
    }

    public synchronized void eliminarMedicamento(Medicamento m) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_DELETE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Error al eliminar medicamento");
    }

    public synchronized List<Medicamento> getMedicamentos() throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_FIND_ALL);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Medicamento>) is.readObject();
        }
        else throw new Exception("Error al buscar medicamentos");
    }

    public synchronized List<Medicamento> buscarMedCodigo(Medicamento m) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_FIND_BY_CODIGO);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Medicamento>) is.readObject();
        }
        else throw new Exception("Error al buscar medicamentos por código");
    }

    public synchronized List<Medicamento> buscarMedNombre(Medicamento m) throws Exception {
        os.writeInt(Protocol.MEDICAMENTO_FIND_BY_NOMBRE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Medicamento>) is.readObject();
        }
        else throw new Exception("Error al buscar medicamentos por nombre");
    }

    // ==================== PACIENTES ====================

    public synchronized void anadirPaciente(Paciente p) throws Exception {
        os.writeInt(Protocol.PACIENTE_CREATE);
        os.writeObject(p);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("Paciente ya existe");
    }

    public synchronized Paciente buscarPaciente(String id) throws Exception {
        os.writeInt(Protocol.PACIENTE_READ);
        os.writeObject(id);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Paciente) is.readObject();
        }
        else throw new Exception("Paciente no existe");
    }

    public synchronized Medico buscarMedico(String id) throws Exception {
        os.writeInt(Protocol.MEDICO_FIND_BY_NOMBRE);
        os.writeObject(id);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Medico) is.readObject();
        }
        else throw new Exception("Paciente no existe");
    }

    public synchronized Farmaceuta buscarFarmaceuta(String id) throws Exception {
        os.writeInt(Protocol.FARMACEUTA_FIND_BY_NOMBRE);
        os.writeObject(id);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Farmaceuta) is.readObject();
        }
        else throw new Exception("Paciente no existe");
    }

    public synchronized void actualizarPaciente(Paciente p) throws Exception {
        os.writeInt(Protocol.PACIENTE_UPDATE);
        os.writeObject(p);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized void eliminarPaciente(Paciente p) throws Exception {
        os.writeInt(Protocol.PACIENTE_DELETE);
        os.writeObject(p);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized List<Paciente> getPacientes() throws Exception {
        os.writeInt(Protocol.PACIENTE_FIND_ALL);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Paciente>) is.readObject();
        }
        else throw new Exception("Error al buscar pacientes");
    }

    public synchronized List<Paciente> buscarPacienteNombre(Paciente filtro) throws Exception {
        os.writeInt(Protocol.PACIENTE_FIND_BY_NOMBRE);
        os.writeObject(filtro);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Paciente>) is.readObject();
        }
        else throw new Exception("Error al buscar pacientes por nombre");
    }

    public synchronized List<Paciente> buscarPacienteCedula(Paciente filtro) throws Exception {
        os.writeInt(Protocol.PACIENTE_FIND_BY_ID);
        os.writeObject(filtro);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Paciente>) is.readObject();
        }
        else throw new Exception("Error al buscar pacientes por ID");
    }

    // ==================== USUARIOS / LOGIN ====================

    public synchronized Usuario login(Usuario u) throws Exception {
        os.writeInt(Protocol.USUARIO_LOGIN);
        os.writeObject(u);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (Usuario) is.readObject();
        }
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized Usuario read(Usuario u) throws Exception {
        System.out.println("Intentando leer usuario: " + u.getCedula());
        os.writeInt(Protocol.USUARIO_READ);
        os.writeObject(u);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            Usuario usuario = (Usuario) is.readObject();
            System.out.println("Usuario leído: " + usuario.getNombre());
            return usuario;
        }
        else {
            String errorMsg = (String) is.readObject();
            System.out.println("Error al leer usuario: " + errorMsg);
            throw new Exception(errorMsg);
        }
    }

    public synchronized boolean cambiarClave(String cedula, String claveActual, String claveNueva) throws Exception {
        os.writeInt(Protocol.USUARIO_CAMBIAR_CLAVE);
        os.writeObject(cedula);
        os.writeObject(claveActual);
        os.writeObject(claveNueva);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
        return true;
    }

    // ==================== MÉDICOS ====================

    public synchronized void anadirMedico(Medico m) throws Exception {
        os.writeInt(Protocol.MEDICO_CREATE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized void actualizarMedico(Usuario m) throws Exception {
        os.writeInt(Protocol.MEDICO_UPDATE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized void eliminarMedico(Medico m) throws Exception {
        os.writeInt(Protocol.MEDICO_DELETE);
        os.writeObject(m);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized List<Medico> getMedicos() throws Exception {
        os.writeInt(Protocol.MEDICO_FIND_ALL);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Medico>) is.readObject();
        }
        else throw new Exception("Error al buscar médicos");
    }

    // ==================== FARMACEUTAS ====================

    public synchronized void anadirFarmaceuta(Farmaceuta f) throws Exception {
        os.writeInt(Protocol.FARMACEUTA_CREATE);
        os.writeObject(f);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized void eliminarFarmaceuta(Farmaceuta f) throws Exception {
        os.writeInt(Protocol.FARMACEUTA_DELETE);
        os.writeObject(f);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else {
            String errorMsg = (String) is.readObject();
            throw new Exception(errorMsg);
        }
    }

    public synchronized List<Farmaceuta> getFarmaceutas() throws Exception {
        os.writeInt(Protocol.FARMACEUTA_FIND_ALL);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Farmaceuta>) is.readObject();
        }
        else throw new Exception("Error al buscar farmaceutas");
    }

    // ==================== DESCONEXIÓN ====================

    private synchronized void disconnect() throws Exception {
        os.writeInt(Protocol.DISCONNECT);
        os.flush();
        s.shutdownOutput();
        s.close();
    }

    public synchronized void stop() {
        try {
            disconnect();
        } catch (Exception e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }
}
