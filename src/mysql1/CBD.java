/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql1;

import com.mysql.jdbc.Connection;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *insert into Java.Tabla1(nombre,edad) values ("hugo", 19),("lluis",30),("pere",15);
 * @author hugo
 */
public class CBD {
    
    //declarar Objetos coenxion
    Connection miConexion;
    PreparedStatement  consulta;
    ResultSet datos;
    ImageIcon icon=null,ajuste=null;
    BufferedImage img = null;
    
    //clase crear obtenerConexion
   public Connection getConnection(String BBDD, String usuario, String contraseña)
    {
        try {
            //llamar a al clase drive de jdbc
            Class.forName("com.mysql.jdbc.Driver");
            //despes // iria dirrecion si es web pues web y la BBDD especifica
            String servidor = "jdbc:mysql://localhost:3306/"+BBDD;
            
            miConexion = (Connection) DriverManager.getConnection(servidor, usuario, contraseña);
        } catch (ClassNotFoundException e  ) {
            JOptionPane.showMessageDialog(null,"No se encontroDriver");
                miConexion=null;
        }catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo conectar BBDD"+ex);
                miConexion=null;
             }
        return miConexion;
    }
    
    
    
    public void insertar()
    {
        try {
            //conexion BBDD especifica
            miConexion= (Connection) this.getConnection("Java","root","antonia");
            String insertTableSQL = "insert into Tabla1(nombre,edad) values"
		+ "(?,?)";
            // enviar consulta
            consulta=miConexion.prepareStatement(insertTableSQL);
            consulta.setInt(2, 11);
            consulta.setString(1, "mkyong");
            
            //ejecutar consulta y guardar consulta executeUpdate();
            //datos= consulta.executeQuery();
            consulta.executeUpdate();
            //mostrar los datos siguiente en siguiente
         
        }catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo hacer insertar " +ex);
        }finally{
            //para descoenctar todo
            this.desconectar();
            consulta();
        }
    }
    
    
    public void consulta()
    {
        try {
            //conexion BBDD especifica
            miConexion= (Connection) this.getConnection("Java","root","antonia");
            // enviar consulta
            consulta=miConexion.prepareStatement("SELECT nombre,edad FROM Java.Tabla1");
            //ejecutar consulta y guardar consulta
            datos= consulta.executeQuery();
            //mostrar los datos siguiente en siguiente
            while(datos.next())
            {
                System.out.println("nombre: "+ datos.getString("nombre")+"  --- edad: "+datos.getInt("edad"));
            }  
        }catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo hacer consulta " +ex);
        }finally{
            //para descoenctar todo
            this.desconectar();
        }
    }
    
    public void desconectar()
    {
        try {
            this.miConexion.close();
            this.consulta.close();
            this.datos= null;
            
        } catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar");
        }
    }
    
    //////Modificar
     public  void insertarImg(String n,String s) throws FileNotFoundException
    {
        try {
            //conexion BBDD especifica
            miConexion= (Connection) this.getConnection("Java","root","antonia");
            String insertTableSQL = "insert into imagenes(nom,imagen) values"
		+ "(?,?)";
            // enviar consulta
            consulta=miConexion.prepareStatement(insertTableSQL);
            consulta.setString(1, n);
            InputStream is= new FileInputStream(new File(s));
            //insertar imatge
            consulta.setBlob(2,is);
            
            //ejecutar consulta y guardar consulta executeUpdate();
            //datos= consulta.executeQuery();
            consulta.executeUpdate();
            //mostrar los datos siguiente en siguiente
            JOptionPane.showMessageDialog(null,"Imatge insertada");
         
        }catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo hacer insertar " +ex);
        }finally{
            //para descoenctar todo
            this.desconectar();
            //consulta();
        }
    }
    
    
    public void consultaImagene(String n) throws IOException
    {
        try {
            //conexion BBDD especifica
            miConexion= (Connection) this.getConnection("Java","root","antonia");
            // enviar consulta
            consulta=miConexion.prepareStatement("SELECT imagen FROM Java.imagenes where nom = ?");
            //ejecutar consulta y guardar consulta
            consulta.setString(1,n);
            datos= consulta.executeQuery();
           datos.next();     
                    Blob blob = datos.getBlob("imagen");
 
                    byte[] data = blob.getBytes(1, (int)blob.length());
                    
                    img = ImageIO.read(new ByteArrayInputStream(data));

              //ajuste= new ImageIcon(icon.getImage().getScaledInstance(LBMissatge.getWidth(),LBMissatge.getHeight(),Image.SCALE_SMOOTH)); 
            //lblImage.setIcon(icon); 
             JOptionPane.showMessageDialog(null,"Imatge vista");
            //mostrar los datos siguiente en siguiente
            //while(datos.next())
            //{
                //System.out.println("nombre: "+ datos.getString("nombre")+"  --- edad: "+datos.getInt("edad"));
            //}  
        }catch ( SQLException ex){
                JOptionPane.showMessageDialog(null,"No se pudo hacer consulta " +ex);
                System.out.println("ex:"+ex);
        }finally{
            //para descoenctar todo
            this.desconectar();
        }
    }
    
    
   
    
}
