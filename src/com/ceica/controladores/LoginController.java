package com.ceica.controladores;

import com.ceica.modelos.Usuario;

import java.util.List;

public class LoginController {

    public static boolean login(String usu, String pass){
        List<Usuario> usuarioList = Usuario.getUsuariosBD();
        return usuarioList.stream()
                .anyMatch(usuario -> usu.equals(usuario.getUsu()) && pass.equals(usuario.getPass()));
    }
}
