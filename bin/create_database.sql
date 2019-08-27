USE MASTER;
GO

CREATE TABLE FISCALIA (
    id int IDENTITY(1,1) PRIMARY KEY,
    nombre varchar(150) NOT NULL,
    descripcion varchar(255),
    fecha_creacion datetime DEFAULT GETDATE()
);
GO

CREATE TABLE MOVIMIENTOS (
    id int IDENTITY(1,1) PRIMARY KEY,
    telefono varchar(50) NOT NULL,
    fecha_creacion datetime DEFAULT GETDATE(),
    fecha_actualizacion datetime DEFAULT GETDATE(),
    direccion varchar(255) not null,
    latitud DECIMAL(9,6),
    longitud DECIMAL(9,6),
    activa bit not null,
);
GO

CREATE PROCEDURE ObtenerFiscalias
AS
    SELECT * FROM FISCALIA
    INNER JOIN MOVIMIENTOS M on FISCALIA.fecha_creacion = M.fecha_creacion
    WHERE M.activa = 1
GO




