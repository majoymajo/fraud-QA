# HU1 – Evaluar monto de la transacción

## DEV Tasks

    • 	Diseñar la entidad con los datos que va a tener una transacción (id, monto, fecha)

    • 	Crear la tabla en la base de datos

    • 	Comparar el monto contra el umbral configurado

    •	Determinar si el monto es normal o inusual

    • 	Manejar errores como montos negativos o valores no numéricos 

# HU2 – Evaluar ubicación de la transacción

## DEV Tasks

    •	Definir la entidad con los datos de ubicación (país, ciudad, IP)

    • 	Crear tabla de historial de ubicaciones

    •	Comparar la ubicación actual con el historial de ubicaciones del usuario

    •	Crear la lógica para detectar si una ubicación es inusual

    •	Devolver si la ubicación es inusual o no

# HU3 – Marcar transacción sospechosa

## DEV Tasks

    • 	recibir los resultados de la evaluación de monto y ubicación.

    • 	Definir reglas configurables (ej. monto > umbral o ubicación no habitual).

    • 	Determinar si la transacción es sospechosa o no.

    • 	Integrar con el flujo de evaluación general.
    
    • 	Retornar resultado si la transacción es sospechosa o no.
