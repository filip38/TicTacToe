/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupa1
 */
public class DBConnection {
    static Connection conn = null;
    static DBConnection db =null;
    private String url = "jdbc:mysql://localhost:3306/tictactoe";
    private String user = "root";
    private String password = "";           
    private DBConnection()
    {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConn(){
        if(db == null)
            db = new DBConnection();
        return conn;
    }
}
