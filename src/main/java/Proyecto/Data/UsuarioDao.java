package Proyecto.Data;

import Proyecto.Logic.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    Database db;

    public UsuarioDao() {
        db = Database.instance();
    }

    public void create(Usuario u) throws Exception {
        String sql = "insert into Usuario (cedula, nombre, clave, rol, especialidad) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, u.getCedula());
        stm.setString(2, u.getNombre());

        String clave = u.getClave();
        if (clave == null || clave.trim().isEmpty()) {
            clave = u.getCedula();
        }
        stm.setString(3, clave);

        String rol = "ADM";
        if (u instanceof Medico) {
            rol = "MED";
        } else if (u instanceof Farmaceuta) {
            rol = "FAR";
        }
        stm.setString(4, rol);

        if (u instanceof Medico) {
            stm.setString(5, ((Medico) u).getEspecialidad());
        } else {
            stm.setString(5, null);
        }

        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario ya existe");
        }
    }

    public Usuario read(String cedula) throws Exception {
        String sql = "select * from Usuario where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);

        if (rs.next()) {
            return from(rs, "");
        } else {
            throw new Exception("Usuario no existe");
        }
    }

    public void update(Usuario u) throws Exception {
        String sql = "update Usuario set nombre=?, clave=?, rol=?, especialidad=? " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, u.getNombre());
        stm.setString(2, u.getClave());
        stm.setString(3, u.getRol());
        if (u instanceof Medico) {
            stm.setString(4, ((Medico) u).getEspecialidad());
        } else {
            stm.setString(4, null);
        }
        stm.setString(5, u.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }

    public void delete(Usuario u) throws Exception {
        String sql = "delete from Usuario where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, u.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }

    public List<Usuario> findAll() {
        List<Usuario> resultado = new ArrayList<>();
        try {
            String sql = "select * from Usuario";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Medico> findAllMedicos() {
        List<Medico> resultado = new ArrayList<>();
        try {
            String sql = "select * from Usuario where rol='MED'";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Medico m = new Medico();
                m.setCedula(rs.getString("cedula"));
                m.setNombre(rs.getString("nombre"));
                m.setClave(rs.getString("clave"));
                m.setEspecialidad(rs.getString("especialidad"));
                resultado.add(m);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Farmaceuta> findAllFarmaceutas() {
        List<Farmaceuta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Usuario where rol='FAR'";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Farmaceuta f = new Farmaceuta();
                f.setCedula(rs.getString("cedula"));
                f.setNombre(rs.getString("nombre"));
                f.setClave(rs.getString("clave"));
                resultado.add(f);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Administrador> findAllAdministradores() {
        List<Administrador> resultado = new ArrayList<>();
        try {
            String sql = "select * from Usuario where rol='ADM'";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Administrador a = new Administrador();
                a.setCedula(rs.getString("cedula"));
                a.setNombre(rs.getString("nombre"));
                a.setClave(rs.getString("clave"));
                resultado.add(a);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    private Usuario from(ResultSet rs, String alias) {
        try {
            String rol = rs.getString(alias + "rol");
            Usuario u;

            if (rol.equals("MED")) {
                Medico m = new Medico();
                m.setEspecialidad(rs.getString(alias + "especialidad"));
                u = m;
            } else if (rol.equals("FAR")) {
                u = new Farmaceuta();
            } else {
                u = new Administrador();
            }

            u.setCedula(rs.getString(alias + "cedula"));
            u.setNombre(rs.getString(alias + "nombre"));
            u.setClave(rs.getString(alias + "clave"));
            u.setRol(rol);
            return u;
        } catch (SQLException ex) {
            return null;
        }
    }
}
