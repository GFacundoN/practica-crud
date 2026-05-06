/**
 * TIPOS DE TYPESCRIPT
 *
 * Definimos las interfaces que representan la estructura de los datos.
 * TypeScript nos ayuda a detectar errores en tiempo de desarrollo
 * (antes de ejecutar el código).
 */

// Interfaz para el producto que recibimos del backend (tiene ID)
export interface Producto {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  cantidad: number;
  categoria: string;
}

// Interfaz para crear/actualizar producto (no tiene ID)
export interface ProductoRequest {
  nombre: string;
  descripcion: string;
  precio: number;
  cantidad: number;
  categoria: string;
}
