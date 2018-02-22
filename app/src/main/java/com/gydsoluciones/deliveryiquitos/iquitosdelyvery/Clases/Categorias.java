package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases;

/**
 * Created by grva on 21/02/2018.
 */

public class Categorias {

    private int id_categoria;
    private String nombre;
    ServiciosRest serviciosRest = new ServiciosRest();
    private String ImagenUrl = serviciosRest.getUrlImgCategoria();

    public Categorias(int id_categoria, String nombre, String ImagenUrl){
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.ImagenUrl = this.ImagenUrl + ImagenUrl;

    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return ImagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        ImagenUrl = imagenUrl;
    }
}
