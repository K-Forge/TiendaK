package co.com.tiendak.controller;


import co.com.tiendak.model.Cliente;
import java.util.List;

import co.com.tiendak.model.Empleado;
import co.com.tiendak.model.connection.DBConnection;
import co.com.tiendak.model.dao.EmpleadoDAO;
import co.com.tiendak.vista.Ventana;
import java.util.Scanner;

public class Control {
    
    protected Ventana ventana = new Ventana();
    private final Scanner sc = new Scanner(System.in);
    private final ServicioLogin loginSvc = new ServicioLogin();
    //private final ServicioProducto prodSvc = new ServicioProducto();

    
    public void run(){
        int op;
         do{
             ventana.menu();
             op = ventana.pedirInt("");
             
             switch (op) {
                 case 1:
                     loginSvc.registrarCliente(sc);
                     break;
                 case 2:
                     menuCliente();
                     break;
                 case 3:
                     menuAdmin();
                     break;
                 case 4:
                     ventana.mostrarMensaje("¡Vuelve pronto ^^!");
                     
                 default:
                     ventana.mostrarMensaje("Opción Invalida");
             }
         }while (op != 4);
    }
    
    //Menú Cliente
    private void menuCliente() {
        Cliente cli = loginSvc.loginCliente(sc);
        if (cli == null) return;
        ventana.mostrarMensaje("\nBienvenido, " + cli.getNombre() + " " + cli.getApellido() + "!");
        int op;
        do{
            ventana.menuCliente();
            op = ventana.pedirInt("");
            switch (op) {
                //case 1 -> prodSvc.mostrarCatalogo();
                case 2 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida");
            }
        }while(op !=2);
    }//Cierre menuCliente
    
    //Menu Admin
    private void menuAdmin(){
        Empleado adm = loginSvc.loginEmpleado(sc);
        if(adm == null)return;
        
        int op;
        do{
            ventana.menuAdmin();
            op = ventana.pedirInt("");
            switch(op){
                /*case 1 -> prodSvc.mostrarCatalogo();
                case 2 -> prodSvc.crearProducto(sc);
                case 3 -> prodSvc.actualizarProducto(sc);
                case 4 -> prodSvc.eliminarProducto(sc);*/
                case 5 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida");
            }
        } while (op != 5);
        
    }
}//Cierre Control
