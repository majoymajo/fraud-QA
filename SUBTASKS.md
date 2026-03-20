# HU1 – Evaluar monto de la transacción

## DEV Tasks

    • 	Diseñar la entidad con los datos que va a tener una transacción (id, monto, fecha).

    • 	Crear la tabla en la base de datos.

    • 	Comparar el monto contra el umbral .configurado.

    •	Determinar si el monto es normal o inusual.

    • 	Manejar errores como montos negativos o valores no numéricos.

# HU2 – Evaluar ubicación de la transacción

## DEV Tasks

    •	Definir la entidad con los datos de ubicación (país, ciudad, IP).

    • 	Crear tabla de historial de ubicaciones.

    •	Comparar la ubicación actual con el historial de ubicaciones del usuario.

    •	Crear la lógica para detectar si una ubicación es inusual.

    •	Devolver si la ubicación es inusual o no.

# HU3 – Marcar transacción sospechosa

## DEV Tasks

    • 	recibir los resultados de la evaluación de monto y ubicación.

    • 	Definir reglas configurables (ej. monto > umbral o ubicación no habitual).

    • 	Determinar si la transacción es sospechosa o no.

    • 	Integrar con el flujo de evaluación general.

    • 	Retornar resultado si la transacción es sospechosa o no.

# HU4 – Clasificar nivel de riesgo

## DEV Tasks

    •   Definir niveles de riesgo (Alto, Medio, Bajo).

    • 	Implementar servicio que combine monto y ubicación para asignar riesgo.

    •	Aplicar reglas configurables.

    • 	Asignar nivel de riesgo.

    • 	Retornar resultado con categoría de riesgo.

**Lógica de clasificación**

    •	Si el monto es alto y la ubicación es inusual → riesgo alto.

    •	Si solo una de las dos condiciones se cumple → riesgo medio.

    •	Si ninguna se cumple → riesgo bajo.


# HU5 – Generar alerta de fraude

## DEV Tasks

    • 	Definir la entidad con los campos (id, transactionId, riesgo, fecha).

    • 	Implementar la lógica para generar una alerta para transacciones sospechosas.

    • 	Validar duplicidad de alertas por transacción.

    • 	Enviar alerta como evento.

# HU6 – Registrar transacciones evaluadas

## DEV Tasks

    • 	Guardar el resultado de evaluación.
    
    • 	Guardar nivel de riesgo asociado.

    • 	Crear endpoint  con filtros y paginación.

   

# HU7 – Marcar transacciones para revisión manual

## DEV Tasks

    •	Definir el estado "requiere revisión manual".
    
    • 	Implementar la lógica para marcar las transacciones inconclusas.

    • 	Actualizar estado según las reglas de negocio.

    • 	Persistir estado en la base de datos.

    • 	Crear endpoint para consultar transacciones en revisión.

# HU8 – Configurar monto máximo

## DEV Tasks

    • 	Crear la entidad/configuración.

    • 	Crear el endpoint.

    • 	Validar los valores (numéricos, > 0).

    • 	Integrar el umbral con evaluación de monto.



#  HU9 – Configurar ubicaciones habituales

## DEV Tasks

    • 	Diseñar la entidad.

    • 	Crear la tabla de ubicaciones por usuario.

    • 	Crear el endpoint.

    • 	Validar los datos de ubicación.

    • 	Actualizar el historial del usuario.

    • 	Integrar con la evaluación de ubicación.

# HU10 – Configurar niveles de riesgo

## DEV Tasks

• 	Definir entidad/configuración de reglas de riesgo.

• 	Crear endpoint.

• 	Validar reglas ingresadas.

• 	Integrar reglas con clasificación de riesgo.



