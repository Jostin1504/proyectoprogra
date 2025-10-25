package Proyecto.Data;

import Proyecto.Logic.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecetaDao {
    Database db;
    MedicamentoDao medicamentoDAO;

    public RecetaDao() {
        db = Database.instance();
        medicamentoDAO = new MedicamentoDao();
    }

    public int create(Receta r) throws Exception {
        String sql = "insert into Receta (fechaRetiro, fechaCreacion, idPaciente, estado) " +
                "values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, r.getFechaRetiro());
        stm.setString(2, r.getFechaCreacion());
        stm.setString(3, r.getIdPaciente());
        stm.setString(4, r.getEstado());

        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Error al crear receta");
        }

        ResultSet generatedKeys = stm.getGeneratedKeys();
        int idReceta = 0;
        if (generatedKeys.next()) {
            idReceta = generatedKeys.getInt(1);
        }

        // IMPORTANTE: Guardar cada medicamento con sus datos específicos
        for (Medicamento m : r.getMedicamentos()) {
            insertarMedicamentoReceta(idReceta, m);
        }
        return idReceta;
    }

    // ACTUALIZADO: Incluir indicaciones en la tabla intermedia
    private void insertarMedicamentoReceta(int idReceta, Medicamento m) throws Exception {
        String sql = "insert into Receta_Medicamento (idReceta, codigoMedicamento, " +
                "cantidad, duracion, indicaciones) values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, idReceta);
        stm.setString(2, m.getCodigo());
        stm.setInt(3, m.getCantidad());
        stm.setInt(4, m.getDuracion());
        stm.setString(5, m.getIndicaciones()); // AGREGADO
        db.executeUpdate(stm);
    }

    public Receta read(int id) throws Exception {
        String sql = "select * from Receta where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = db.executeQuery(stm);

        if (rs.next()) {
            Receta r = from(rs, "");
            // Cargar los medicamentos asociados
            r.setMedicamentos(obtenerMedicamentosReceta(id));
            return r;
        } else {
            throw new Exception("Receta no existe");
        }
    }

    // ACTUALIZADO: Cargar también las indicaciones específicas
    private List<Medicamento> obtenerMedicamentosReceta(int idReceta) {
        List<Medicamento> medicamentos = new ArrayList<>();
        try {
            String sql = "select m.codigo, m.nombre, m.presentacion, " +
                    "rm.cantidad, rm.duracion, rm.indicaciones " +
                    "from Medicamento m " +
                    "inner join Receta_Medicamento rm on m.codigo = rm.codigoMedicamento " +
                    "where rm.idReceta = ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, idReceta);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Medicamento m = new Medicamento();
                // Datos del catálogo
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setPresentacion(rs.getString("presentacion"));
                // Datos específicos de esta prescripción
                m.setCantidad(rs.getInt("cantidad"));
                m.setDuracion(rs.getInt("duracion"));
                m.setIndicaciones(rs.getString("indicaciones")); // AGREGADO
                medicamentos.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return medicamentos;
    }

    public List<Receta> findAll() {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Receta r = from(rs, "");
                // Cargar los medicamentos de cada receta
                r.setMedicamentos(obtenerMedicamentosReceta(rs.getInt("id")));
                resultado.add(r);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Receta> findByPaciente(String idPaciente) {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta where idPaciente=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, idPaciente);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                Receta r = from(rs, "");
                r.setMedicamentos(obtenerMedicamentosReceta(rs.getInt("id")));
                resultado.add(r);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public List<Receta> findByEstado(String estado) {
        List<Receta> resultado = new ArrayList<>();
        try {
            String sql = "select * from Receta where estado=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, estado);
            ResultSet rs = db.executeQuery(stm);

            while (rs.next()) {
                resultado.add(from(rs, ""));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public void update(Receta r, int id) throws Exception {
        String sql = "update Receta set fechaRetiro=?, estado=? where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, r.getFechaRetiro());
        stm.setString(2, r.getEstado());
        stm.setInt(3, id);
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Receta no existe");
        }
    }

    private Receta from(ResultSet rs, String alias) {
        try {
            Receta r = new Receta();
            r.setFechaRetiro(rs.getString(alias + "fechaRetiro"));
            r.setFechaCreacion(rs.getString(alias + "fechaCreacion"));
            r.setIdPaciente(rs.getString(alias + "idPaciente"));
            r.setEstado(rs.getString(alias + "estado"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
}
