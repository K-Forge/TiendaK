package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;

import java.sql.*;
import java.util.*;
import co.com.tiendak.model.enums.Categoria;


public class ProductoDAO {
    
    private final Connection conn;

    public ProductoDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }

    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProducto, categoria, nombreProducto, precioUnitario FROM Producto";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Producto buscarPorId(int id) {
        String sql = "SELECT idProducto, categoria, nombreProducto, precioUnitario FROM Producto WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProducto, categoria, nombreProducto, precioUnitario FROM Producto WHERE categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void crear(Producto p) {
        String sql = "INSERT INTO Producto(categoria, nombreProducto, precioUnitario) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getCategoria().name());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecioUnitario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Producto p) {
        String sql = "UPDATE Producto SET categoria = ?, nombreProducto = ?, precioUnitario = ? WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getCategoria().name());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecioUnitario());
            ps.setInt(4, p.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Producto WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getInt("idProducto"),
            Categoria.valueOf(rs.getString("categoria")),
            rs.getString("nombreProducto"),
            rs.getDouble("precioUnitario")
        );
    }
}