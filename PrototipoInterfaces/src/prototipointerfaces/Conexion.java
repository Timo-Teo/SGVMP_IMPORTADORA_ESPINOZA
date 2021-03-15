package prototipointerfaces;

import java.sql.*;

public class Conexion {
    
    Connection cn=null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Registra el drive de conexion para la bd
            cn = DriverManager.getConnection("jdbc:mysql://localhost:33068/baseimportadoraespinoza","root",""); // se utiliza la libreria que se impo
        // Este método crea un objeto Connection, que se utiliza para crear sentencias SQL, 
        //enviarlas a la base de datos y procesar los resultados.
                                                                                    
            System.out.println("Conexion exitosa"); // Driver es un conjunto de clase necesarias que se implementan para
                                                       // la conexion sea correcta
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cn;   
    }
    
    Statement createStatement(){
        throw new  UnsupportedOperationException("No Soportado");
    }
    
    public PreparedStatement prepareStatement(String insert_into) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }  
}

