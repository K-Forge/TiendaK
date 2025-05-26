package co.com.tiendak.model;

import co.com.tiendak.model.enums.MetodoPago;
import java.util.Date;

public class Factura {
    
    private int idFactura;
    private Date fechaCompra;
    private double totalCompra;
    private MetodoPago metodoPago;
    private double IVA;
    private int idEmpleado;
    private int idCliente;
    private int idCarrito;

    public Factura() {
    }

    public Factura(int idFactura, Date fechaCompra, double totalCompra, MetodoPago metodoPago, double IVA, int idEmpleado, int idCliente, int idCarrito) {
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
        this.IVA = IVA;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idCarrito = idCarrito;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }
}
