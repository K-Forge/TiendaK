package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;

import java.sql.*;
import java.util.*;

public class StockDAO {
    
    private final Connection conn;

    public StockDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }

    public List<Stock> listarTodos() {
        List<Stock> lista = new ArrayList<>();
        String sql = "SELECT idStock, fechaIngreso, stock, idProducto FROM Stock";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearStock(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Stock buscarPorId(int id) {
        String sql = "SELECT * FROM Stock WHERE idStock = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearStock(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Stock> buscarPorProducto(int idProducto) {
        List<Stock> lista = new ArrayList<>();
        String sql = "SELECT * FROM Stock WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearStock(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void crear(Stock s) {
        String sql = "INSERT INTO Stock(fechaIngreso, stock, idProducto) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(s.getFechaIngreso().getTime()));
            ps.setInt(2, s.getStock());
            ps.setInt(3, s.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Stock s) {
        String sql = "UPDATE Stock SET fechaIngreso = ?, stock = ?, idProducto = ? WHERE idStock = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Conversión de fecha para JDBC:
            ps.setDate(1, new java.sql.Date(s.getFechaIngreso().getTime()));
            ps.setInt(2, s.getStock());
            ps.setInt(3, s.getIdProducto());
            ps.setInt(4, s.getIdStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Stock WHERE idStock = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Stock mapearStock(ResultSet rs) throws SQLException {
        return new Stock(
            rs.getInt("idStock"),
            rs.getDate("fechaIngreso"),
            rs.getInt("stock"),
            rs.getInt("idProducto")
        );
    }
}