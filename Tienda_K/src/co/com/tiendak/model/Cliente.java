package co.com.tiendak.model;

import co.com.tiendak.model.enums.TipoDocumento;

public class Cliente extends Usuario { 
 
    private int idCliente; 

    public Cliente() {
    }

    public Cliente(int idCliente, int idUsuario) {
        super(idUsuario);
        this.idCliente = idCliente;
    }
 
    public Cliente(int idUsuario, String nombre, String apellido, TipoDocumento tipoDocumento, String documento, String telefono, String correo, String direccion, String contraseña, int idCliente) { 
        super(idUsuario, nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contraseña); 
        this.idCliente = idCliente; 
    } 
 
    public int getIdCliente() { 
        return idCliente; 
    } 
 
    public void setIdCliente(int idCliente) { 
        this.idCliente = idCliente; 
    } 
 
    @Override 
    public String toString() { 
        return super.toString() + "Cliente{" + "idCliente=" + idCliente + '}'; 
    } 
} 
