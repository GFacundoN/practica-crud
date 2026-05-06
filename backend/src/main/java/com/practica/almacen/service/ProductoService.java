package com.practica.almacen.service;

import com.practica.almacen.dto.ProductoRequestDTO;
import com.practica.almacen.dto.ProductoResponseDTO;
import com.practica.almacen.entity.Producto;
import com.practica.almacen.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SERVICE: ProductoService
 *
 * ¿Qué es un Service?
 * Es la capa donde va la LÓGICA DE NEGOCIO de la aplicación.
 * Actúa como intermediario entre el Controller y el Repository.
 *
 * Flujo de una petición HTTP:
 * Cliente (Frontend) → Controller → Service → Repository → Base de Datos
 *
 * ¿Por qué no poner la lógica directamente en el Controller?
 * 1. Separación de responsabilidades (cada capa tiene su función)
 * 2. Reutilización (el mismo servicio puede ser usado por varios controllers)
 * 3. Testabilidad (es más fácil testear la lógica de negocio aislada)
 *
 * @Service marca esta clase para que Spring la registre como un bean
 * y la pueda inyectar donde se necesite.
 */
@Service
public class ProductoService {

    // Inyección de dependencias por constructor (la forma recomendada)
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ==========================================
    // CREATE - Crear un nuevo producto
    // ==========================================
    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param dto los datos del producto que llegan del frontend
     * @return el producto creado con su ID asignado
     */
    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        // 1. Convertimos el DTO a una entidad
        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .cantidad(dto.getCantidad())
                .categoria(dto.getCategoria())
                .build();

        // 2. Guardamos la entidad en la base de datos
        Producto productoGuardado = productoRepository.save(producto);

        // 3. Convertimos la entidad guardada a un DTO de respuesta y lo retornamos
        return ProductoResponseDTO.fromEntity(productoGuardado);
    }

    // ==========================================
    // READ - Obtener productos
    // ==========================================
    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return lista de todos los productos
     */
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()                                    // Convertimos la lista a un Stream
                .map(ProductoResponseDTO::fromEntity)        // Transformamos cada entidad a DTO
                .collect(Collectors.toList());               // Recolectamos en una nueva lista
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto a buscar
     * @return el producto encontrado
     * @throws RuntimeException si el producto no existe
     */
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        return ProductoResponseDTO.fromEntity(producto);
    }

    /**
     * Busca productos por categoría.
     */
    public List<ProductoResponseDTO> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(ProductoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Busca productos por nombre (búsqueda parcial).
     */
    public List<ProductoResponseDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(ProductoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ==========================================
    // UPDATE - Actualizar un producto existente
    // ==========================================
    /**
     * Actualiza un producto existente.
     *
     * @param id  el ID del producto a actualizar
     * @param dto los nuevos datos del producto
     * @return el producto actualizado
     * @throws RuntimeException si el producto no existe
     */
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {
        // 1. Buscamos el producto existente (si no existe, lanza excepción)
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // 2. Actualizamos los campos con los nuevos datos
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        producto.setCategoria(dto.getCategoria());

        // 3. Guardamos los cambios (JPA detecta que ya tiene ID y hace UPDATE)
        Producto productoActualizado = productoRepository.save(producto);

        // 4. Retornamos el producto actualizado como DTO
        return ProductoResponseDTO.fromEntity(productoActualizado);
    }

    // ==========================================
    // DELETE - Eliminar un producto
    // ==========================================
    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar
     * @throws RuntimeException si el producto no existe
     */
    public void eliminarProducto(Long id) {
        // Verificamos que el producto exista antes de eliminarlo
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        productoRepository.deleteById(id);
    }
}
