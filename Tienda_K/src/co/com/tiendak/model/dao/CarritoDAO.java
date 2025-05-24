package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.enums.Estado;
import java.sql.*;
import java.util.*;

public class CarritoDAO {

    //private final Connection conn;

    public CarritoDAO() {
      //  this.conn = DBConnection.getConnection();
    }

    /** Recupera todos los carritos. */
    public List<Carrito> listarTodos() {
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT idCarrito, fechaCreacion, estado, idUsuario FROM CarritoCompra";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearCarrito(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Busca un carrito por su ID. */
    public Carrito buscarPorId(int id) {
        String sql = "SELECT idCarrito, fechaCreacion, estado, idUsuario FROM CarritoCompra WHERE idCarrito = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCarrito(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Lista los carritos de un usuario específico. */
    public List<Carrito> buscarPorUsuario(int idUsuario) {
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT idCarrito, fechaCreacion, estado, idUsuario FROM CarritoCompra WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCarrito(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Inserta un nuevo carrito. */
    public void crear(Carrito c) {
        String sql = "INSERT INTO CarritoCompra(fechaCreacion, estado, idUsuario) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(c.getFechaCreacion().getTime()));
            ps.setString(2, c.getEstado().name());
            ps.setInt(3, c.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Actualiza un carrito existente. */
    public void actualizar(Carrito c) {
        String sql = "UPDATE CarritoCompra SET fechaCreacion = ?, estado = ?, idUsuario = ? WHERE idCarrito = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(c.getFechaCreacion().getTime()));
            ps.setString(2, c.getEstado().name());
            ps.setInt(3, c.getIdUsuario());
            ps.setInt(4, c.getIdCarrito());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Elimina un carrito por su ID. */
    public void eliminar(int id) {
        String sql = "DELETE FROM CarritoCompra WHERE idCarrito = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Mapea un ResultSet a un objeto CarritoCompra. */
    private Carrito mapearCarrito(ResultSet rs) throws SQLException {
        return new Carrito(
            rs.getInt("idCarrito"),
            rs.getTimestamp("fechaCreacion"),
            Estado.valueOf(rs.getString("estado")),
            rs.getInt("idUsuario")
        );
    }
}