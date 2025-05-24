package co.com.tiendak.model.dao;

import co.com.tiendak.model.*;
import co.com.tiendak.model.enums.TipoUsuario;
import co.com.tiendak.model.enums.TipoDocumento;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    //PARA CONEXION suerte cami xd :D
    //private final Conexion conex;

    public UsuarioDAO() {
        //this.conex = DBConnection.getConnection();
    }
    
    //Obtiene todos los usuarios de la BD
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT idUsuario, nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contrasena, tipoUsuario FROM Usuario";
        try (Statement st = conex.createStatement();
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
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
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
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
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
    public void crear(Usuario u) {
        String sql = "INSERT INTO Usuario(nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contrasena, tipoUsuario) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTipoDocumento().name());
            ps.setString(4, u.getDocumento());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getDireccion());
            ps.setString(8, u.getContrasena());
            ps.setString(9, u.getTipoUsuario().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Modificar datos de un usuario existente 
    public void actualizar(Usuario u) {
        String sql = "UPDATE Usuario SET nombre=?, apellido=?, tipoDocumento=?, documento=?, telefono=?, correo=?, direccion=?, contrasena=?, tipoUsuario=? WHERE idUsuario=?";
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
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
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
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
