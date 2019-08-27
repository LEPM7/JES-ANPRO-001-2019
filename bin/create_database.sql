USE MASTER;
GO

CREATE TABLE FISCALIA
(
    id             int IDENTITY (1,1) PRIMARY KEY,
    nombre         varchar(150) NOT NULL UNIQUE,
    descripcion    varchar(255),
    fecha_creacion datetime DEFAULT GETDATE()
);
GO

CREATE TABLE MOVIMIENTOS
(
    id                  int IDENTITY (1,1) PRIMARY KEY,
    telefono            varchar(50)  NOT NULL,
    fecha_creacion      datetime DEFAULT GETDATE(),
    fecha_actualizacion datetime DEFAULT GETDATE(),
    direccion           varchar(255) not null,
    latitud             DECIMAL(9, 6),
    longitud            DECIMAL(9, 6),
    activa              bit          not null
);
GO

CREATE PROCEDURE FiscaliaObtener
AS
SELECT *
FROM FISCALIA
         INNER JOIN MOVIMIENTOS M on FISCALIA.fecha_creacion = M.fecha_creacion
WHERE M.activa = 1
GO


CREATE PROCEDURE InsertarFiscalia @Nombre varchar(150),
                                  @Descripcion varchar(255),
                                  @Telefono varchar(50),
                                  @Direccion varchar(255),
                                  @Latitud DECIMAL(9, 6),
                                  @Longitud DECIMAL(9, 6)
AS
BEGIN
    DECLARE @id as int;
    SET NOCOUNT ON
    INSERT INTO dbo.FISCALIA(nombre, descripcion)
    values (@Nombre, @Descripcion);
    INSERT INTO dbo.MOVIMIENTOS(telefono, direccion, latitud, longitud, activa)
    values (@Telefono, @Direccion, @Latitud, @Longitud, 1);
    SET @id=SCOPE_IDENTITY()
    RETURN  @id
END
GO





