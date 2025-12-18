package co.com.tiendak.model;

import co.com.tiendak.model.enums.Estado;
import java.util.Date;

public class Carrito {

    private int idCarrito;
    private Date fechaCreacion;
    private Estado estado;
    private int idUsuario;

    public Carrito() {
    }
    
    public Carrito(int idCarrito, Date fechaCreacion, Estado estado, int idUsuario) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
