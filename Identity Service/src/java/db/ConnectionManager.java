package db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.*;
/**
 *
 * @author Scarletta's
 */
public class ConnectionManager {
    static Connection con;
    static String url;
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/acc_management";
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try {
                con = DriverManager.getConnection(url,"root","");
            }
            
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return con;
    }
}
