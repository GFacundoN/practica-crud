import { Producto } from "../types/Producto";

/**
 * COMPONENTE: ProductoCard
 *
 * Tarjeta individual que muestra la información de un producto.
 * Incluye botones para editar y eliminar.
 */

interface ProductoCardProps {
  producto: Producto;
  onEdit: (producto: Producto) => void;
  onDelete: (id: number) => void;
}

// Mapeo de categorías a emojis para un toque visual
const CATEGORIA_EMOJI: Record<string, string> = {
  Alimentos: "🍎",
  Bebidas: "🥤",
  Limpieza: "🧹",
  Electrónica: "💻",
  Herramientas: "🔧",
  Otros: "📦",
};

const ProductoCard = ({ producto, onEdit, onDelete }: ProductoCardProps) => {
  const emoji = CATEGORIA_EMOJI[producto.categoria] || "📦";

  // Determina el estado del stock
  const getStockStatus = () => {
    if (producto.cantidad === 0) return { text: "Sin stock", clase: "stock-empty" };
    if (producto.cantidad <= 5) return { text: "Stock bajo", clase: "stock-low" };
    return { text: "En stock", clase: "stock-ok" };
  };

  const stock = getStockStatus();

  return (
    <div className="producto-card">
      <div className="card-header">
        <span className="card-emoji">{emoji}</span>
        <span className={`stock-badge ${stock.clase}`}>{stock.text}</span>
      </div>

      <div className="card-body">
        <h3 className="card-title">{producto.nombre}</h3>
        <p className="card-description">
          {producto.descripcion || "Sin descripción"}
        </p>

        <div className="card-details">
          <div className="detail-item">
            <span className="detail-label">Precio</span>
            <span className="detail-value price">
              ${producto.precio.toFixed(2)}
            </span>
          </div>
          <div className="detail-item">
            <span className="detail-label">Cantidad</span>
            <span className="detail-value">{producto.cantidad} uds.</span>
          </div>
          <div className="detail-item">
            <span className="detail-label">Categoría</span>
            <span className="detail-value category-tag">
              {producto.categoria}
            </span>
          </div>
        </div>
      </div>

      <div className="card-actions">
        <button
          className="btn btn-edit"
          onClick={() => onEdit(producto)}
          title="Editar producto"
        >
          ✏️ Editar
        </button>
        <button
          className="btn btn-delete"
          onClick={() => onDelete(producto.id)}
          title="Eliminar producto"
        >
          🗑️ Eliminar
        </button>
      </div>
    </div>
  );
};

export default ProductoCard;
