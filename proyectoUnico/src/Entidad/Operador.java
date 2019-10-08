/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Byrich
 */
public class Operador {
    public long id;
    public String nombre;
    public String pass;
    public long id_agencia;

    public Operador(long id, String nombre, String pass, long id_agencia) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.id_agencia = id_agencia;
    }
    
    
    @Override
    public String toString(){
        return String.format("Nombre: %s, Fecha nacimiento: %d",this.nombre, this.id);
    }
    
    
    
}



