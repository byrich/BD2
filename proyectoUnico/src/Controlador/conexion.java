/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Byrich
 */
public class conexion {
    
    private static conexion cone;
    private ResultSet rs;
    private java.sql.Connection conn;
    private Statement stmt;
    
    private conexion() {
        try {
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:byrich","byRich","24490024");
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.43.41:1522/BASESDATOS2","bd2","Basesdatos2");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/BASESDATOS2","bd2","Basesdatos2");
            stmt=conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static conexion getInstance(){
        if(cone == null){
            cone = new conexion();
        }
        return cone;
    }
    
    public ResultSet runSql(String sentencia){
        try {  
            this.rs= this.stmt.executeQuery(sentencia);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }
    
    // login
    public ResultSet autenticar(String id, String pass){
        String sql = String.format(
                "Select rol_id_rol from operador where id_operador = %s and password = '%s' and estado = 1"
                , id
                , pass
        );
        return runSql(sql);
    }
    
    public int addChequeTemp(int cuenta, int cheque, float monto) throws SQLException {
        Statement statement = conn.createStatement();
        int rowsAffected = statement.executeUpdate("INSERT INTO TransaccionChequeTmp " + "VALUES (" + cuenta + ", " + cheque + ", " + monto + ")");
        statement.close();
        return rowsAffected;
    }
    
    // registro de cliente
    public boolean addCliente(String dpi, String nombre, String date, File foto, File firma){
        String sql = String.format(
               "Insert into Cliente (dpi,nombre,fecha_nacimiento,estado) values (%s,'%s','%s',1)"
                //"Insert into cliente (dpi,nombre,firma,foto,fecha_nacimiento) values (%s,'%s','firma','foto','%s')"
                , dpi
                , nombre
                ,date
        );
        
        try {  
            this.rs= this.stmt.executeQuery(sql);
            if (rs.next()){
                String sqls = String.format("update cliente set foto=? where dpi=%s"
                ,dpi
                );
                PreparedStatement myStmt = conn.prepareStatement(sqls);
                FileInputStream input;
                try {
                    input = new FileInputStream(foto);
                    myStmt.setBinaryStream(1, input);
                    myStmt.executeUpdate();
                    input.close();
                    myStmt.close();
                    String sqls2 = String.format("update cliente set firma=? where dpi=%s"
                    ,dpi
                    );
                    myStmt = conn.prepareStatement(sqls2);
                    FileInputStream input1;
                    try {
                        input1 = new FileInputStream(firma);
                        myStmt.setBinaryStream(1, input1);
                        myStmt.executeUpdate();
                        input1.close();
                        myStmt.close();
                    } catch (FileNotFoundException ex) {
                        return false;
                    } catch (IOException ex) {
                        return false;
                    }
                } catch (FileNotFoundException ex) {
                    return false;
                } catch (IOException ex) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    // listado de clientes
    public ResultSet getClientes(){
        String sql = "Select * from cliente where estado = 1";
        return runSql(sql);
    }
    
    // datos del cliente* falta
    public ResultSet getCliente(String dpi, String nombre, String date){
        String sql = String.format(
               "Insert into cliente (dpi,nombre,firma,foto,fecha_nacimiento,estado) values (%s,'%s','firma','foto','%s',1)"
                , dpi
                , nombre
                ,date
        );
        return runSql(sql);
    }
    
    // modificar datos del cliente
    public ResultSet editCliente(String dpi, String nombre, String date){
        String sql = String.format(
               "Update cliente set nombre='%s' , fecha_nacimiento = '%s' WHERE dpi = %s and estado = 1"
                , nombre
                , date
                , dpi
        );
        return runSql(sql);
    }
    
    // dar de baja a cliente
    public ResultSet downCliente(String dpi){
        String sql = String.format(
               "UPDATE cliente SET estado = 0 WHERE dpi = %s and estado = 1"
                , dpi
        );
        return runSql(sql);
    }
    
    // registro de operador
    public ResultSet addOperador(String nombre, String pass, long id_agencia){
        String sql = String.format(
               "Insert into operador (nombre,estado,password,Agencia_id_agencia,Terminal_id_terminal,Rol_id_rol) values ('%s',1,'%s',%d,1,2)"
                , nombre
                , pass
                ,id_agencia
        );
        return runSql(sql);
    }
    
    public ResultSet editOperador(String id,String nombre, String pass, long id_agencia){
        String sql = String.format(
               "Update operador set nombre='%s' , password = '%s', Agencia_id_agencia = %d WHERE id_operador = %s and estado = 1"
                , nombre
                , pass
                , id_agencia
                ,id
        );
        return runSql(sql);
    }
    
    // listado de operadores
    public ResultSet getOperadores(){
        String sql = "Select * from operador where estado = 1";
        return runSql(sql);
    }
    
    // dar de baja a cliente
    public ResultSet downOperador(String dpi){
        String sql = String.format(
               "UPDATE operador SET estado = 0 WHERE dpi = %s and estado = 1"
                , dpi
        );
        return runSql(sql);
    }
    
    // registro de agencia
    public ResultSet addAgencia(String nombre){
        String sql = String.format(
               "Insert into agencia (nombre,estado) values ('%s',1)"
                , nombre
        );
        return runSql(sql);
    }
    
    public ResultSet editAgencia(String nombre,String id){
        String sql = String.format(
               "Update agencia set nombre='%s' WHERE id_operador = %s and estado = 1"
                , nombre
                ,id
        );
        return runSql(sql);
    }
    
    // datos del cliente* falta
    public ResultSet getAgencias(){
        String sql = "Select * from agencia where estado = 1";
        return runSql(sql);
    }
    
    public ResultSet downAgencia(String dpi){
        String sql = String.format(
               "UPDATE agencia SET estado = 0 WHERE dpi = %s and estado = 1"
                , dpi
        );
        return runSql(sql);
    }
    
    public boolean evaluarCadena (String campo){
        String PATRON = "[A-Za-z0-9-_? ]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarPass (String campo){
        String PATRON = "[A-Za-z0-9-_?]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarInt (String campo){
        String PATRON = "[0-9]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarFecha (String campo){
        String PATRON = "[0-9]?[0-9][/][0-9]?[0-9][/][0-9][0-9][0-9][0-9]$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
}
