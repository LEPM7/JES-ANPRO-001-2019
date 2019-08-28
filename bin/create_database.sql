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
    activa              bit          not null,
    fiscaliaId int FOREIGN KEY REFERENCES FISCALIA(id)
);
GO

--- Store Procedures

--- Insertar

CREATE PROCEDURE FiscaliaInsertar @Nombre varchar(150),
                                  @Descripcion varchar(255),
                                  @Telefono varchar(50),
                                  @Direccion varchar(255),
                                  @Latitud DECIMAL(9, 6),
                                  @Longitud DECIMAL(9, 6)
AS
BEGIN
    SET NOCOUNT ON
    INSERT INTO dbo.FISCALIA(nombre, descripcion)
    values (@Nombre, @Descripcion);
    INSERT INTO dbo.MOVIMIENTOS(telefono, direccion, latitud, longitud, activa, fiscaliaId)
    values (@Telefono, @Direccion, @Latitud, @Longitud, 1, (select id from FISCALIA where FISCALIA.nombre = @Nombre));
END
GO


--- Obtener
CREATE PROCEDURE FiscaliaObtenerActivas
AS
SELECT FISCALIA.nombre, FISCALIA.descripcion, M.*
FROM FISCALIA
         INNER JOIN MOVIMIENTOS M on FISCALIA.id = M.fiscaliaId
WHERE M.activa = 1;
go

--- Borrar
CREATE PROCEDURE FiscaliaBorrar @id int
AS
BEGIN
    SET NOCOUNT ON
    DELETE FROM MOVIMIENTOS WHERE fiscaliaId = @id;
    DELETE from FISCALIA where id = @id;
END
GO