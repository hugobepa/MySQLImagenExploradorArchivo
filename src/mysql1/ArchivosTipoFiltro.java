/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql1;

/**
 *
 * @author hugo
 */
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;



public class ArchivosTipoFiltro extends FileFilter {
    
    private final String extensio;
    private final String descripcio;
    JFileChooser fs = new  JFileChooser();

    public ArchivosTipoFiltro(String extensio, String descripcio) {
        this.extensio = extensio;
        this.descripcio = descripcio;
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory())
        {
            return true;
        }
        
        return f.getName().endsWith(extensio);
    }

    @Override
    public String getDescription() {
        return descripcio + String.format(" (*%s)", extensio);
    }
    
}
