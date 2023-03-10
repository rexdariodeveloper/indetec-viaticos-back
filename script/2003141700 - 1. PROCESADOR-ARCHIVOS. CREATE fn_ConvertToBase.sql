SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-----------------------------------------------------
-- Creacion: Alonso Soto
-- Fecha:   15/05/2019
-- Funcion que convierte un determinado decimal a otro base
/*
EJEMPLO:
SELECT
   dbo.ConvertToBase(406, 2)   -- 110010110
  ,dbo.ConvertToBase(406, 3)   -- 120001
  ,dbo.ConvertToBase(406, 8)   -- 626
  ,dbo.ConvertToBase(406, 10)  -- 406
  ,dbo.ConvertToBase(406, 16)  -- 196
  ,dbo.ConvertToBase(406, 36); -- ba
*/

CREATE FUNCTION [dbo].[fn_ConvertToBase]
(
    @value AS BIGINT,
    @base AS INT
) RETURNS VARCHAR(MAX) AS BEGIN

    -- some variables
    DECLARE @characters CHAR(36),
            @result VARCHAR(MAX);

    -- the encoding string and the default result
    SELECT @characters = '0123456789abcdefghijklmnopqrstuvwxyz',
           @result = '';

    -- make sure it's something we can encode.  you can't have
    -- base 1, but if we extended the length of our @character
    -- string, we could have greater than base 36
    IF @value < 0 OR @base < 2 OR @base > 36 RETURN NULL;

    -- until the value is completely converted, get the modulus
    -- of the value and prepend it to the result string.  then
    -- devide the value by the base and truncate the remainder
    WHILE @value > 0
        SELECT @result = SUBSTRING(@characters, @value % @base + 1, 1) + @result,
               @value = @value / @base;

    -- return our results
    RETURN @result;

END
