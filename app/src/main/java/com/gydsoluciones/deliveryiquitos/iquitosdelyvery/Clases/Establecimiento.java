package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases;

/**
 * Created by grva on 26/02/2018.
 */

public class Establecimiento {

    ServiciosRest serviciosRest = new ServiciosRest();
    private int id_establecimiento;
    private String nombre;
    private String imagen = serviciosRest.getUrlImgEstablecimiento();

    public Establecimiento(int id_establecimiento, String nombre, String imagen) {
        this.id_establecimiento = id_establecimiento;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getId_establecimiento() {
        return id_establecimiento;
    }

    public void setId_establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
