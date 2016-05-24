/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *https://www.youtube.com/watch?v=lGVriqnxysU&list=PLMTiAh6qhda2-RucubaToZXaIJL2vOC4l&index=5
 * 
 * https://youtu.be/MUYgDJ97KAA
 * 
 * https://www.youtube.com/watch?v=hyx6SxTcRT8  7 minutos
 * @author hugo
 */
public class Main {
     
    /**
     * @param args the command line arguments
     * newo
     * ConectoToDB
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CBD ctb= new CBD();
        V v = new V();
         
         //conectToDB.consulta();
         //ctb.insertarImg("lluis","/home/hugo/NetBeansProjects/MySQLImagen/src/mysql1/img/imagen3.jpeg");
         //ctb.consultaImagene("lluis");
         v.setLocationRelativeTo(null);
         v.setVisible(true);
         //v.Mostrar();
    }
    
}
