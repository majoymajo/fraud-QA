# 📗 HISTORIAS DE USUARIOS

## HU1 – Evaluar monto de la transacción

**Como** Sistema antifraude

**Quiero** revisar el monto de cada transacción  que se realiza

**Para**  identificar valores inusualmente altos 

## HU2 – Evaluar ubicación de la transacción

**Como** sistema antifraude

**Quiero** detectar la ubicación de cada transacción comparándola con la ubicación habitual del usuario

**Para** identificar si es sospechosa. 

## HU3 – Marcar transacción sospechosa

**Como** sistema antifraude

**Quiero** marcar transacciones como 
sospechosas cuando cumplan cumplan los siguientes criterios(ej. monto alto o ubicación inusual)

**Para** permitir su revisión

## HU4 Clasificar nivel de riesgo

**Como**: Sistema Antifraude.

**Quiero**: Asignar una categoría de riesgo (Alto, Medio, Bajo) a cada transacción sospechosa según los criterios establecidos

**Para**: priorizar su análisis.

## HU5 – Generar alerta de fraude

**Como** sistema antifraude

**Quiero** generar una alerta cuando identifique una transacción sospechosa

**Para** que la operación sea validada automáticamente o reciba revisión humana.

## HU6 Registrar transacciones evaluadas

**Como** sistema antifraude

**Quiero** guardar el resultado de cada transacción analizada

**Para** tener un historial que permita auditar las decisiones tomadas

## HU7 – Marcar transacciones para revisión manual

**Como** sistema antifraude

**Quiero** marcar ciertas transacciones como “requiere revisión manual”

**Para** que puedan ser analizadas por un operador humano cuando no sean concluyentes

## HU8 – Configurar monto máximo

**Como** administrador

**Quiero** definir el monto límite de transacciones

**Para** establecer el umbral de detección
