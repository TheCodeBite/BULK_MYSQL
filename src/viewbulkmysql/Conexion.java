/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewbulkmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Conexion {
    public String user;
    public String password;
    
    public String drive = "com.mysql.jdbc.Driver";
    
    public String database;
    public String hostname;
    public String port;
    
    public String url;

    public Conexion(String user, String password, String database, String hostname, String port) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.hostname = hostname;
        this.port = port;
        this.url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    }

    public Conexion() {
    }
    
    
    public Connection mysql() {
        Connection conn = null;
        
        try{
            Class.forName(drive);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa..");
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
        return conn;
    }
    
    public String ExcuteQuery(String query, Connection con) {
        ArrayList<String> errores = new ArrayList<>();
        
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            
            return "Exitoso";
        } catch(SQLException e){
            System.out.println("este es el error");
            System.out.println(query);
            System.out.println("-----");
            e.printStackTrace();
            errores.add(query);
            
            return "";
        } // rchacon@grupoconserva.mx
        
    }

    
}
