package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Classes {
    public static void gerarTableClasses(){
        Connection c = null;
        Statement s = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Tormenta.db");
            System.out.println("Banco de dados acessado com sucesso.");
            
            s = c.createStatement();
            
            String sql = "CREATE TABLE classes("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ")";
            
            
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
}
