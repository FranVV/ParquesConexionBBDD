/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author alumno
 */
public class Conexion {

    private String url;
    private String usuario;
    private String contraseña;
    private Connection con;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3307/parques";
        this.usuario = "root";
        this.contraseña = "usbw";
    }

    public Conexion(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public void conectar() {
        try {
            con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int añadirParque(String nombre,double extension,int idCiudad) {
        Statement st;
        int rs = 0;
        try {
            conectar();
            st = con.createStatement();
            rs = st.executeUpdate("Insert into parque values('"+0+"','"+nombre+"','"+extension+"','"+idCiudad+"')");
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    //        ◦ Listar todos los parques de una determinada ciudad por nombre.
    public ArrayList<String>  listarParqueporCiudad(String nombreciudad) {
        Statement st;
       ArrayList<String> vString = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from parque where idCiudades in(select id from ciudad where nombre='"+nombreciudad+"')");
           while (rs.next()) {
                vString.add(rs.getString(2));
            }
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vString;
    }
    //  Añadir un parque a una determinada ciudad (por nombre de ciudad), si la ciudad no existe no se añade y se informa de ello.
    public boolean añadirPaqueCiudad(String nombreciudad,String nombreParque,double extension) {
        Statement st;
       ArrayList<String> vString = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ciudad where nombre='"+nombreciudad+"'");
           while (rs.next()) {
                vString.add(rs.getString(1));
            }
            int rsa = st.executeUpdate("Insert into parque values(0,'"+nombreParque+"','"+extension+"','"+vString.get(0)+"')");
            desconectar();
            if(rsa!=0){
            return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //        ◦ Actualizar la información de un parque (primero se pedirá al usuario el nombre del parque que quiere actualizar,
    //se buscará en la base de datos y se mostrarán los datos del parque  (nombre, nombre ciudad, extensión) y a continuación se pedirán 
          //  los 3 nuevos datos (nombre, nombre ciudad y extensión) y se hará la actualización.
    public ArrayList<String> actualizarinfoParque(String nombreParque) {
        Statement st;
         Scanner leer1 = new Scanner(System.in);
         Scanner leer2 = new Scanner(System.in);
         Scanner leer3 = new Scanner(System.in);
         
       ArrayList<String> vString = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from parque where nombre='"+nombreParque+"'");
            
           while (rs.next()) {
           vString.add(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
           System.out.println(vString);
            System.out.println("Dime el nuevo nombre");
            vString.add(leer1.nextLine());//1
            System.out.println("Dime el nuevo Id Ciudad");
            vString.add(leer2.nextLine());//2
            System.out.println("Dime el nuevo extension");
            vString.add(leer3.nextLine());//3
            }
            int rsa = st.executeUpdate("UPDATE  parques.parque SET  nombre =  '"+vString.get(1)+"',extension =  '"+vString.get(3)+"',idciudades =  '"+vString.get(2)+"' WHERE  parque.id ='"+rs.getString(1)+"'");
            desconectar();
            return vString;
            
        } catch (SQLException ex) {
            System.out.println("Nombre no encontrado");;
        }
        return vString;
    }
    //        ◦ Seleccionar todos los parques cuyo nombre contenga una determinada cadena.
    public ArrayList<String> listarParqueconNombre(String nombreParque) {
        Statement st;
         Scanner leer1 = new Scanner(System.in);
         Scanner leer2 = new Scanner(System.in);
         Scanner leer3 = new Scanner(System.in);
         
       ArrayList<String> vString = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from parque where nombre='"+nombreParque+"'");
            
           while (rs.next()) {
           vString.add(rs.getString(2));
           
            }
            
            desconectar();
            return vString;
            
        } catch (SQLException ex) {
            System.out.println("Nombre no encontrado");;
        }
        return vString;
    }
    //        ◦ Devolver el  número de parques de una determinada ciudad.
    public int numeroParquesPorCiudad(String nombreciudad) {
       Statement st;
       ArrayList<String> vString = new ArrayList<>();
       ArrayList<String> vString2 = new ArrayList<>();
       int numeroParques=0;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ciudad where nombre='"+nombreciudad+"'");
           while (rs.next()) {
                vString.add(rs.getString(1));
            }
            ResultSet rsa = st.executeQuery("select * from parque where idciudades='"+vString.get(0)+"'");
            while (rsa.next()) {
                vString2.add(rsa.getString(1));
            }
           numeroParques=vString2.size();
            desconectar();
        return numeroParques;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroParques;
    }
          //  ◦ Listar el nombre de todas las ciudades que contengan parques con una extensión mayor a la que quiera el usuario.
    public ArrayList<String> listarNombreCiudadesExtensionmayorde(double extension) {
       Statement st;
       ArrayList<String> vString = new ArrayList<>();
       ArrayList<String> vString2 = new ArrayList<>();
       int numeroParques=0;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rsa = st.executeQuery("select nombre from ciudad where id in(select idciudades from parque where extension>='"+extension+"')");
            while (rsa.next()) {
                vString2.add(rsa.getString(1));
            }
            desconectar();
        return vString2;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vString2;
    }
    //        ◦ Borrar todos los parques de una determinada ciudad por nombre.
    
    public boolean BorrartodosParquesPorCiudad(String nombreciudad) {
       Statement st;
       ArrayList<String> vString = new ArrayList<>();
       ArrayList<String> vString2 = new ArrayList<>();
       int numeroParques=0;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ciudad where nombre='"+nombreciudad+"'");
            
            while (rs.next()) {
                vString.add(rs.getString(1));
            }
            st = con.createStatement();
            int rsa = st.executeUpdate("DELETE FROM `parques`.`parque` WHERE `parque`.`idciudades` = '"+vString.get(0)+"'");
            desconectar();
        if(rsa!=0){
            return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int añadirCiudad(String nombre) {
        Statement st;
        int rs = 0;
        try {
            conectar();
            st = con.createStatement();
            rs = st.executeUpdate("Insert into ciudad values('"+0+"','"+nombre+"')");
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * ALTER TABLE nombre_tabla ADD [CONSTRAINT símbolo] FOREIGN KEY(...)
REFERENCES otra_tabla(...) [acciones_ON_DELETE][acciones_ON_UPDATE]
     */
    
      public boolean añadirForanea() {
        Statement st;
        int rs = 0;
        try {
            conectar();
            st = con.createStatement();
            rs = st.executeUpdate("ALTER TABLE parque ADD CONSTRAINT fk_ciudad FOREIGN KEY(idCiudades) " +
"REFERENCES ciudad(Id);");
            desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
