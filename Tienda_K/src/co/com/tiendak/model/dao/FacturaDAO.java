package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;

import java.sql.*;
import java.util.*;
import co.com.tiendak.model.enums.MetodoPago;

public class FacturaDAO {
    
    private final Connection conn;

    public FacturaDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }

    /** Recupera todas las facturas. */
    public List<Factura> listarTodos() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT idFactura, fechaCompra, totalCompra, metodoPago, iva, idEmpleado, idCliente, idCarrito FROM Factura";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearFactura(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Busca una factura por su ID. */
    public Factura buscarPorId(int id) {
        String sql = "SELECT idFactura, fechaCompra, totalCompra, metodoPago, iva, idEmpleado, idCliente, idCarrito FROM Factura WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearFactura(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Lista las facturas emitidas por un empleado específico. */
    public List<Factura> buscarPorEmpleado(int idEmpleado) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT idFactura, fechaCompra, totalCompra, metodoPago, iva, idEmpleado, idCliente, idCarrito FROM Factura WHERE idEmpleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFactura(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Lista las facturas de un cliente específico. */
    public List<Factura> buscarPorCliente(int idCliente) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT idFactura, fechaCompra, totalCompra, metodoPago, iva, idEmpleado, idCliente, idCarrito FROM Factura WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFactura(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Inserta una nueva factura. */
    public void crear(Factura f) {
        String sql = "INSERT INTO Factura(fechaCompra, totalCompra, metodoPago, iva, idEmpleado, idCliente, idCarrito) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(f.getFechaCompra().getTime()));
            ps.setDouble(2, f.getTotalCompra());
            ps.setString(3, f.getMetodoPago().name());
            ps.setDouble(4, f.getIVA());
            ps.setInt(5, f.getIdEmpleado());
            ps.setInt(6, f.getIdCliente());
            ps.setInt(7, f.getIdCarrito());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Actualiza una factura existente. */
    public void actualizar(Factura f) {
        String sql = "UPDATE Factura SET fechaCompra=?, totalCompra=?, metodoPago=?, iva=?, idEmpleado=?, idCliente=?, idCarrito=? WHERE idFactura=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(f.getFechaCompra().getTime()));
            ps.setDouble(2, f.getTotalCompra());
            ps.setString(3, f.getMetodoPago().name());
            ps.setDouble(4, f.getIVA());
            ps.setInt(5, f.getIdEmpleado());
            ps.setInt(6, f.getIdCliente());
            ps.setInt(7, f.getIdCarrito());
            ps.setInt(8, f.getIdFactura());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Elimina una factura por su ID. */
    public void eliminar(int id) {
        String sql = "DELETE FROM Factura WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Mapea un ResultSet a un objeto Factura. */
    private Factura mapearFactura(ResultSet rs) throws SQLException {
        return new Factura(
            rs.getInt("idFactura"),
            rs.getDate("fechaCompra"),
            rs.getDouble("totalCompra"),
            MetodoPago.valueOf(rs.getString("metodoPago")),
            rs.getDouble("iva"),
            rs.getInt("idEmpleado"),
            rs.getInt("idCliente"),
            rs.getInt("idCarrito")
        );
    }
}
