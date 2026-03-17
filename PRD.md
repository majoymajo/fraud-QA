#  💳 FinTech: Motor de Reglas de Fraude

## Desafio: Evaluar transacciones entrantes. Si el monto > X o la ubicación es inusual, marcar como sospechosa.


## Visión del Producto

Desarrollar un sistema antifraude que permita detectar transacciones sospechosas de acuerdo al monto y ubicación. al ubicar montos superiores a un umbral o ubicaciones inusuales, se conseguirá aumentar la seguridad de las transacciones y reducir el riego de fraudes, garantizando una mejor experiencia del usuario.

## Objetivos

    1. Evaluar transacciones en tiempo real y marcar como sospechosas aquellas con un monto mayor a 15000$ o que provengan de paraísos fiscales fuera del país de residencia del usuario. 

    2. Diseñar un sistema antifraude escalable capaz de recibir nuevas reglas y funciones que incrementen la confiabilidad de las transacciones de los usuarios. 

    3. Mejorar la seguridad contra fraudes financieros a través de advertencias tempranas y clasificaciones de casos sospechosos en 3 categorias de riesgo (alto,medio,bajo). 

## Alcance del MVP

El producto en su primera versión se enfocará en la detección básica de transacciones sospechosas mediante reglas simples y claras con un plan efectivo de mejora continua.

El MVP incluirá:

    1. Evaluación de transacciones en tiempo real basada en dos criterios:
	
    - Monto superior a un umbral fijo (ej. 15,000).
    - Lugar de la transacción inusual (paraísos fiscales )o fuera del país del usuario.
	
    2. Clasificación de la transacción como:

    - Sospechosa
    - No sospechosa
    - Requiere revisión manual
    
    3. Gestión de alertas:

    - Generar alerta cuando se identifique una transacción sospechosa.
    - Priorizar alertas de transacciones sospechosas según el nivel de riesgo  (alto,medio,bajo).

    4. Registro:

    - Registro de todas las transacciones evaluadas y su resultado.

## Riesgos de Negocio y Técnicos

    Identificacion de riesgos 

    Analisis cualitativo 

    Analisis cuantitativo 

    Plan de accion ante Riesgos  

    Monitoreo 