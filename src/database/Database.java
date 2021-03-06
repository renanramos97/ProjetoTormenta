package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    public static Connection connect(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Tormenta.db");
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        
        //System.out.println("Conexão: " + c);
        return c;
    }
    
    public static void executarUpdate(String sql){
        Connection c = connect();
        System.out.println("QUERY UPDATE: " + sql);
        
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch(SQLException e){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        try {
            //System.out.println("Fechando conexão: " + c);
            c.close();
        } catch(SQLException e){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public static ResultSet executarQuery(String sql){
        Connection c = connect();
        System.out.println("QUERY BUSCA: " + sql);
        ResultSet result = null;
        
        try {
            Statement s = c.createStatement();
            result = s.executeQuery(sql);
            
        } catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        return result;
    }
    
    public static ArrayList<String> queryConsulta(String query){
        Connection c = connect();
        ArrayList<String> saida = new ArrayList<>();
        System.out.println("QUERY: " + query);
        
        try{
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(query);
            
            int resultSize = result.getMetaData().getColumnCount();
            
            while(result.next()){
                int i = 1;
                while(i <= resultSize){
                    saida.add(result.getString(i++));
                }
            }
            
            s.close();
            //System.out.println("Fechando conexão: " + c);
            c.close();
            
        } catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return saida;
    }
}
