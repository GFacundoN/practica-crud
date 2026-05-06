package com.practica.almacen.controller;

import com.practica.almacen.dto.ProductoRequestDTO;
import com.practica.almacen.dto.ProductoResponseDTO;
import com.practica.almacen.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER: ProductoController
 * Recibe peticiones HTTP del frontend y devuelve respuestas JSON.
 * 
 * @RestController = @Controller + @ResponseBody
 * @CrossOrigin permite peticiones desde el frontend (Vite puerto 5173)
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // POST /api/productos - Crear producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO productoCreado = productoService.crearProducto(dto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    // GET /api/productos - Obtener todos (con filtros opcionales)
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nombre) {

        List<ProductoResponseDTO> productos;

        if (categoria != null && !categoria.isEmpty()) {
            productos = productoService.buscarPorCategoria(categoria);
        } else if (nombre != null && !nombre.isEmpty()) {
            productos = productoService.buscarPorNombre(nombre);
        } else {
            productos = productoService.obtenerTodos();
        }
        return ResponseEntity.ok(productos);
    }

    // GET /api/productos/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        ProductoResponseDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    // PUT /api/productos/{id} - Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO productoActualizado = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok(productoActualizado);
    }

    // DELETE /api/productos/{id} - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
