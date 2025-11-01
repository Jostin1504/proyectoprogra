package Proyecto.data;

import Proyecto.logic.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    Database db;

    public PacienteDao() {
        db = Database.instance();
    }



    public void create(Paciente p) throws Exception {
        String sql = "insert into Paciente (id, nombre, fechaNacimiento, telefono) " +
                "values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getId());
        stm.setString(2, p.getNombre());
        stm.setString(3, p.getFechanac());
        stm.setString(4, p.getNumTelefono());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Paciente ya existe");
        }
    }

    public Paciente read(String id) throws Exception {
        String sql = "select * from Paciente where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);

        if (rs.next()) {
            return from(rs, "");
        } else {
            throw new Exception("Paciente no existe");
        }
    }

    public void update(Paciente p) throws Exception {
        String sql = "update Paciente set nombre=?, fechaNacimiento=?, telefono=? " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getNombre());
        stm.setString(2, p.getFechanac());
        stm.setString(3, p.getNumTelefono());
        stm.setString(4, p.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Paciente no existe");
        }
    }

    public void delete(Paciente p) throws Exception {
        String sql = "delete from Paciente where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Paciente no existe");
        }
    }

    public List<Paciente> findByNombre(String nombre) {
        List<Paciente> resultado = new ArrayList<>();
        try {
            String sql = "select * from Paciente where nombre like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%" + nombre + "%");
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Paciente> findAll() {
        List<Paciente> resultado = new ArrayList<>();
        try {
            String sql = "select * from Paciente";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Paciente> findById(String id) {
        List<Paciente> resultado = new ArrayList<>();
        try {
            String sql = "select * from Paciente where id like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%" + id + "%");
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    private Paciente from(ResultSet rs, String alias) {
        try {
            Paciente p = new Paciente();
            p.setId(rs.getString(alias + "id"));
            p.setNombre(rs.getString(alias + "nombre"));
            p.setFechanac(rs.getString(alias + "fechaNacimiento"));
            p.setNumTelefono(rs.getString(alias + "telefono"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
}
