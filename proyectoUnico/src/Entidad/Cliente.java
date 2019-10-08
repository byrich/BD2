/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.InputStream;

/**
 *
 * @author Byrich
 */
public class Cliente {
    public long dpi;
    public String nombre;
    public InputStream firma;
    public InputStream foto;
    public String fec_nac;

    public Cliente(long dpi, String nombre, InputStream firma, InputStream foto, String fec_nac) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.firma = firma;
        this.foto = foto;
        this.fec_nac = fec_nac;
    }
    
    
    @Override
    public String toString(){
        return String.format("Nombre: %s, Fecha nacimiento: %s",this.nombre, this.fec_nac);
    }
    
    
    
}



