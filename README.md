# Conexiones MongoDB con Python, Node.js, Go y Java

Este repositorio contiene ejemplos de cómo conectar y realizar operaciones básicas (como inserciones de datos y gestión de índices) en una base de datos MongoDB utilizando diferentes lenguajes de programación: Python, Node.js, Go y Java (con Spring Boot).

---

## Resumen de lo Realizado

El objetivo de este proyecto fue demostrar la interacción con una base de datos MongoDB, enfocándose en:

1.  **Conexión a MongoDB:** Establecer una conexión exitosa con un servidor MongoDB local (generalmente en `mongodb://localhost:27017`).
2.  **Operaciones CRUD Básicas:** Inserción de un documento simple (`{ nombre: "Ana", edad: 25 }` o similar) en una colección (`usuarios` o `estudiantes`).
3.  **Gestión de Índices:** Creación de varios tipos de índices en una colección para optimizar el rendimiento de las consultas y/o asegurar la unicidad de los datos. Esto incluyó:
    * Índices de campo único.
    * Índices compuestos.
    * Índices únicos.
    * **Índices Únicos y Parciales:** Un enfoque más avanzado para aplicar restricciones de unicidad solo a un subconjunto de documentos, lo cual fue un punto clave de aprendizaje debido a la resolución de conflictos de datos existentes.

---

## Estructura del Proyecto
El repositorio está organizado por lenguajes, con cada subdirectorio conteniendo su proyecto respectivo:

```bash
conexionesPythonNodeJsGoJava/
├── .gitignore
├── proyectoGo/
│   ├── main.go
│   ├── go.mod
│   └── go.sum
├── proyectoJava/
│   └── mongodemo/
│       ├── pom.xml
│       ├── src/
│       ├── mvnw
│       └── mvnw.cmd
├── proyectoNodeJs/
│   ├── index.js
│   ├── package.json
│   └── package-lock.json
├── proyectoPython/
│   ├── main.py
│   └── requirements.txt (opcional, si se usó)
└── README.md
```

## Requisitos Previos

Antes de ejecutar cualquier proyecto, asegúrate de tener lo siguiente instalado y en ejecución:

* **MongoDB Server:** Un servidor MongoDB local ejecutándose (normalmente en `mongodb://localhost:27017`).
* **MongoDB Shell (mongosh):** Para la gestión de la base de datos y la creación manual de índices.

Además, para cada lenguaje:

* **Python:** Python 3.x y `pymongo` (`pip install pymongo`).
* **Node.js:** Node.js (versión LTS recomendada) y el driver `mongodb` (`npm install mongodb`).
* **Go:** Go Lang y el driver `go.mongodb.org/mongo-driver` (`go get go.mongodb.org/mongo-driver/...`).
* **Java:** JDK 17 (o superior), Apache Maven y un IDE como IntelliJ IDEA o VS Code para Spring Boot.

---

## Cómo Ejecutar Cada Proyecto

### 1. Proyecto Python

**Ubicación:** `proyectoPython/`

1.  Asegúrate de tener las dependencias instaladas:
    ```bash
    pip install pymongo
    ```
2.  Navega a la carpeta del proyecto:
    ```bash
    cd proyectoPython
    ```
3.  Ejecuta el script principal:
    ```bash
    python main.py
    ```
    Este script creará la base de datos `universidad` y la colección `usuarios` (o `estudiantes`) e insertará datos.

### 2. Proyecto Node.js

**Ubicación:** `proyectoNodeJs/`

1.  Instala las dependencias:
    ```bash
    cd proyectoNodeJs
    npm install mongodb
    ```
2.  Ejecuta el archivo principal:
    ```bash
    node index.js
    ```
    Este script creará la base de datos `universidad` y la colección `usuarios` (o `estudiantes`) e insertará datos.

### 3. Proyecto Go

**Ubicación:** `proyectoGo/`

1.  Asegúrate de haber descargado el módulo del driver de MongoDB:
    ```bash
    cd proyectoGo
    go mod init proyectoGoMongo # Si no lo has hecho
    go get go.mongodb.org/mongo-driver/mongo
    go get go.mongodb.org/mongo-driver/mongo/options
    ```
2.  Ejecuta el programa:
    ```bash
    go run main.go
    ```
    Este programa creará la base de datos `universidad` y la colección `usuarios` (o `estudiantes`) e insertará datos.

### 4. Proyecto Java (Spring Boot)

**Ubicación:** `proyectoJava/mongodemo/`

1.  Navega a la carpeta del proyecto Spring Boot:
    ```bash
    cd proyectoJava/mongodemo
    ```
2.  Ejecuta la aplicación usando Maven Wrapper:
    * **En Linux/WSL/macOS:**
        ```bash
        ./mvnw spring-boot:run
        ```
    * **En Windows CMD/PowerShell:**
        ```bash
        mvnw.cmd spring-boot:run
        ```
    La aplicación se iniciará, se conectará a MongoDB, realizará la inserción de datos y luego se detendrá o quedará en ejecución para otras operaciones (dependiendo de tu código).

---

## Gestión de Índices en MongoDB Shell (mongosh)

Los índices fueron creados y gestionados directamente a través de `mongosh`. Aquí se listan los comandos clave (asegúrate de estar en la base de datos `universidad` con `use universidad;`):

**1. Índices de Campo Único (03 Índices):**

```javascript
db.estudiantes.createIndex({ nombre: 1 });
db.estudiantes.createIndex({ carrera: 1 });
db.estudiantes.createIndex({ semestre: -1 });
```

**2. Índices Compuestos:**

```
db.estudiantes.createIndex({ edad: 1, matriculado: 1 });
db.estudiantes.createIndex({ "contacto.email": 1, "contacto.telefono": 1 });
db.estudiantes.createIndex({ carrera: 1, "cursos_actuales.nombre_curso": 1 });
```
**1. Índices Únicos y Parciales:**
```
// Este requirió resolver duplicados para { matriculado: true, "contacto.telefono": "987654321" }
db.estudiantes.createIndex(
  { "contacto.telefono": 1 },
  { unique: true, partialFilterExpression: { matriculado: true } }
);

// Este requirió resolver duplicados para { semestre: { $gt: 5 }, carrera: "..." , "contacto.email": "..." }
db.estudiantes.createIndex(
  { carrera: 1, "contacto.email": 1 },
  { unique: true, partialFilterExpression: { semestre: { $gt: 5 } } }
);

// Este requirió resolver duplicados para { carrera: "Arquitectura", nombre: "...", edad: "..." }
// O si hubo conflicto de nombre con un índice simple previo en `nombre`, se necesitó eliminar el anterior.
db.estudiantes.createIndex(
  { nombre: 1, edad: 1 },
  { unique: true, partialFilterExpression: { carrera: "Arquitectura" } }
);
```
