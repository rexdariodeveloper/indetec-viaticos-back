SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: 20/09/2021
-- Description:	SP para validar si un registro puede ser eliminado
-- =============================================
CREATE PROCEDURE sp_PermiteEliminarRegistro
	@RegistroId bigInt,
	@NombreTabla nvarchar(500),
	@PermiteEliminar BIT OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DECLARE @TableName NVARCHAR(128);
	DECLARE @ColumnName NVARCHAR(128);
	DECLARE @sSQL nvarchar(max);
	DECLARE @Contador bigint;
	DECLARE @campoBorrado NVARCHAR(100)
	DECLARE @IdEstatusBorrado INT

	SET @PermiteEliminar = 1

	-- Obtenemos todas las tablas a las que esta relacionado el registro que 
	-- queremos borrar (FK)
	DECLARE cLlaves 
	CURSOR FOR
	SELECT  OBJECT_NAME(f.parent_object_id) TableName,
			COL_NAME(fc.parent_object_id,fc.parent_column_id) ColumnName
	FROM sys.foreign_keys AS f
	INNER JOIN sys.foreign_key_columns AS fc ON f.object_id = fc.constraint_object_id
	INNER JOIN sys.tables AS t ON t.object_id = fc.referenced_object_id
	WHERE OBJECT_NAME(f.referenced_object_id) = @NombreTabla;

	-- Iteramos por cada tabla encontrada, y verificamos si el registro que queremos
	-- borrar se encuentra en esa tabla y esta vigente 
	OPEN cLlaves;
	FETCH NEXT FROM cLlaves INTO @TableName, @ColumnName;
	WHILE @@FETCH_STATUS = 0
	BEGIN

		SET @campoBorrado = (SELECT CampoBorrado FROM tblNombreTabla WHERE NombreTabla = @TableName)
		SET @IdEstatusBorrado = (SELECT IDEstatusBorrado FROM tblNombreTabla WHERE NombreTabla = @TableName)

		SET @sSQL = 'SELECT @Contador = COUNT(*) FROM ' + QUOTENAME(@TableName) +               
					' WHERE ' + QUOTENAME(@ColumnName) + ' = ' + cast(@RegistroId as nvarchar) + 
							 (CASE WHEN @campoBorrado IS NOT NULL THEN ( ' AND ' + QUOTENAME(@campoBorrado) + ' = ' + cast(@IdEstatusBorrado as nvarchar) ) ELSE '' END )
		EXEC sp_executesql @sSQL, N'@Contador bigint output', @Contador OUTPUT;	 
		IF @Contador > 0 
		BEGIN		
			SET @PermiteEliminar = 0
			BREAK;
		END
	
	FETCH NEXT FROM cLlaves INTO @TableName, @ColumnName;
	end;

	close cLlaves;
	deallocate cLlaves;

END
GO
