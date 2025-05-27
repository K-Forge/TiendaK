package co.com.tiendak.controller;

import co.com.tiendak.model.*;
import co.com.tiendak.model.enums.*;
import co.com.tiendak.model.dao.*;
import co.com.tiendak.vista.Ventana;
import java.util.Scanner;
public class ServicioLogin {
    
    protected Ventana ventana = new Ventana();
    private final UsuarioDAO usuario= new UsuarioDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    
    //Metodo para registrar Cliente
    public void registrarCliente(Scanner sc){
        ventana.mostrarMensaje("\n--- Registro Cliente ---");
        Usuario u = leerUsuarioComun(sc, TipoUsuario.REGISTRADO);
        int idUsuario = usuario.crear(u);
        if(idUsuario>0){
            Cliente c = new Cliente(); c.setIdCliente(idUsuario);
            clienteDAO.actualizar(c);
            ventana.mostrarMensaje("✅ Cliente registrado con ID " + idUsuario);
        }
    }
    
    
    //Metodo para login cliente
    public Cliente loginCliente(Scanner sc){
        ventana.mostrarMensaje("\nDocumento: ");
        String doc = sc.nextLine();
        ventana.mostrarMensaje("Contraseña:");
        String password = sc.nextLine();

        Usuario u = usuario.buscarPorDocumento(doc);
        if(u != null && u.getContrasena().equals(password) && u.getTipoUsuario() == TipoUsuario.REGISTRADO) {
            Cliente c = clienteDAO.buscarPorUsuario(u.getIdUsuario());
            if (c != null) return c;
        }
        System.out.println("Credenciales incorrectas.");
        return null;
    }
    
    //Metodo para login empleado
    public Empleado loginEmpleado(Scanner sc){
        ventana.mostrarMensaje("\nDocumento: ");
        String doc = sc.nextLine();
        ventana.mostrarMensaje("Contraseña:");
        String password = sc.nextLine();
        
        Usuario u = usuario.buscarPorDocumento(doc);
        if(u != null && u.getContrasena().equals(password) && u.getTipoUsuario() == TipoUsuario.REGISTRADO) {
        Empleado e = empleadoDAO.buscarPorId(u.getIdUsuario());
        if (e != null) {
            // Seteamos los datos comunes heredados de Usuario
            e.setNombre(u.getNombre());
            e.setApellido(u.getApellido());
            e.setDocumento(u.getDocumento());
            e.setCorreo(u.getCorreo());
            e.setDireccion(u.getDireccion());
            e.setTelefono(u.getTelefono());
            e.setTipoDocumento(u.getTipoDocumento());
            e.setContrasena(u.getContrasena());
            e.setTipoUsuario(u.getTipoUsuario());

            if (e.getTipoEmpleado() == TipoEmpleado.ADMINISTRADOR) {
                ventana.mostrarMensaje("🔐 Bienvenido Administrador " + e.getNombre());
            } else {
                ventana.mostrarMensaje("👤 Bienvenido Empleado " + e.getNombre());
            }
            return e;
        }
    }
    System.out.println("❌ Credenciales Incorrectas.");
    return null;
    }
    
    private Usuario leerUsuarioComun(Scanner sc, TipoUsuario tu) {
        Usuario u = new Usuario();
        System.out.print("Nombre: ");  u.setNombre(sc.nextLine());
        System.out.print("Apellido: ");u.setApellido(sc.nextLine());
        u.setTipoDocumento(TipoDocumento.CC); // simplificado
        System.out.print("Documento: ");u.setDocumento(sc.nextLine());
        System.out.print("Teléfono: "); u.setTelefono(sc.nextLine());
        System.out.print("Correo: ");   u.setCorreo(sc.nextLine());
        System.out.print("Dirección: ");u.setDireccion(sc.nextLine());
        System.out.print("Contraseña: ");u.setContrasena(sc.nextLine());
        u.setTipoUsuario(tu);
        return u;
    }
}


