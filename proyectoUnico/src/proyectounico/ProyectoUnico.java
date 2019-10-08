/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectounico;

import Controlador.conexion;
import UI.Principal_ui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Byrich
 */
public class ProyectoUnico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*ResultSet rs;
        Connection conn;
        Statement stmt;
        try {
            //conn = DriverManager.getConnection("jdbc:oracle:thin:192.168.43.41:1522/BASESDATOS2","bd2","Basesdatos2");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.189.115:1522/BASESDATOS2","bd2","Basesdatos2");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.175.171:1521:byrich","byRich","24490024");
            stmt = conn.createStatement();  
        } catch (SQLException ex) { 
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        conexion global = conexion.getInstance();
        Principal_ui asdf = new Principal_ui();
        asdf.setVisible(true);//*/
        
    }
    
}
