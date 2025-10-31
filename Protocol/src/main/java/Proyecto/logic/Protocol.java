package Proyecto.logic;

public class Protocol {
    public static final String SERVER = "localhost";
    public static final int PORT = 1234;

    // Recetas
    public static final int RECETA_CREATE = 101;
    public static final int RECETA_READ = 102;
    public static final int RECETA_UPDATE = 103;
    public static final int RECETA_SEARCH_BY_PACIENTE = 104;

    // Medicamentos
    public static final int MEDICAMENTO_CREATE = 201;
    public static final int MEDICAMENTO_SEARCH = 202;

    // Pacientes
    public static final int PACIENTE_CREATE = 301;
    public static final int PACIENTE_READ = 302;
    // ... etc para cada operación que necesites

    // Usuarios (Login)
    public static final int USUARIO_LOGIN = 401;
    public static final int USUARIO_READ = 402;

    // Errores
    public static final int ERROR_NO_ERROR = 0;
    public static final int ERROR_ERROR = 1;

    // Comunicación
    public static final int SYNC = 10;
    public static final int ASYNC = 11;
    public static final int DISCONNECT = 12;
    public static final int DELIVER_MESSAGE = 13;
}
