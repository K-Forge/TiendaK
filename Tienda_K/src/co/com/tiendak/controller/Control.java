package co.com.tiendak.controller;


import java.util.List;

import co.com.tiendak.model.Empleado;
import co.com.tiendak.model.dao.EmpleadoDAO;

public class Control {
    
    public void run(){
        EmpleadoDAO dao = new EmpleadoDAO();
        List<Empleado> lista = dao.listarTodos();
        for (Empleado emp : lista) {
            System.out.println("ID: " + emp.getDocumento());
            System.out.println("Nombre: " + emp.getNombre());
            System.out.println("Apellido: " + emp.getApellido());
            System.out.println("Dirección: " + emp.getDireccion());
            System.out.println("Telefono: " + emp.getTelefono());
            System.out.println("-----------------------------");
        }
    }
    
}//Cierre Control
