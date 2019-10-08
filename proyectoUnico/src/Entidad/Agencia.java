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
public class Agencia {
    public long id;
    public String nombre;
    public int estado;

    public Agencia(long id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
