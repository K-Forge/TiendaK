package co.com.tiendak.model;

import java.util.Date;

public class Stock {
    
    private int idStock;
    private Date fechaIngreso;
    private int stock;
    private int idProducto;

    public Stock() {
    }

    public Stock(int idStock, Date fechaIngreso, int stock, int idProducto) {
        this.idStock = idStock;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.idProducto = idProducto;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
