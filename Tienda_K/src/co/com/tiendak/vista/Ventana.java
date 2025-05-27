
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
        
        public void menu(){
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
        
        public void menuCliente(){
            System.out.println("\n-- Menú Cliente --");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Cerrar sesión");
            System.out.print(">> ");
        }
        
        public void menuAdmin(){
            System.out.println("\n-- Panel Administrador --");
            System.out.println("1. Ver inventario");
            System.out.println("2. Agregar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Cerrar sesión");
            System.out.print(">> ");
        }
}//Ventana Cierre
