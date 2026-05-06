import { useState, useEffect } from "react";
import { Producto, ProductoRequest } from "./types/Producto";
import {
  obtenerProductos,
  crearProducto,
  actualizarProducto,
  eliminarProducto,
} from "./services/productoService";
import ProductoForm from "./components/ProductoForm";
import ProductoList from "./components/ProductoList";
import "./App.css";

/**
 * COMPONENTE PRINCIPAL: App
 *
 * Aquí se orquesta toda la lógica del CRUD.
 * Maneja el estado global de los productos y coordina
 * las operaciones entre el formulario y la lista.
 */
function App() {
  // === ESTADOS ===
  const [productos, setProductos] = useState<Producto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [editingProduct, setEditingProduct] = useState<Producto | null>(null);
  const [notification, setNotification] = useState<{
    message: string;
    type: "success" | "error";
  } | null>(null);

  // === EFECTOS ===
  // Cargar productos al montar el componente
  useEffect(() => {
    cargarProductos();
  }, []);

  // Auto-ocultar notificación después de 3 segundos
  useEffect(() => {
    if (notification) {
      const timer = setTimeout(() => setNotification(null), 3000);
      return () => clearTimeout(timer);
    }
  }, [notification]);

  // === FUNCIONES ===
  const showNotification = (message: string, type: "success" | "error") => {
    setNotification({ message, type });
  };

  // READ: Cargar todos los productos
  const cargarProductos = async () => {
    try {
      setLoading(true);
      const data = await obtenerProductos();
      setProductos(data);
    } catch (error) {
      console.error("Error al cargar productos:", error);
      showNotification(
        "Error al cargar productos. ¿Está corriendo el backend?",
        "error"
      );
    } finally {
      setLoading(false);
    }
  };

  // CREATE: Crear un nuevo producto
  const handleCreate = async (productoData: ProductoRequest) => {
    try {
      await crearProducto(productoData);
      showNotification("✅ Producto creado exitosamente", "success");
      cargarProductos(); // Recargar la lista
    } catch (error) {
      console.error("Error al crear producto:", error);
      showNotification("Error al crear el producto", "error");
    }
  };

  // UPDATE: Actualizar un producto existente
  const handleUpdate = async (productoData: ProductoRequest) => {
    if (!editingProduct) return;

    try {
      await actualizarProducto(editingProduct.id, productoData);
      showNotification("✅ Producto actualizado exitosamente", "success");
      setEditingProduct(null); // Salir del modo edición
      cargarProductos(); // Recargar la lista
    } catch (error) {
      console.error("Error al actualizar producto:", error);
      showNotification("Error al actualizar el producto", "error");
    }
  };

  // DELETE: Eliminar un producto
  const handleDelete = async (id: number) => {
    const confirmar = window.confirm(
      "¿Estás seguro de que querés eliminar este producto?"
    );

    if (!confirmar) return;

    try {
      await eliminarProducto(id);
      showNotification("✅ Producto eliminado exitosamente", "success");
      cargarProductos(); // Recargar la lista
    } catch (error) {
      console.error("Error al eliminar producto:", error);
      showNotification("Error al eliminar el producto", "error");
    }
  };

  // Preparar edición
  const handleEdit = (producto: Producto) => {
    setEditingProduct(producto);
    window.scrollTo({ top: 0, behavior: "smooth" }); // Scroll al formulario
  };

  // Cancelar edición
  const handleCancelEdit = () => {
    setEditingProduct(null);
  };

  return (
    <>
      <div className="app">
        {/* Header */}
        <header className="app-header">
          <div className="header-content">
            <h1>
              <span className="header-icon">🏪</span>
              Almacén CRUD
            </h1>
            <p className="header-subtitle">
              Sistema de gestión de inventario — Práctica con pasantes
            </p>
          </div>
        </header>

        {/* Notificación */}
        {notification && (
          <div className={`notification notification-${notification.type}`}>
            {notification.message}
          </div>
        )}

        {/* Contenido principal */}
        <main className="main-content">
          {/* Formulario */}
          <section className="form-section">
            <ProductoForm
              onSubmit={editingProduct ? handleUpdate : handleCreate}
              initialData={
                editingProduct
                  ? {
                      nombre: editingProduct.nombre,
                      descripcion: editingProduct.descripcion,
                      precio: editingProduct.precio,
                      cantidad: editingProduct.cantidad,
                      categoria: editingProduct.categoria,
                    }
                  : null
              }
              onCancel={editingProduct ? handleCancelEdit : undefined}
            />
          </section>

          {/* Lista de productos */}
          <section className="list-section">
            <ProductoList
              productos={productos}
              onEdit={handleEdit}
              onDelete={handleDelete}
              loading={loading}
            />
          </section>
        </main>

        {/* Footer */}
        <footer className="app-footer">
          <p>
            Práctica CRUD/ABM — React + Spring Boot + MySQL — 2026
          </p>
        </footer>
      </div>
    </> 
  );
}

export default App;
