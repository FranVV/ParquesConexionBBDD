/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import BBDD.Conexion;

/**
 *
 * @author alumno
 */
public class Ejercicios {
     public static void main(String[] args) {
        Conexion con = new Conexion();
         System.out.println(con.añadirForanea());
    }
}
