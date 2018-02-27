package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases;

/**
 * Created by grva on 21/02/2018.
 */

public class ServiciosRest {

    private String urlLogin = "http://10.0.2.1/delivery-rest/index.php/login/";
    private String urlCategorias = "http://10.0.2.1/delivery-rest/index.php/categorias/";
    private String urlImgCategoria = "http://10.0.2.1/delivery-rest/public/img/categorias/";
    private String urlEstablecimiento = "http://10.0.2.1/delivery-rest/establecimiento/";
    private String urlImgEstablecimiento = "http://10.0.2.1/delivery-rest/public/img/establecimientos/";

    public String getUrlLogin() {
        return urlLogin;
    }

    public String getUrlImgCategoria() {
        return urlImgCategoria;
    }

    public String getUrlCategorias() {
        return urlCategorias;
    }

    public String getUrlEstablecimiento() {
        return urlEstablecimiento;
    }

    public String getUrlImgEstablecimiento() {
        return urlImgEstablecimiento;
    }
}
