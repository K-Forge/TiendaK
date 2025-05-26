package co.com.tiendak.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Configuración de la conexión a la base de datos
    public static String usuario = "root";
    public static String clave = ""; //Sin contraseña por defecto
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/tienda_k";

    //Patron Singleton para la conexión
    private static Conexion conex = null;
    private Connection conn;

    private Conexion() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Conexion getConexion(){
        if(conex == null){
            conex = new Conexion();
        }
        return conex;
    }

    public Connection getConn() {
        return conn;
    }

    public void cerrarConexion() {
        if (conn != null) {
            try {
               conn.close();
               System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
