package co.com.tiendak.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //Configuración de la conexión a la base de datos
    public static String usuario = "root";
    public static String clave = ""; //Sin contraseña por defecto
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/tienda_k";

    //Patron Singleton para la conexión
    private static DBConnection conn = null;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static DBConnection getConnection() {
        if (conn == null) {
            conn = new DBConnection();
        }
        return conn;
    }

    public Connection getConn() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, usuario, clave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
