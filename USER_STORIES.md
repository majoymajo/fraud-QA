# 📗 HISTORIAS DE USUARIOS

## HU1 – Evaluar monto de la transacción

**Como** Sistema antifraude

**Quiero** revisar el monto de cada transacción  que se realiza

**Para**  identificar valores inusualmente altos 

### Criterios de aceptación 

**Escenario 1: Monto exced el umbral**

        Dado que el monto de una transacción excede el umbral establecido 

        Cuando la transacción es procesada por el sistema   

        Entonces el sistema lo clasifica como transacción inusual 
**Escenario 2 : Monto es igual o menor al umbral**
        Dado que el monto de la transacción es menor  o igua a X  

        Cuando la transacción es procesada por el sistema 

        Entonces el sistema no lo clasifica como movimiento sospechoso
## HU2 – Evaluar ubicación de la transacción

**Como** sistema antifraude

**Quiero** detectar la ubicación de cada transacción comparándola con la ubicación habitual del usuario

**Para** identificar si es sospechosa. 

### Criterios de aceptación 

**Escenario 1:Ubicación de la transacción inusual**
        Dado que la ubicación habitual del usuario esta registrada   

        Cuando se hace un movimiento desde una ubicación diferente  

        Entonces el sistema lo detecta como transacción inusual. 
**Escenario 2: Ubicación de la transacción usual**
        Dado que la ubicación habitual del usuario esta registrada 

        Cuando se hace un movimiento desde esa ubicación 

        Entonces el sistema lo detecta como transacción usual. 

## HU3 – Marcar transacción sospechosa

**Como** sistema antifraude

**Quiero** marcar transacciones como 
sospechosas cuando cumplan cumplan los siguientes criterios(ej. monto alto o ubicación inusual)

**Para** permitir su revisión

### Criterios de aceptación

**Escenario 1: Señal de transacción sospechosa**
        Dado que el sistema evalua el monto y lugar de cada transacción  

        Cuando la transacción no cumple alguno de estos criterios 

        Entonces el sistema señala como sospechosa la transacción. 
**Escenario 2: Señal de Transacción no sospechosa**
        Dado que el sistema evalua el monto y lugar de cada transacción  

        Cuando la transacción cumple con estos criterios 

        Enconces el sistema señala como no sospechosa a la transacción         

## HU4 Clasificar nivel de riesgo

**Como**: Sistema Antifraude.

**Quiero**: Asignar una categoría de riesgo (Alto, Medio, Bajo) a cada transacción sospechosa según los criterios establecidos

**Para**: priorizar su análisis.

### Criterios de aceptación
**Escenario 1: Categorizar el nivel de riesgo de una transacción**
        Dado que una transacción es detectada como sospechosa 

        Cuando el sistema evalua los criterios estalecidos 

        Entonces el sistema asigna una cateogria (baja,media o alta) de riesgo 

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

## HU9 – Configurar ubicaciones habituales

**Como** administrador

**Quiero** definir las ubicaciones habituales de un usuario basadas en su historial de transacciones

**Para** mejorar la detección de anomalías

## HU10 – Configurar niveles de riesgo

**Como** administrador

**Quiero** definir los criterios de clasificación de riesgo

**Para** ajustar la priorización de transacciones
