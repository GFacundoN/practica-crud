import { Producto } from "../types/Producto";
import ProductoCard from "./ProductoCard";

/**
 * COMPONENTE: ProductoList
 *
 * Lista de productos en formato grilla.
 * Muestra un mensaje si no hay productos.
 */

interface ProductoListProps {
  productos: Producto[];
  onEdit: (producto: Producto) => void;
  onDelete: (id: number) => void;
  loading: boolean;
}

const ProductoList = ({
  productos,
  onEdit,
  onDelete,
  loading,
}: ProductoListProps) => {
  if (loading) {
    return (
      <div className="loading-container">
        <div className="spinner"></div>
        <p>Cargando productos...</p>
      </div>
    );
  }

  if (productos.length === 0) {
    return (
      <div className="empty-state">
        <span className="empty-icon">📭</span>
        <h3>No hay productos en el almacén</h3>
        <p>Comenzá agregando tu primer producto con el formulario de arriba.</p>
      </div>
    );
  }

  return (
    <div className="producto-list">
      <div className="list-header">
        <h2>📋 Inventario ({productos.length} productos)</h2>
      </div>
      <div className="cards-grid">
        {productos.map((producto) => (
          <ProductoCard
            key={producto.id}
            producto={producto}
            onEdit={onEdit}
            onDelete={onDelete}
          />
        ))}
      </div>
    </div>
  );
};

export default ProductoList;
