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


1. Identificacion de riesgos 
2. Definir la probabilidad e impacto
3. Priorizacion de riesgos
4. Evaluación de Impacto
5. Determinacion de los tiempos de recuperacion



## 1. Identificacion de Riesgos 

Negocio: Retrasos en entregas, sobrecostos , cambios operativos , poca acogida del MVP. 

Técnicos:  Falta de claridad en requerimientos, bajo desempeño , contratiempos operativos , fallos técnicos . 

## 2. Definir Probabilidad e impacto 

**Probabilidad**:  1 (muy baja) a 5 (muy alta) 

**Impacto**: 1(mínimo) a 5 (crítico) 

## 3. Evaluación de Impacto 

### Riesgos de Negocio 

- Retrasos en la entrega 

Afecta la confianza del cliente,a su vez la reputación de la empresa , 
puede generar problemas organizacionales para reestructurar el horario planeado y tareas. 

- Sobrecostos 

Limita el presupuesto del team de desarrollo, lo cual puede limitar tambien el acceso a ciertos recursos y herramientas.
 Incluso puede frenar la escalabilidad del producto. 

- Cambios operativos 

Puede generar restructuracion de planes, horarios y tareas lo que podria afectar la productividad del equipo y 
retardar entregas .

- Poca acogida del MVP 

 Afecta directamente la viabilidad del producto y la inversión futura.

### Riesgos Técnicos 

- Falta de claridad en los requerimientos 

 Deriva en funcionalidades mal implementadas, retrabajo y defectos en producción. 

- Bajo desempeño 

Afecta la experiencia de usuario y la adopción del producto. Incluso puede provocar pérdida de clientes o fracaso del proyecto. 

- Contratiempos operativos 

 Generan re-planificacion de horarios y tareas lo que podria afectar la productividad y bienestear del equipo

- Fallos técnicos  

Pueden interrumpir servicios críticos, afectar disponibilidad y perjudicar la reputación de la empresa.


## 4. Priorización de riesgos

Multiplicacion de probabilidad por impacto:
    
    >= 12   Riesgo Alto   
    6-11     Riesgo medio
    <= 5     Riesgo bajo


## 5. Determinación de tiempos de recuperación

Tiempo máximo para restablecer operaciones despúes de un incidente : 

**Operaciones críticas** : 2-4 horas 

**Desempeño técnico**: 24 - 32 horas. 



