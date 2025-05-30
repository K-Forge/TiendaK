package co.com.tiendak.vista;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ventana {

    public static Scanner leer = new Scanner(System.in);

    public String pedirString(String mensaje) {
        System.out.println(mensaje);
        String op = leer.nextLine();
        leer.nextLine();
        return op;
    }

    public int pedirInt(String mensaje) {
        System.out.println(mensaje);
        int op = leer.nextInt();
        leer.nextLine();
        return op;
    }

    public long pedirLong(String mensaje) {
        System.out.println(mensaje);
        long op = leer.nextLong();
        leer.nextLine();
        return op;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void menu() {
        System.out.println("======================================");
        System.out.println("       ¡Bienvenido a Tienda K!        ");
        System.out.println("======================================");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Registrarse como Cliente");
        System.out.println("2. Iniciar sesión Cliente");
        System.out.println("3. Iniciar sesión Administrador");
        System.out.println("4. Salir");
        System.out.print(">> Opción: ");
    }

    public void menuCliente() {
        System.out.println("""
        -----------------------------
        1. Ver Catálogo
        2. Buscar Producto
        3. Cerrar Sesión
        -----------------------------
        """);
    }

    public void menuAdmin() {
        System.out.println("""
        === MENÚ ADMINISTRADOR ===
        1. Ver catálogo
        2. Crear producto
        3. Actualizar producto
        4. Eliminar producto
        5. Ver registros de stock
        6. Agregar stock
        7. Eliminar stock
        8. Buscar producto
        9. Cerrar sesión
    """);
    }

    public void menuEmpleado() {
         System.out.println("""
        === MENÚ EMPLEADO ===
        1. Ver catálogo
        2. Buscar producto
        3. Agregar stock
        4. Eliminar stock
        5. Cerrar sesión
    """);
    }
}//Ventana Cierre
