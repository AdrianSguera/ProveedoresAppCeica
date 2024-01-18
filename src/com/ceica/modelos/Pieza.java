package com.ceica.modelos;

public class Pieza {
    private static int idPieza = 0;
    private int id;
    private String nombre, color;
    private Double precio;
    private Categoria categoria;

    public Pieza() {
        this.id = idPieza++;
    }

    public Pieza(String nombre, Color color, Double precio) {
        this.id = idPieza++;
        this.nombre = nombre;
        this.color = color.toString();
        this.precio = precio;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nPieza{" +
                "ID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Color='" + color + '\'' +
                ", Categoría='" + categoria + '\'' +
                ", Precio=" + precio +
                '}';
    }
}
