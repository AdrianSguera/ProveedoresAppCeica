package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private static int idProveedor = 0;
    private int id;
    private String nombre, cif, direccion, localidad, provincia;

    public Proveedor() {
        this.id = idProveedor++;
    }

    public Proveedor(String nombre, String cif) {
        this.id = idProveedor++;
        this.nombre = nombre;
        this.cif = cif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static List<Proveedor> getProveedoresBD() {
        List<Proveedor> proveedorList = new ArrayList<>();
        Connection connection = Conexion.conectar();
        String consulta = "select * from proveedores";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt("id"));
                proveedor.setCif(resultSet.getString("cif"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setDireccion(resultSet.getString("direccion"));
                proveedor.setLocalidad(resultSet.getString("localidad"));
                proveedor.setProvincia(resultSet.getString("provincia"));
                proveedorList.add(proveedor);
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return proveedorList;
        }
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
        return proveedorList;
    }

    public static boolean insertar(Proveedor proveedor) {
        Connection connection = Conexion.conectar();
        String consulta = "insert into proveedores (cif,nombre,direccion,localidad,provincia) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, proveedor.getCif());
            preparedStatement.setString(2, proveedor.getNombre());
            preparedStatement.setString(3, proveedor.getDireccion());
            preparedStatement.setString(4, proveedor.getLocalidad());
            preparedStatement.setString(5, proveedor.getProvincia());
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static boolean eliminar(Proveedor proveedor) {
        Connection connection = Conexion.conectar();
        String consulta = "DELETE FROM proveedores WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1, proveedor.getId());
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static boolean modificarNombre(String cif, String dato) {
        Connection connection = Conexion.conectar();
        String consulta = "UPDATE proveedores SET nombre = ? WHERE cif = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, dato);
            preparedStatement.setString(2, cif);
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static boolean modificarDireccion(String cif, String dato) {
        Connection connection = Conexion.conectar();
        String consulta = "UPDATE proveedores SET direccion = ? WHERE cif = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, dato);
            preparedStatement.setString(2, cif);
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static boolean modificarLocalidad(String cif, String dato) {
        Connection connection = Conexion.conectar();
        String consulta = "UPDATE proveedores SET localidad = ? WHERE cif = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, dato);
            preparedStatement.setString(2, cif);
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static boolean modificarProvincia(String cif, String dato) {
        Connection connection = Conexion.conectar();
        String consulta = "UPDATE proveedores SET provincia = ? WHERE cif = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, dato);
            preparedStatement.setString(2, cif);
            if (preparedStatement.executeUpdate() == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "\nProveedor{" +
                "ID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", CIF='" + cif + '\'' +
                ", Dirección='" + direccion + '\'' +
                ", Localidad='" + localidad + '\'' +
                ", Provincia='" + provincia + '\'' +
                '}';
    }

}
