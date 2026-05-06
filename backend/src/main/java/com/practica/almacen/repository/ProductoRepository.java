package com.practica.almacen.repository;

import com.practica.almacen.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * REPOSITORY: ProductoRepository
 *
 * ¿Qué es un Repository?
 * Es la capa que se comunica directamente con la base de datos.
 * Spring Data JPA nos provee métodos CRUD automáticamente al extender JpaRepository.
 *
 * JpaRepository<Producto, Long> significa:
 * - Producto: la entidad que maneja este repositorio
 * - Long: el tipo de dato del ID de la entidad
 *
 * Métodos que hereda automáticamente (no necesitamos escribirlos):
 * - findAll()        → SELECT * FROM productos
 * - findById(id)     → SELECT * FROM productos WHERE id = ?
 * - save(producto)   → INSERT INTO productos ... (o UPDATE si ya existe)
 * - deleteById(id)   → DELETE FROM productos WHERE id = ?
 * - existsById(id)   → SELECT COUNT(*) FROM productos WHERE id = ?
 * - count()          → SELECT COUNT(*) FROM productos
 *
 * También podemos crear queries personalizadas simplemente declarando métodos
 * con nombres específicos (Spring los implementa automáticamente):
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca productos por categoría.
     * Spring genera automáticamente: SELECT * FROM productos WHERE categoria = ?
     */
    List<Producto> findByCategoria(String categoria);

    /**
     * Busca productos cuyo nombre contenga el texto dado (búsqueda parcial).
     * Spring genera: SELECT * FROM productos WHERE nombre LIKE '%texto%'
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
