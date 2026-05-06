package com.practica.almacen.dto;

import com.practica.almacen.entity.Producto;

/**
 * DTO de RESPONSE
 *
 * Este DTO es lo que devolvemos al frontend.
 * Incluye el ID (que el request no tiene) porque el frontend
 * necesita el ID para identificar cada producto.
 *
 * El método estático fromEntity() convierte una Entidad a DTO.
 * Esto se conoce como "mapper" o "converter".
 */
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private String categoria;

    // Constructor vacío
    public ProductoResponseDTO() {}

    // Constructor con todos los campos
    public ProductoResponseDTO(Long id, String nombre, String descripcion, Double precio, Integer cantidad, String categoria) {
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

    /**
     * Convierte una entidad Producto a un ProductoResponseDTO.
     * Se usa para no exponer la entidad directamente al cliente.
     *
     * @param producto la entidad que viene de la base de datos
     * @return un DTO listo para enviar al frontend
     */
    public static ProductoResponseDTO fromEntity(Producto producto) {
        return new ProductoResponseDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCantidad(),
            producto.getCategoria()
        );
    }
}
