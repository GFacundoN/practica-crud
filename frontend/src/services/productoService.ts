import { Producto, ProductoRequest } from "../types/Producto";

/**
 * SERVICIO DE API
 *
 * Este archivo centraliza todas las llamadas HTTP al backend.
 * Usamos la API nativa "fetch" del navegador.
 *
 * ¿Por qué centralizar las llamadas?
 * 1. Si cambia la URL del backend, solo modificamos un archivo
 * 2. Evitamos duplicar código en los componentes
 * 3. Separamos la lógica de comunicación de la lógica de UI
 */

// URL base del backend
const API_URL = "http://localhost:8080/api/productos";

/**
 * Obtiene todos los productos del almacén.
 * Método HTTP: GET
 */
export const obtenerProductos = async (): Promise<Producto[]> => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener los productos");
  }

  return response.json();
};

/**
 * Obtiene un producto por su ID.
 * Método HTTP: GET
 */
export const obtenerProductoPorId = async (id: number): Promise<Producto> => {
  const response = await fetch(`${API_URL}/${id}`);

  if (!response.ok) {
    throw new Error(`Error al obtener el producto con ID ${id}`);
  }

  return response.json();
};

/**
 * Crea un nuevo producto.
 * Método HTTP: POST
 *
 * Nota: debemos indicar en los headers que enviamos JSON
 * y convertir el objeto a string con JSON.stringify()
 */
export const crearProducto = async (
  producto: ProductoRequest
): Promise<Producto> => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(producto),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(JSON.stringify(errorData));
  }

  return response.json();
};

/**
 * Actualiza un producto existente.
 * Método HTTP: PUT
 */
export const actualizarProducto = async (
  id: number,
  producto: ProductoRequest
): Promise<Producto> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(producto),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(JSON.stringify(errorData));
  }

  return response.json();
};

/**
 * Elimina un producto por su ID.
 * Método HTTP: DELETE
 */
export const eliminarProducto = async (id: number): Promise<void> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });

  if (!response.ok) {
    throw new Error(`Error al eliminar el producto con ID ${id}`);
  }
};
