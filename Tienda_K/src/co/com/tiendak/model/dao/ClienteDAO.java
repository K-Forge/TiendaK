package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import java.sql.*;
import java.util.*;

public class ClienteDAO {

    //private final Connection conn;

    public ClienteDAO() {
        //this.conn = DBConnection.getConnection();
    }

    /** Recupera todos los clientes. */
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT idCliente, idUsuario FROM Cliente";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Busca un cliente por su ID. */
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT idCliente, idUsuario FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Busca un cliente por el ID de usuario asociado. */
    public Cliente buscarPorUsuario(int idUsuario) {
        String sql = "SELECT idCliente, idUsuario FROM Cliente WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Inserta un nuevo cliente. */
    public void crear(Cliente c) {
        String sql = "INSERT INTO Cliente(idUsuario) VALUES(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Actualiza el usuario asociado de un cliente existente. */
    public void actualizar(Cliente c) {
        String sql = "UPDATE Cliente SET idUsuario = ? WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getIdUsuario());
            ps.setInt(2, c.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Elimina un cliente por su ID. */
    public void eliminar(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Mapea un ResultSet a un objeto Cliente. */
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("idCliente"),
            rs.getInt("idUsuario")
        );
    }
}







