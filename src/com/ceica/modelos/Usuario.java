package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String usu, pass;

    public Usuario() {
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static List<Usuario> getUsuariosBD() {
        List<Usuario> usuarioList = new ArrayList<>();
        Connection connection = Conexion.conectar();
        String consulta = "select * from usuarios";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsu(resultSet.getString("usuario"));
                usuario.setPass(resultSet.getString("password"));
                usuarioList.add(usuario);
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return usuarioList;
        }
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
        return usuarioList;
    }
}
