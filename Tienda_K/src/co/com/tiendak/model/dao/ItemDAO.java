package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;

import java.sql.*;
import java.util.*;

public class ItemDAO {
    
    private final Connection conn;

    public ItemDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }

    /** Recupera todos los ítems de todos los carritos. */
    public List<Items> listarTodos() {
        List<Items> lista = new ArrayList<>();
        String sql = "SELECT idItem, cantidad, precioUnitario, idCarrito, idProducto FROM ItemCarrito";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Busca un ítem por su ID. */
    public Items buscarPorId(int id) {
        String sql = "SELECT idItem, cantidad, precioUnitario, idCarrito, idProducto FROM ItemCarrito WHERE idItem = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearItem(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Lista los ítems de un carrito específico. */
    public List<Items> buscarPorCarrito(int idCarrito) {
        List<Items> lista = new ArrayList<>();
        String sql = "SELECT idItem, cantidad, precioUnitario, idCarrito, idProducto FROM ItemCarrito WHERE idCarrito = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Lista los ítems de un producto específico. */
    public List<Items> buscarPorProducto(int idProducto) {
        List<Items> lista = new ArrayList<>();
        String sql = "SELECT idItem, cantidad, precioUnitario, idCarrito, idProducto FROM ItemCarrito WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Inserta un nuevo ítem en un carrito. */
    public void crear(Items item) {
        String sql = "INSERT INTO ItemCarrito(cantidad, precioUnitario, idCarrito, idProducto) VALUES(?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getCantidad());
            ps.setDouble(2, item.getPrecioUnitario());
            ps.setInt(3, item.getIdCarrito());
            ps.setInt(4, item.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Actualiza un ítem existente. */
    public void actualizar(Items item) {
        String sql = "UPDATE ItemCarrito SET cantidad=?, precioUnitario=?, idCarrito=?, idProducto=? WHERE idItem=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getCantidad());
            ps.setDouble(2, item.getPrecioUnitario());
            ps.setInt(3, item.getIdCarrito());
            ps.setInt(4, item.getIdProducto());
            ps.setInt(5, item.getIdItem());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Elimina un ítem por su ID. */
    public void eliminar(int id) {
        String sql = "DELETE FROM ItemCarrito WHERE idItem = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Mapea un ResultSet a un objeto ItemCarrito. */
    private Items mapearItem(ResultSet rs) throws SQLException {
        return new Items(
            rs.getInt("idItem"),
            rs.getInt("cantidad"),
            rs.getDouble("precioUnitario"),
            rs.getInt("idCarrito"),
            rs.getInt("idProducto")
        );
    }
}
