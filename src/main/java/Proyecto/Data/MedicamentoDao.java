package Proyecto.Data;

import Proyecto.Logic.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDao {
    Database db;

    public MedicamentoDao() {
        db = Database.instance();
    }

    public void create(Medicamento m) throws Exception {
        String sql = "insert into Medicamento (codigo, nombre, presentacion) values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, m.getCodigo());
        stm.setString(2, m.getNombre());
        stm.setString(3, m.getPresentacion());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Medicamento ya existe");
        }
    }

    public Medicamento read(String codigo) throws Exception {
        String sql = "select * from Medicamento where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);

        if (rs.next()) {
            return from(rs, "");
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public void update(Medicamento m) throws Exception {
        String sql = "update Medicamento set nombre=?, presentacion=? where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, m.getNombre());
        stm.setString(2, m.getPresentacion());
        stm.setString(3, m.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Medicamento no existe");
        }
    }

    public void delete(Medicamento m) throws Exception {
        String sql = "delete from Medicamento where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, m.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Medicamento no existe");
        }
    }

    public List<Medicamento> findByNombre(String nombre) {
        List<Medicamento> resultado = new ArrayList<>();
        try {
            String sql = "select * from Medicamento where nombre like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%" + nombre + "%");
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Medicamento> findByCodigo(String codigo) {
        List<Medicamento> resultado = new ArrayList<>();
        try {
            String sql = "select * from Medicamento where codigo like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%" + codigo + "%");
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Medicamento> findAll() {
        List<Medicamento> resultado = new ArrayList<>();
        try {
            String sql = "select * from Medicamento";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public Medicamento from(ResultSet rs, String alias) {
        try {
            Medicamento m = new Medicamento();
            m.setCodigo(rs.getString(alias + "codigo"));
            m.setNombre(rs.getString(alias + "nombre"));
            m.setPresentacion(rs.getString(alias + "presentacion"));
            // cantidad, duracion e indicaciones quedan en 0/"" por defecto
            return m;
        } catch (SQLException ex) {
            return null;
        }
    }
}
