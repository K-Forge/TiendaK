--- Creación de Database
--- CREATE DATABASE Tienda_K

--- Usar Database
USE Tienda_K;
---Show Tables;

--- Tabla Producto
CREATE TABLE IF NOT EXISTS Producto (
	idProducto INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    categoria ENUM('ROPA','ACCESORIOS', 'LIBRERIA', 'PAPELERIA') NOT NULL,
    nombreProducto VARCHAR(50) NOT NULL,
    precioUnitario FLOAT NOT NULL   
);
--- Describe Producto;

--- Tabla Stock
CREATE TABLE IF NOT EXISTS Stock (
    idStock INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    idProducto INT NOT NULL,
    fechaIngreso DATE NOT NULL,
    stock INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);
--- Describe Stock;

--- Tabla ItemCarrito
CREATE TABLE IF NOT EXISTS ItemCarrito (
    idItemCarrito INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario FLOAT NOT NULL,
    idProducto INT NOT NULL,
    idCarritoCompra INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

--- Tabla CarritoCompra
CREATE TABLE IF NOT EXISTS CarritoCompra (
    idCarritoCompra INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fechaCreacion DATE NOT NULL,
    estado ENUM('VACIO','CON_PRODUCTOS', 'EN_PROCESO_DE_PAGO', 'PAGO_PENDIENTE', 'PAGO_EXITOSO') NOT NULL,
    idUsuario INT NOT NULL
);
--- Describe CarritoCompra;

ALTER TABLE ItemCarrito ADD CONSTRAINT fk_idCarritoCompra FOREIGN KEY (idCarritoCompra) REFERENCES CarritoCompra(idCarritoCompra);

--- Tabla Factura
CREATE TABLE IF NOT EXISTS Factura (
    idFactura INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fechaCompra DATE NOT NULL,
    totalCompra FLOAT NOT NULL,
    metodoPago ENUM('PSE', 'TARJETA_CREDITO', 'TARJETA_DEBITO', 'EFECTIVO', 'TRANSFERENCIA') NOT NULL,
    iva FLOAT NOT NULL,
    idEmpleado INT NOT NULL,
    idCliente INT NOT NULL,
    idCarritoCompra INT NOT NULL,
    FOREIGN KEY (idCarritoCompra) REFERENCES CarritoCompra(idCarritoCompra)
);
--- Describe Factura;

---Altertable para colocar Foreign keys
---ALTER TABLE Factura ADD CONSTRAINT fk_idCliente FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente);