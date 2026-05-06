import { useState, useEffect } from "react";
import { ProductoRequest } from "../types/Producto";

/**
 * COMPONENTE: ProductoForm
 *
 * Formulario reutilizable para crear y editar productos.
 *
 * Props:
 * - onSubmit: función que se ejecuta al enviar el formulario
 * - initialData: datos iniciales (para edición), si es null es creación
 * - onCancel: función para cancelar la edición
 */

interface ProductoFormProps {
  onSubmit: (producto: ProductoRequest) => void;
  initialData?: ProductoRequest | null;
  onCancel?: () => void;
}

// Categorías predefinidas para el almacén
const CATEGORIAS = [
  "Alimentos",
  "Bebidas",
  "Limpieza",
  "Electrónica",
  "Herramientas",
  "Otros",
];

const ProductoForm = ({ onSubmit, initialData, onCancel }: ProductoFormProps) => {
  // Estado del formulario
  const [formData, setFormData] = useState<ProductoRequest>({
    nombre: "",
    descripcion: "",
    precio: 0,
    cantidad: 0,
    categoria: "Alimentos",
  });

  const [errors, setErrors] = useState<Record<string, string>>({});

  // Si recibimos datos iniciales (edición), los cargamos en el formulario
  useEffect(() => {
    if (initialData) {
      setFormData(initialData);
    }
  }, [initialData]);

  // Maneja los cambios en los inputs
  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "precio" || name === "cantidad" ? Number(value) : value,
    }));

    // Limpia el error del campo al modificarlo
    if (errors[name]) {
      setErrors((prev) => ({ ...prev, [name]: "" }));
    }
  };

  // Validación del formulario (lado cliente)
  const validate = (): boolean => {
    const newErrors: Record<string, string> = {};

    if (!formData.nombre.trim()) {
      newErrors.nombre = "El nombre es obligatorio";
    }
    if (formData.precio <= 0) {
      newErrors.precio = "El precio debe ser mayor a 0";
    }
    if (formData.cantidad < 0) {
      newErrors.cantidad = "La cantidad no puede ser negativa";
    }
    if (!formData.categoria) {
      newErrors.categoria = "La categoría es obligatoria";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Maneja el envío del formulario
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (validate()) {
      onSubmit(formData);

      // Resetear formulario solo si es creación
      if (!initialData) {
        setFormData({
          nombre: "",
          descripcion: "",
          precio: 0,
          cantidad: 0,
          categoria: "Alimentos",
        });
      }
    }
  };

  const isEditing = !!initialData;

  return (
    <form className="producto-form" onSubmit={handleSubmit}>
      <h2 className="form-title">
        <span className="form-icon">{isEditing ? "✏️" : "📦"}</span>
        {isEditing ? "Editar Producto" : "Nuevo Producto"}
      </h2>

      <div className="form-grid">
        <div className="form-group">
          <label htmlFor="nombre">Nombre *</label>
          <input
            type="text"
            id="nombre"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            placeholder="Ej: Coca-Cola 500ml"
            className={errors.nombre ? "input-error" : ""}
          />
          {errors.nombre && <span className="error-msg">{errors.nombre}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="categoria">Categoría *</label>
          <select
            id="categoria"
            name="categoria"
            value={formData.categoria}
            onChange={handleChange}
            className={errors.categoria ? "input-error" : ""}
          >
            {CATEGORIAS.map((cat) => (
              <option key={cat} value={cat}>
                {cat}
              </option>
            ))}
          </select>
          {errors.categoria && (
            <span className="error-msg">{errors.categoria}</span>
          )}
        </div>

        <div className="form-group">
          <label htmlFor="precio">Precio ($) *</label>
          <input
            type="number"
            id="precio"
            name="precio"
            value={formData.precio}
            onChange={handleChange}
            min="0"
            step="0.01"
            className={errors.precio ? "input-error" : ""}
          />
          {errors.precio && <span className="error-msg">{errors.precio}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="cantidad">Cantidad *</label>
          <input
            type="number"
            id="cantidad"
            name="cantidad"
            value={formData.cantidad}
            onChange={handleChange}
            min="0"
            className={errors.cantidad ? "input-error" : ""}
          />
          {errors.cantidad && (
            <span className="error-msg">{errors.cantidad}</span>
          )}
        </div>

        <div className="form-group full-width">
          <label htmlFor="descripcion">Descripción</label>
          <textarea
            id="descripcion"
            name="descripcion"
            value={formData.descripcion}
            onChange={handleChange}
            placeholder="Descripción del producto (opcional)"
            rows={3}
          />
        </div>
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {isEditing ? "💾 Guardar Cambios" : "➕ Agregar Producto"}
        </button>
        {isEditing && onCancel && (
          <button
            type="button"
            className="btn btn-secondary"
            onClick={onCancel}
          >
            ✖ Cancelar
          </button>
        )}
      </div>
    </form>
  );
};

export default ProductoForm;
