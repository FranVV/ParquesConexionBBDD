/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author yo
 */
public class Parque {
private int id;    
private String nombre;    
private double extension;    
private int idCuidad;

    public Parque(int id, String nombre, double extension, int idCuidad) {
        this.id = id;
        this.nombre = nombre;
        this.extension = extension;
        this.idCuidad = idCuidad;
    }

    public Parque( String nombre, double extension) {
        this.id = 0;
        this.nombre = nombre;
        this.extension = extension;
        this.idCuidad = 0;
    }

    @Override
    public String toString() {
        return "id=" + getId() + ", nombre=" + getNombre() + ", extension=" + getExtension() + ", idCuidad=" + getIdCuidad();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the extension
     */
    public double getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(double extension) {
        this.extension = extension;
    }

    /**
     * @return the idCuidad
     */
    public int getIdCuidad() {
        return idCuidad;
    }

    /**
     * @param idCuidad the idCuidad to set
     */
    public void setIdCuidad(int idCuidad) {
        this.idCuidad = idCuidad;
    }
    
}
