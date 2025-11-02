package Proyecto.data;

import Proyecto.logic.*;
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
        System.out.println("DAO: Iniciando creación de receta");
        System.out.println("DAO: Paciente: " + r.getIdPaciente());
        System.out.println("DAO: Fecha Creación: " + r.getFechaCreacion());
        System.out.println("DAO: Estado: " + r.getEstado());

        String sql = "insert into Receta (fechaRetiro, fechaCreacion, idPaciente, estado) " +
                "values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, r.getFechaRetiro());
        stm.setString(2, r.getFechaCreacion());
        stm.setString(3, r.getIdPaciente());
        stm.setString(4, r.getEstado());

        System.out.println("DAO: Ejecutando INSERT en tabla Receta");
        int count = db.executeUpdate(stm);
        if (count == 0) {
            System.err.println("DAO ERROR: No se insertó la receta");
            throw new Exception("Error al crear receta");
        }
        System.out.println("DAO: Receta insertada, count=" + count);

        ResultSet generatedKeys = stm.getGeneratedKeys();
        int idReceta = 0;
        if (generatedKeys.next()) {
            idReceta = generatedKeys.getInt(1);
            System.out.println("DAO: ID de receta generado: " + idReceta);
        }

        System.out.println("DAO: Insertando medicamentos...");
        if (r.getMedicamentos() != null) {
            System.out.println("DAO: Cantidad de medicamentos: " + r.getMedicamentos().size());
            for (Medicamento m : r.getMedicamentos()) {
                System.out.println("DAO: Insertando medicamento: " + m.getNombre() +
                        " (Código: " + m.getCodigo() + ")");
                insertarMedicamentoReceta(idReceta, m);
            }
        } else {
            System.out.println("DAO WARNING: La lista de medicamentos es NULL");
        }

        System.out.println("DAO: Receta creada completamente con ID: " + idReceta);
        return idReceta;
    }

    private void insertarMedicamentoReceta(int idReceta, Medicamento m) throws Exception {
        System.out.println("DAO: insertarMedicamentoReceta() - ID Receta: " + idReceta +
                ", Medicamento: " + m.getCodigo());
        String sql = "insert into Receta_Medicamento (idReceta, codigoMedicamento, " +
                "cantidad, duracion, indicaciones) values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, idReceta);
        stm.setString(2, m.getCodigo());
        stm.setInt(3, m.getCantidad());
        stm.setInt(4, m.getDuracion());
        stm.setString(5, m.getIndicaciones());
        int count = db.executeUpdate(stm);
        System.out.println("DAO: Medicamento insertado en Receta_Medicamento, count=" + count);
    }

    public Receta read(int id) throws Exception {
        String sql = "select * from Receta where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = db.executeQuery(stm);

        if (rs.next()) {
            Receta r = from(rs, "");
            r.setMedicamentos(obtenerMedicamentosReceta(id));
            return r;
        } else {
            throw new Exception("Receta no existe");
        }
    }

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
                r.setMedicamentos(obtenerMedicamentosReceta(rs.getInt("id")));
                resultado.add(r);
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public void delete(int id) throws Exception {
        String sql = "delete from Receta where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Receta no existe");
        }
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
