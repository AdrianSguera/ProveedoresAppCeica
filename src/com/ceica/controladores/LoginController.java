package com.ceica.controladores;

public class LoginController {

    public static boolean login(String usu, String con){
        String contrasenia = "1234";
        String usuario = "admin";
        return usuario.equals(usu) & contrasenia.equals(con);
    }
}
