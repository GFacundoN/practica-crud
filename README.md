# 🏪 CRUD de Almacén - Práctica con Pasantes

Un sistema completo de gestión de inventario desarrollado con **React + TypeScript** (frontend) y **Spring Boot + MySQL** (backend). Ideal para enseñar conceptos básicos de desarrollo web full-stack.

## 📋 Tabla de Contenidos

- [🚀 Cómo ejecutar el proyecto](#-cómo-ejecutar-el-proyecto)
- [🎯 Funcionalidades del sistema](#-funcionalidades-del-sistema)
- [📚 Conceptos CRUD/ABM](#-conceptos-crudabm)
- [🏗️ Arquitectura del proyecto](#️-arquitectura-del-proyecto)
- [🛠️ Tecnologías utilizadas](#️-tecnologías-utilizadas)

---

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos

- **Java 21** o superior
- **Maven 3.6+**
- **Node.js 18+** y **npm**
- **MySQL 8.0+**

### 1️⃣ Configurar la Base de Datos

```sql
-- Crear la base de datos
CREATE DATABASE almacen_db;

-- Usar la base de datos
USE almacen_db;
```

### 2️⃣ Configurar Backend

Editar `backend/src/main/resources/application.properties` si es necesario:

```properties
# URL de conexión a MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/almacen_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

# Credenciales (cambiar según tu configuración)
spring.datasource.username=root
spring.datasource.password=root
```

### 3️⃣ Ejecutar Backend

```bash
cd backend
mvn clean spring-boot:run
```

El backend estará disponible en: **http://localhost:8080**

### 4️⃣ Ejecutar Frontend

```bash
cd frontend
npm install
npm run dev
```

El frontend estará disponible en: **http://localhost:5173**

---

## 🎯 Funcionalidades del sistema

### ✅ Gestión Completa de Productos

| Funcionalidad | Descripción | Endpoint HTTP |
|-------------|-------------|---------------|
| **Crear** | Agregar nuevos productos al inventario | `POST /api/productos` |
| **Leer** | Ver todos los productos o buscar por filtros | `GET /api/productos` |
| **Actualizar** | Modificar datos de productos existentes | `PUT /api/productos/{id}` |
| **Eliminar** | Quitar productos del inventario | `DELETE /api/productos/{id}` |

### 🔍 Búsqueda y Filtrado

- **Búsqueda por nombre**: `GET /api/productos?nombre=coca`
- **Filtro por categoría**: `GET /api/productos?categoria=Bebidas`
- **Listado completo**: `GET /api/productos`

### 📱 Interfaz de Usuario

- **Formulario intuitivo** para crear/editar productos
- **Validación en tiempo real** de datos ingresados
- **Cards visuales** con información del stock
- **Notificaciones** de éxito/error
- **Diseño responsivo** para móviles y desktop

### 📊 Gestión de Inventario

- **Control de stock**: cantidad disponible por producto
- **Categorización**: Alimentos, Bebidas, Limpieza, Electrónica, Herramientas
- **Indicadores visuales**: Stock normal, bajo, sin stock
- **Precios**: Configuración de valores monetarios

---

## 📚 Conceptos CRUD/ABM

### ¿Qué significa CRUD?

**CRUD** son las cuatro operaciones básicas de persistencia de datos:

| Operación | Inglés | Español | HTTP | SQL |
|-----------|----------|----------|------|
| **C**reate | Crear | POST | INSERT |
| **R**ead | Leer | GET | SELECT |
| **U**pdate | Actualizar | PUT/UPDATE | UPDATE |
| **D**elete | Eliminar | DELETE | DELETE |

### ¿Qué significa ABM?

**ABM** es el equivalente en español, muy usado en Argentina:

| Operación | Significado |
|-----------|-------------|
| **A**lta | Agregar nuevos registros |
| **B**aja | Eliminar registros existentes |
| **M**odificación | Cambiar datos de registros |

### 🎯 Importancia en Desarrollo

1. **Base de datos**: Todo sistema necesita guardar información
2. **API REST**: Comunicación entre frontend y backend
3. **Validación**: Proteger la integridad de los datos
4. **Estado de la UI**: Manejar loading, errores, éxito
5. **Experiencia de usuario**: Feedback claro al usuario

---

## 🏗️ Arquitectura del proyecto

```
practica-crud/
├── backend/                    # Spring Boot API
│   ├── src/main/java/com/practica/almacen/
│   │   ├── controller/           # Endpoints REST
│   │   │   └── ProductoController.java
│   │   ├── service/              # Lógica de negocio
│   │   │   └── ProductoService.java
│   │   ├── repository/           # Acceso a datos
│   │   │   └── ProductoRepository.java
│   │   ├── entity/              # Entidades JPA
│   │   │   └── Producto.java
│   │   ├── dto/                # Data Transfer Objects
│   │   │   ├── ProductoRequestDTO.java
│   │   │   └── ProductoResponseDTO.java
│   │   └── exception/           # Manejo de errores
│   │       └── GlobalExceptionHandler.java
│   └── pom.xml               # Configuración Maven
│
└── frontend/                   # React + TypeScript
    ├── src/
    │   ├── components/          # Componentes React
    │   │   ├── ProductoForm.tsx    # Formulario crear/editar
    │   │   ├── ProductoList.tsx    # Lista de productos
    │   │   └── ProductoCard.tsx   # Tarjeta individual
    │   ├── services/           # Llamadas HTTP
    │   │   └── productoService.ts
    │   ├── types/             # Tipos TypeScript
    │   │   └── Producto.ts
    │   ├── App.tsx            # Componente principal
    │   └── main.tsx           # Punto de entrada
    ├── package.json
    └── vite.config.ts
```

---

## 🛠️ Tecnologías utilizadas

### Backend (Java Spring Boot)

| Tecnología | Versión | Propósito |
|------------|----------|-----------|
| **Java** | 21 | Lenguaje principal |
| **Spring Boot** | 3.2.5 | Framework web |
| **Spring Data JPA** | 3.2.5 | Persistencia de datos |
| **MySQL Connector** | 8.x | Driver base de datos |
| **Spring Validation** | 3.2.5 | Validación de datos |
| **Maven** | 3.9+ | Gestión de dependencias |

### Frontend (React TypeScript)

| Tecnología | Versión | Propósito |
|------------|----------|-----------|
| **React** | 19.1.0 | Biblioteca UI |
| **TypeScript** | 6.0+ | Tipado estático |
| **Vite** | 8.0.10 | Build tool |
| **CSS3** | - | Estilos visuales |
| **Fetch API** | - | Llamadas HTTP |

---

## 🎓 Objetivos de aprendizaje

Este proyecto está diseñado para que los pasantes aprendan:

1. **Arquitectura cliente-servidor**
2. **API REST y comunicación HTTP**
3. **Tipado estático con TypeScript**
4. **Manejo de estado en React**
5. **Validación de datos (frontend y backend)**
6. **Buenas prácticas de código**
7. **Gestión de errores y用户体验**
8. **Desarrollo full-stack end-to-end**

---

## 🚀 Próximos pasos (para extender el proyecto)

- [ ] **Autenticación de usuarios**
- [ ] **Roles y permisos**
- [ ] **Exportación a Excel/PDF**
- [ ] **Gráficos de inventario**
- [ ] **Historial de cambios**
- [ ] **Notificaciones en tiempo real**
- [ ] **Testing automatizado**
- [ ] **Dockerización**

---

## 📝 Notas para el instructor

- **Paso a paso**: Explicar cada capa (Controller → Service → Repository)
- **Errores comunes**: CORS, validación, manejo de nulos
- **Buenas prácticas**: Separación de responsabilidades, DTOs vs Entidades
- **Debug**: Cómo usar logs y breakpoints
- **Despliegue**: Subir a producción (Vercel, Railway, etc.)

---

**¡Feliz aprendizaje! 🎓**

Cualquier duda, los conceptos están explicados línea por línea en el código con comentarios detallados.
