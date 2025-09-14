package Proyecto.Logic;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Medico.class, Farmaceuta.class, Administrador.class})
public class Usuario {
    @XmlElement
    protected String nombre;

    @XmlElement
    protected String cedula;

    @XmlElement
    protected String clave;

    @XmlElement
    protected String rol;

    public Usuario(String nombre, String cedula, String rol) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.clave = cedula;
        this.rol = rol;
    }

    public Usuario() {
        nombre = "";
        cedula = "";
        clave = "";
        rol = "";
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getClave() { return clave; }
    public String getRol() { return rol; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setClave(String clave) { this.clave = clave; }


}
