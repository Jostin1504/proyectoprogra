package Proyecto.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Worker {
    Server srv;
    Socket s;
    ObjectOutputStream os;
    ObjectInputStream is;
    Service service;

    String sid; // Session Id
    Socket as; // Asynchronous Socket
    ObjectOutputStream aos;
    ObjectInputStream ais;

    public Worker(Server srv, Socket s, ObjectOutputStream os, ObjectInputStream is, String sid, Service service) {
        this.srv = srv;
        this.s = s;
        this.os = os;
        this.is = is;
        this.service = service;
        this.sid = sid;
    }
    public void setAs(Socket as, ObjectOutputStream aos, ObjectInputStream ais) {
        this.as = as;
        this.aos = aos;
        this.ais = ais;
    }

    boolean continuar;

    public void start() {
        try {
            System.out.println("Worker atendiendo peticiones...");
            Thread t = new Thread(new Runnable() {
                public void run() {
                    listen();
                }
            });
            continuar = true;
            t.start();
        } catch (Exception ex) {
        }
    }

    public void stop() {
        continuar = false;
        srv.removeActiveUser(sid);
        System.out.println("Conexion cerrada...");
    }

    public void listen() {
        int method;
        while (continuar) {
            try {
                method = is.readInt();
                System.out.println("Operacion: " + method);
                switch (method) {
                    case Protocol.RECETA_CREATE:
                        try {
                            Receta recetaRecibida = (Receta) is.readObject();
                            service.agregarReceta(recetaRecibida);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Receta creada");
                        } catch (Exception ex) {
                            System.err.println("WORKER ERROR: " + ex.getMessage());
                            ex.printStackTrace();
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_READ:
                        try {
                            int id = is.readInt();
                            Receta r = service.readReceta(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(r);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_UPDATE:
                        try {
                            int id = is.readInt();
                            Receta r = (Receta) is.readObject();
                            service.updateReceta(r, id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            System.err.println("Error al actualizar receta: " + ex.getMessage());
                            ex.printStackTrace();
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.GET_ACTIVE_USERS:
                        try {
                            List<Usuario> activeUsers = srv.getActiveUsers();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(activeUsers);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_DELETE:
                        try {
                            int id = is.readInt();
                            service.deleteReceta(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_FIND_ALL:
                        try {
                            List<Receta> le = service.obtenerTodasRecetas();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_FIND_BY_ESTADO:
                        try {
                            String estado = (String) is.readObject();
                            List<Receta> le = service.buscarRecetasPorEstado(estado);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.RECETA_FIND_BY_PACIENTE:
                        try{
                            String ced = (String) is.readObject();
                            List<Receta> le = service.buscarRecetasPorPaciente(ced);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_CREATE:
                        try {
                            service.anadirMedicamento((Medicamento) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this,"Medicamento creado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_READ:
                        try {
                            String id = (String) is.readObject();
                            Medicamento r = service.buscarMedicamento(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(r);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_UPDATE:
                        try {
                            service.updateMed((Medicamento) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_DELETE:
                        try {
                            service.deleteMed((Medicamento) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_FIND_ALL:
                        try {
                            List<Medicamento> le = service.obtenerMedicamentos();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_FIND_BY_CODIGO:
                        try {
                            String codigo = (String) is.readObject();
                            List<Medicamento> le = service.buscarMedCodigo(codigo);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.MEDICAMENTO_FIND_BY_NOMBRE:
                        try {
                            String Nombre = (String) is.readObject();
                            List<Medicamento> le = service.buscarMedNombre(Nombre);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(le);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.PACIENTE_CREATE:
                        try {
                            service.anadirPaciente((Paciente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this,"Paciente creado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.PACIENTE_READ:
                        try {
                            String id = (String) is.readObject();
                            Paciente r = service.buscarPaciente(id);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(r);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.PACIENTE_UPDATE:
                        try {
                            service.actualizarPaciente((Paciente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Paciente actualizado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.PACIENTE_DELETE:
                        try {
                            service.eliminarPaciente((Paciente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Paciente eliminado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.PACIENTE_FIND_BY_NOMBRE:
                        try {
                            Paciente filter = (Paciente) is.readObject();
                            List<Paciente> pacientes = service.buscarPacienteNombre(filter);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(pacientes);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;

                    case Protocol.PACIENTE_FIND_BY_ID:
                        try {
                            Paciente filter = (Paciente) is.readObject();
                            List<Paciente> pacientes = service.buscarPacienteCedula(filter);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(pacientes);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;

                    case Protocol.PACIENTE_FIND_ALL:
                        try {
                            List<Paciente> pacientes = service.getPacientes();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(pacientes);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.USUARIO_LOGIN:
                        try {
                            Usuario u = (Usuario) is.readObject();
                            Usuario resultado = service.read(u);

                            if (resultado != null && resultado.getClave().equals(u.getClave())) {
                                os.writeInt(Protocol.ERROR_NO_ERROR);
                                os.writeObject(resultado);
                                srv.addActiveUser(sid, resultado);
                            } else {
                                os.writeInt(Protocol.ERROR_ERROR);
                                os.writeObject("Usuario o contraseña incorrectos");
                            }
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject("Usuario no existe");
                        }
                        break;

                    case Protocol.USUARIO_READ:
                        try {
                            Usuario u = (Usuario) is.readObject();
                            Usuario resultado = service.read(u);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(resultado);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.USUARIO_CAMBIAR_CLAVE:
                        try {
                            String cedula = (String) is.readObject();
                            String claveActual = (String) is.readObject();
                            String claveNueva = (String) is.readObject();

                            service.cambiarClave(cedula, claveActual, claveNueva);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;
                    case Protocol.MEDICO_CREATE:
                        try {
                            service.anadirMedico((Medico) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Médico creado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.MEDICO_UPDATE:
                        try {
                            service.update((Usuario) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Médico actualizado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.MEDICO_DELETE:
                        try {
                            service.eliminarMedico((Medico) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Médico eliminado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.MEDICO_FIND_ALL:
                        try {
                            List<Medico> medicos = service.getMedicos();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(medicos);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.FARMACEUTA_CREATE:
                        try {
                            service.anadirFarmaceuta((Farmaceuta) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Farmaceuta creado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.FARMACEUTA_DELETE:
                        try {
                            service.eliminarFarmaceuta((Farmaceuta) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            srv.deliver_message(this, "Farmaceuta eliminado");
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                            os.writeObject(ex.getMessage());
                        }
                        break;

                    case Protocol.FARMACEUTA_FIND_ALL:
                        try {
                            List<Farmaceuta> farmaceutas = service.getFarmaceutas();
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(farmaceutas);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.DISCONNECT:
                        stop();
                        srv.remove(this);
                        break;
                    case Protocol.MEDICO_FIND_BY_NOMBRE:
                        try {
                            String filter = (String) is.readObject();
                            Medico medico = service.buscarMedicoNombre(filter);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(medico);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                    case Protocol.FARMACEUTA_FIND_BY_NOMBRE:
                        try {
                            String filter = (String) is.readObject();
                            Farmaceuta medico = service.buscarFarNombre(filter);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(medico);
                        } catch (Exception ex) {
                            os.writeInt(Protocol.ERROR_ERROR);
                        }
                        break;
                }
                os.flush();
            } catch (IOException e) {
                stop();
            }
        }
    }

    public synchronized void deliver_message(String message) {
        if (as != null) {
            try {
                aos.writeInt(Protocol.DELIVER_MESSAGE);
                aos.writeObject(message);
                aos.flush();
            } catch (Exception e) {
            }
        }
    }

}
