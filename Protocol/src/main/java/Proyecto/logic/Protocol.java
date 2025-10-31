package Proyecto.logic;

public class Protocol {
    public static final String SERVER = "localhost";
    public static final int PORT = 1234;

    // Recetas
    public static final int RECETA_CREATE = 101;
    public static final int RECETA_READ = 102;
    public static final int RECETA_DELETE = 103;
    public static final int RECETA_FIND_ALL = 104;
    public static final int RECETA_FIND_BY_PACIENTE  = 105;
    public static final int RECETA_FIND_BY_ESTADO = 106;
    public static final int RECETA_UPDATE = 107;

    // Medicamentos
    public static final int MEDICAMENTO_CREATE = 201;
    public static final int MEDICAMENTO_READ = 202;
    public static final int MEDICAMENTO_UPDATE = 203;
    public static final int MEDICAMENTO_DELETE = 204;
    public static final int MEDICAMENTO_FIND_BY_NOMBRE = 205;
    public static final int MEDICAMENTO_FIND_BY_CODIGO = 206;
    public static final int MEDICAMENTO_FIND_ALL = 207;

    // Pacientes
    public static final int PACIENTE_CREATE = 301;
    public static final int PACIENTE_READ = 302;
    public static final int PACIENTE_UPDATE = 303;
    public static final int PACIENTE_DELETE = 304;
    public static final int PACIENTE_FIND_BY_NOMBRE = 305;
    public static final int PACIENTE_FIND_ALL  = 306;
    public static final int PACIENTE_FIND_BY_ID  = 307;

    // Usuarios (Login)
    public static final int USUARIO_LOGIN = 401;
    public static final int USUARIO_READ = 402;
    public static final int USUARIO_CAMBIAR_CLAVE = 403;

    // Médicos
    public static final int MEDICO_CREATE = 404;
    public static final int MEDICO_UPDATE = 405;
    public static final int MEDICO_DELETE = 406;
    public static final int MEDICO_FIND_ALL = 407;

    // Farmaceutas
    public static final int FARMACEUTA_CREATE = 408;
    public static final int FARMACEUTA_DELETE = 409;
    public static final int FARMACEUTA_FIND_ALL = 410;

    // Errores
    public static final int ERROR_NO_ERROR = 0;
    public static final int ERROR_ERROR = 1;

    // Comunicación
    public static final int SYNC = 10;
    public static final int ASYNC = 11;
    public static final int DISCONNECT = 12;
    public static final int DELIVER_MESSAGE = 13;
}
