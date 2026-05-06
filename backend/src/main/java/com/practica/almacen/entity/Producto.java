package com.practica.almacen.entity;

import jakarta.persistence.*;

/**
 * ENTIDAD: Producto
 *
 * Esta clase representa la tabla "productos" en la base de datos.
 * Cada atributo de la clase se convierte en una columna de la tabla.
 *
 * Anotaciones de JPA:
 * - @Entity: marca esta clase como una entidad de base de datos
 * - @Table: especifica el nombre de la tabla en MySQL
 * - @Id: indica la clave primaria
 * - @GeneratedValue: el ID se genera automáticamente (autoincremental)
 * - @Column: configura propiedades de la columna (nombre, si puede ser null, etc.)
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, length = 50)
    private String categoria;

    // Constructor vacío (requerido por JPA)
    public Producto() {}

    // Constructor con todos los campos
    public Producto(Long id, String nombre, String descripcion, Double precio, Integer cantidad, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Builder pattern manual para facilitar creación
    public static class Builder {
        private Long id;
        private String nombre;
        private String descripcion;
        private Double precio;
        private Integer cantidad;
        private String categoria;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder precio(Double precio) {
            this.precio = precio;
            return this;
        }

        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public Producto build() {
            return new Producto(id, nombre, descripcion, precio, cantidad, categoria);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
