
package javaapplication3.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    String url = "jdbc:mysql://localhost:3306/datosreserva";
    String usuario = "root";
    String contraseña = "";
    String driver="com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Connection conectar() throws ClassNotFoundException{

        try  {
            Class.forName(driver);
            cx= DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos");
            // Aquí puedes realizar operaciones con la base de datos
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return cx;
    }

}