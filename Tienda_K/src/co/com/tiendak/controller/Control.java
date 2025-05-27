package co.com.tiendak.controller;

import co.com.tiendak.model.Cliente;
import java.util.List;

import co.com.tiendak.model.Empleado;
import co.com.tiendak.model.connection.DBConnection;
import co.com.tiendak.model.dao.EmpleadoDAO;
import co.com.tiendak.model.enums.TipoEmpleado;
import co.com.tiendak.vista.Ventana;
import java.util.Scanner;

public class Control {

    protected Ventana ventana = new Ventana();
    private final Scanner sc = new Scanner(System.in);
    private final ServicioLogin loginSvc = new ServicioLogin();
    private final ServicioProducto prodSvc = new ServicioProducto();

    public void run() {
        int op;
        do {
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
        } while (op != 4);
    }

    //Menú Cliente
    private void menuCliente() {
        Cliente cli = loginSvc.loginCliente(sc);
        if (cli == null) {
            return;
        }
        ventana.mostrarMensaje("\nBienvenido, " + cli.getNombre() + " " + cli.getApellido() + "!");
        int op;
        do {
            ventana.menuCliente(); // Asegúrate de que el menú tenga la nueva opción
            op = ventana.pedirInt("");
            switch (op) {
                case 1 ->
                    prodSvc.mostrarCatalogo();
                case 2 ->
                    prodSvc.buscarProducto(sc);
                case 3 ->
                    System.out.println("Sesión cerrada.");
                default ->
                    System.out.println("Opción inválida");
            }
        } while (op != 3);
    }//Cierre menuCliente

    //Menu Admin
    private void menuAdmin() {
        Empleado adm = loginSvc.loginEmpleado(sc);
        if (adm == null) {
            return;
        }

        int op;
        boolean esAdmin = adm.getTipoEmpleado() == TipoEmpleado.ADMINISTRADOR;

    do {
        if (esAdmin) {
            ventana.menuAdmin();
        } else {
            ventana.menuEmpleado();
        }

        op = ventana.pedirInt("Seleccione una opción:");

        if (esAdmin) {
            switch (op) {
                case 1 -> prodSvc.mostrarCatalogo();
                case 2 -> prodSvc.crearProducto(sc);
                case 3 -> prodSvc.actualizarProducto(sc);
                case 4 -> prodSvc.eliminarProducto(sc);
                case 5 -> prodSvc.verRegistrosDeStock(sc);
                case 6 -> prodSvc.agregarStock(sc);
                case 7 -> prodSvc.eliminarStock(sc);
                case 8 -> prodSvc.buscarProducto(sc);
                case 9 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida");
            }
        } else {
            switch (op) {
                case 1 -> prodSvc.mostrarCatalogo();
                case 2 -> prodSvc.buscarProducto(sc);
                case 3 -> prodSvc.agregarStock(sc);
                case 4 -> prodSvc.eliminarStock(sc);
                case 5 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida");
            }
        }

    } while ((esAdmin && op != 9) || (!esAdmin && op != 5));

    }
}//Cierre Control
