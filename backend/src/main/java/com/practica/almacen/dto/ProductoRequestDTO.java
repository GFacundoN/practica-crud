package com.practica.almacen.dto;

import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) de REQUEST
 *
 * ¿Qué es un DTO?
 * Es un objeto que usamos para transportar datos entre el cliente y el servidor.
 * Separamos la entidad (lo que va a la BD) del DTO (lo que recibimos del frontend)
 * por seguridad y buenas prácticas.
 *
 * ¿Por qué no usar la entidad directamente?
 * 1. Seguridad: el cliente no debería poder setear el ID
 * 2. Flexibilidad: podemos recibir datos diferentes a los que guardamos
 * 3. Validación: podemos validar los datos antes de procesarlos
 *
 * Anotaciones de validación (@Valid en el Controller activa estas validaciones):
 * - @NotBlank: no puede ser null ni vacío (para Strings)
 * - @NotNull: no puede ser null (para números, objetos)
 * - @Positive: debe ser un número positivo
 * - @PositiveOrZero: debe ser cero o positivo
 * - @Size: limita el tamaño del String
 */
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "La cantidad es obligatoria")
    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede superar los 50 caracteres")
    private String categoria;

    // Constructor vacío
    public ProductoRequestDTO() {}

    // Constructor con todos los campos
    public ProductoRequestDTO(String nombre, String descripcion, Double precio, Integer cantidad, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Getters y Setters
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
}
