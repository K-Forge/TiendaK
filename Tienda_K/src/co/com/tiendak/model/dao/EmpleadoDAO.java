package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;

import java.sql.*;
import java.util.*;
import co.com.tiendak.model.enums.TipoEmpleado;

public class EmpleadoDAO {

    private final Connection conn;

    public EmpleadoDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }

    /** Recupera todos los empleados. */
    public List<Empleado> listarTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT idUsuario, tipoEmpleado FROM Empleado";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Busca un empleado por su ID de usuario. */
    public Empleado buscarPorId(int idUsuario) {
        String sql = "SELECT idUsuario, tipoEmpleado FROM Empleado WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEmpleado(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Lista los empleados que coinciden con un tipo. */
    public List<Empleado> buscarPorTipo(String tipoEmpleado) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT idUsuario, tipoEmpleado FROM Empleado WHERE tipoEmpleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEmpleado(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Inserta un nuevo empleado. */
    public void crear(Empleado e) {
        String sql = "INSERT INTO Empleado(idUsuario, tipoEmpleado) VALUES(?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getIdUsuario());
            ps.setString(2, e.getTipoEmpleado().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** Actualiza un empleado existente. */
    public void actualizar(Empleado e) {
        String sql = "UPDATE Empleado SET tipoEmpleado = ? WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getTipoEmpleado().name());
            ps.setInt(2, e.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** Elimina un empleado por su ID de usuario. */
    public void eliminar(int idUsuario) {
        String sql = "DELETE FROM Empleado WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Mapea un ResultSet a un objeto Empleado. */
    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
            rs.getInt("idUsuario"),
            TipoEmpleado.valueOf(rs.getString("tipoEmpleado"))
        );
    }
}