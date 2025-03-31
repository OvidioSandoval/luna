# App Luna

    App Luna es una aplicación backend para el control de ventas y productos, desarrollada con Spring Boot, JPA y MySQL.

## Requisitos Previos

    Antes de ejecutar la aplicacion, asegurarse de tener instaldao:

    Java 24
    Maven 
    MySQL Server.
    Postaman 

## Instalacion de MySQL

    1. Descarga e instala MySQLServer desde el enlace oficial.
    2. Inicia el servi  cio de MySQL.
    3. Crear una base de datos para la aplicacion
        

## Configuracion de la aplicacion

    Editar el archivo application.properties  con la configuracion de la base de datos:

    spring.datasource.url=jdbc:mysql://localhost:3306/luna
    spring.datasource.username=root 
    spring.datasource.password=soel
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.show-sql=true

## Modulo de productos

    1. GET /api/prodcutos/lista         
        * Retorma lista completaa de productos registrados en la base de datos.
        * Resouesta de ejemplo
        [
            {
                "id": 1,
                "descripcion":"medias grandes",
                "precio":15000,
                "stock":50
            }
        ]

    2. POST /api/producto/crear     
        * Permite un nuevo producto
        * Datos requeridos
        [
            {
                "descripcion":"Vestido Casual",
                "precio":220000,
                "stock"10:
            }
        ]

        * Validaciones: los datos deben ser correctos y noo nulos.

    3. PUT /api/producto/modificar
        * Permite modificar un producto existente.
        * Datos requeridos
        [
            {
                "id":1,
                "descripcion":"Chaqueta de cuerro",
                "precio":350000,
                "stock":5
            }
        ]

        *Validaciones: el producto debe existir.

## Modulo de Ventas
    1. POST "/api/venta/registrar"
        * Ermite registrar una venta.
        * Datos requeridos
        [
            {
                "producto_id":1,
                "cantidad":2
            }
        ]

        * Validaciones:
            * Verificar que el producto exista.
            * Validar que haya stock suficiente antes de descontar.
            * Registrar la venta con la fecha y hora actual.
            * Descontar la cantidad vendida del stock del producto.

    2. GET /api/venta/lista
        * Devuelve la lista de todas las ventas realizadas.
        * Respuesta de ejemplo.
        [
             {
                "id": 1,
                "fecha": "2025-03-31T14:30:00",
                "producto": "Medias grandes",
                "cantidad": 2
             }
        ]

### Modelado de base de datos

## Tabla producto

    Campo           Tipo            Restricciones

    id              Long            Clave primaria, autogenerada

    descripcion     String          No nulo

    precio          Decimal         No nulo, mayor a 0

    stock           Integer         No nulo, mayor o igual a 0

## Tabla venta

    Campo               Tipo        Restricciones

    id                  Long        Clave primaria, autogenerada

    fecha               DateTime        Se debe registrar automáticamente la fecha y hora

    producto_id         Long            Relación  Clave foránea que referencia a producto

    cantidad            integer         No nulo, mayor a 0

## Script SQL de Creacion De Base de Datos y Datos de Prueba

CREATE DATABASE app_luna; 
USE app_luna; 

CREATE TABLE producto (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    stock INT NOT NULL CHECK (stock >= 0)

); 

CREATE TABLE venta (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    producto_id BIGINT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    FOREIGN KEY (id_producto) REFERENCES producto(id)

); 

INSERT INTO producto (descripcion, precio, stock) VALUES
('Blusa elegante ', 15000, 10), 
('Sweater de la lana', 100000, 50), 
('Gorra unisex', 80000, 30); 

Usar Postman o similares para probar los metodos GET, POS, DELETE, PUT

Metodos y rutas 

## PRODUCTOS

GET: /api/producto/lista            -> Lista de los productos
GET: /api/producto/{id}             -> Buscar producto por ID 
Delete: /api/producto/borrar/{id}   -> Borrar un producto
POST:   /api/producto/crear/{id}    -> Crear nuevo producto
PUT:    /api/producto/modificar/{id}   -> Modificar producto


## Venta

GET: /api/venta/lista            -> Lista de las ventas
GET: /api/venta/{id}             -> Buscar venta  por ID
POST: /api/registrar/{id}    -> Crear nuevo producto