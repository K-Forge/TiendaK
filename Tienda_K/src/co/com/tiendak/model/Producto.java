package co.com.tiendak.model;

import co.com.tiendak.model.enums.Categoria;

public class Producto { 
 
    private int idProducto; 
    private Categoria categoria; 
    private String nombre; 
    private double precioUnitario; 
 
    public Producto(int idProducto, Categoria categoria, String nombre, double precioUnitario) { 
        this.idProducto = idProducto; 
        this.categoria = categoria; 
        this.nombre = nombre; 
        this.precioUnitario = precioUnitario; 
    } 
 
    public int getIdProducto() { 
        return idProducto; 
    } 
 
    public void setIdProducto(int idProducto) { 
        this.idProducto = idProducto; 
    } 
 
    public Categoria getCategoria() { 
        return categoria; 
    } 
 
    public void setCategoria(Categoria categoria) { 
        this.categoria = categoria; 
    } 
 
    public String getNombre() { 
        return nombre; 
    } 
 
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    } 
 
    public double getPrecioUnitario() { 
        return precioUnitario; 
    } 
 
    public void setPrecioUnitario(double precioUnitario) { 
        this.precioUnitario = precioUnitario; 
    } 
 
 
    @Override 
    public String toString() { 
        return "Producto{" + "idProducto=" + idProducto + ", categoria=" + categoria + ", nombre=" + nombre + ", precioUnitario=" + precioUnitario +'}'; 
    } 
} 
