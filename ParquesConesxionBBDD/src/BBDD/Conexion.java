/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public int añadirCliente(String telefono,String nombre,String direccion) {
        Statement st;
        int rs = 0;
        try {
            conectar();
            st = con.createStatement();
            rs = st.executeUpdate("Insert into cliente values('"+nombre+"','"+telefono+"','"+direccion+"')");
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
            rs = st.executeUpdate("ALTER TABLE parques ADD CONSTRAINT fk_ciudad FOREIGN KEY(idCiudades) " +
"REFERENCES ciudad(Id);");
            desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
