package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.connection.DBConnection;
import co.com.tiendak.model.enums.TipoUsuario;
import co.com.tiendak.model.enums.TipoDocumento;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    //PARA CONEXION suerte cami xd :D
    private final Connection conn;

    public UsuarioDAO() {
        this.conn = DBConnection.getConnection().getConn();
    }
    
    //Obtiene todos los usuarios de la BD
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT idUsuario, nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contrasena, tipoUsuario FROM Usuario";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    //Busca usuario por id
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Busca usuario por documento
    public Usuario buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM Usuario WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Inserta nuevo usuario en bd
    public int crear(Usuario u) {
    String sql = "INSERT INTO Usuario(nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contrasena, tipoUsuario) " +
                 "VALUES(?,?,?,?,?,?,?,?,?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getApellido());
        ps.setString(3, u.getTipoDocumento().name());
        ps.setString(4, u.getDocumento());
        ps.setString(5, u.getTelefono());
        ps.setString(6, u.getCorreo());
        ps.setString(7, u.getDireccion());
        ps.setString(8, u.getContrasena());
        ps.setString(9, u.getTipoUsuario().name());
        int filas = ps.executeUpdate();

        if (filas > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // ID generado
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Indica que hubo error
}

    //Modificar datos de un usuario existente 
    public void actualizar(Usuario u) {
        String sql = "UPDATE Usuario SET nombre=?, apellido=?, tipoDocumento=?, documento=?, telefono=?, correo=?, direccion=?, contrasena=?, tipoUsuario=? WHERE idUsuario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTipoDocumento().name());
            ps.setString(4, u.getDocumento());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getDireccion());
            ps.setString(8, u.getContrasena());
            ps.setString(9, u.getTipoUsuario().name());
            ps.setInt(10, u.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Eliminar usuario
    public void eliminar(int id) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("idUsuario"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            TipoDocumento.valueOf(rs.getString("tipoDocumento")),
            rs.getString("documento"),
            rs.getString("telefono"),
            rs.getString("correo"),
            rs.getString("direccion"),
            rs.getString("contrasena"),
            TipoUsuario.valueOf(rs.getString("tipoUsuario"))
        );
    }
}
