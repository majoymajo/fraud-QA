#  💳 FinTech: Motor de Reglas de Fraude

## Desafio: Evaluar transacciones entrantes. Si el monto > X o la ubicación es inusual, marcar como sospechosa.


## Visión del Producto

Desarrollar un sistema antifraude que permita detectar transacciones sospechosas de acuerdo al monto y ubicación. al ubicar montos superiores a un umbral o ubicaciones inusuales, se conseguirá aumentar la seguridad de las transacciones y reducir el riego de fraudes, garantizando una mejor experiencia del usuario.

## Objetivos

    1. Evaluar transacciones en tiempo real y marcar como sospechosas aquellas con un monto mayor a 15000$ o que provengan de paraísos fiscales fuera del país de residencia del usuario. 

    2. Diseñar un sistema antifraude escalable capaz de recibir nuevas reglas y funciones que incrementen la confiabilidad de las transacciones de los usuarios. 

    3. Mejorar la seguridad contra fraudes financieros a través de advertencias tempranas y clasificaciones de casos sospechosos en 3 categorias de riesgo (alto,medio,bajo). 

## Alcance del MVP

**IN**

Para la primera versión del sistema, nos vamos a enfocar en funcionalidades básicas. El sistema podrá:

    •	Evaluar si el monto de una transacción supera un valor límite definido

    •	Analizar desde dónde se realiza la transacción para identificar ubicaciones inusuales

    •	Marcar automáticamente como sospechosas aquellas transacciones que cumplan alguna condición de riesgo

    •	Asignar un nivel de riesgo sencillo (alto, medio o bajo) según las reglas definidas

    •	Generar alertas básicas dentro del sistema cuando se detecte algo sospechoso

    •	Guardar un registro de cada evaluación para poder consultarlo después

**OUT**

Para mantener el enfoque en un MVP, se decidió dejar por fuera funcionalidades más avanzadas que pueden desarrollarse en futuras versiones, como:

    •	El uso de inteligencia artificial o modelos de Machine Learning

    •	Integraciones externas como correos, mensajes SMS o APIs bancarias

    •	bloqueo automático de transacciones

    •	Dashboards o analítica visual

    •	Sistemas complejos de usuarios y autenticación

    •	Un motor de reglas avanzado 

    •	Auditorías detalladas con trazabilidad completa


El producto en su primera versión se enfocará en la detección básica de transacciones sospechosas mediante reglas simples y claras con un plan efectivo de mejora continua.

El MVP incluirá:

    1. Evaluación de transacciones en tiempo real basada en dos criterios:
	
    - Monto superior a un umbral fijo (ej. 15,000).
    - Lugar de la transacción inusual (paraísos fiscales ) o fuera del país del usuario.
	
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

### Metodología: 
Cualitativa y Business Impact Analysis (BIA)

1. Identificacion de riesgos 
2. Definir la probabilidad e impacto
3. Evaluacion de impacto
4. Priorizacion de riesgos
5. Determinacion de los tiempos de recuperacion
6. PLan de acción ante riesgos



## 1. Identificacion de Riesgos 

Negocio: Retrasos en entregas, sobrecostos , cambios operativos , poca acogida del MVP. 

Técnicos:  Falta de claridad en requerimientos, bajo desempeño , contratiempos operativos , fallos técnicos . 

## 2. Definir Probabilidad e impacto 

**Probabilidad**:  1 (muy baja) a 5 (muy alta) 

**Impacto**: 1(mínimo) a 5 (crítico) 

## 3. Evaluación de Impacto 

### **Riesgos de Negocio** 

- **Retrasos en la entrega** 

        Afecta la confianza del cliente,a su vez la reputación de la empresa , 
        puede generar problemas organizacionales para reestructurar el horario planeado y tareas. 

- **Sobrecostos**

        Limita el presupuesto del team de desarrollo, lo cual puede limitar tambien el acceso a ciertos recursos y herramientas.
        Incluso puede frenar la escalabilidad del producto. 

- **Cambios operativos**

        Puede generar restructuracion de planes, horarios y tareas lo que podria afectar la productividad del equipo y 
        retardar entregas .

- **Poca acogida del MVP**

        Afecta directamente la viabilidad del producto y la inversión futura.

### **Riesgos Técnicos** 

- **Falta de claridad en los requerimientos** 

        Deriva en funcionalidades mal implementadas, retrabajo y defectos en producción. 

- **Bajo desempeño** 

        Afecta la experiencia de usuario y la adopción del producto. Incluso puede provocar pérdida de clientes o fracaso del proyecto. 

- **Contratiempos operativos**

        Generan re-planificacion de horarios y tareas lo que podria afectar la productividad y bienestear del equipo

- **Fallos técnicos**

        Pueden interrumpir servicios críticos, afectar disponibilidad y perjudicar la reputación de la empresa.


## 4. Priorización de riesgos

Multiplicacion de probabilidad por impacto:
    
    >= 12   Riesgo Alto   
    6-11     Riesgo medio
    <= 5     Riesgo bajo

| Riesgos de negocio       | Probabilidad | Impacto | Prioridad |
|--------------------------|--------------|---------|-----------|
| Retrasos en la entrega   | 3            | 3       | 9         |
| Sobrecostos              | 2            | 4       | 8         |
| Cambios operativos       | 3            | 3       | 9         |
| Poca acogida del MVP     | 2            | 5       | 10        |

| Riesgos técnicos         | Probabilidad | Impacto | Prioridad |
|--------------------------|--------------|---------|-----------|
| Falta de claridad        |              |         |           |
en requerimientos          | 3            | 4       | 12        |
| Bajo desempeño           | 2            | 5       | 10        |
| Contratiempos operativos | 2            | 3       | 6         |
| Fallos técnicos          | 3            | 5       | 15        |


## 5. Determinación de tiempos de recuperación

Tiempo máximo para restablecer operaciones despúes de un incidente : 

**Operaciones críticas** : 2-4 horas 

**Desempeño técnico**: 24 - 32 horas. 

## 6 Plan de acción ante riesgos

### **Riesgos de Negocio**

- **Retrasos en la entrega**

        Comunicar a los clientes con anticipación o dar updates regulares. 

        Actualización del plan con nuevos horarios y distribución de tareas.

- **Sobrecostos**

        Colaborar con el equipo de finanzas para distribuir adecuadamente el dinero en herramientas de IT con el presupuesto disponible. 

        Cancelación de suscripciones no utilizadas o no necesarias.

- **Cambios operativos**

        Reestructuracion del plan de trabajo y tareas.  

        Comunicar a los clientes con anticipación o dar updates regulares.

- **Poca acogida del MVP**

        Programas/Charlas de incentivación del programa. 

        Demostraciones live del MVP.

### **Riesgos Técnicos**

- **Falta de claridad en los requerimientos**

        Reuniones para unificar ideas y alinear objetivos . 

        Esto garantizaría que todos esten en sincronía nuevamente. 

        Documentación de los acuerdos de las reuniones 
        en un lugar accesible para el equipo.

- **Bajo desempeño y fallos técnicos**

         Mantener activo el sistema para clientes con las funcionalidades que esten funcionando exitosamente 
         y reparación en background de las partes que 
         necesitan mejoras. 

- **Contratiempos operativos**

        Replanificación de tareas y horarios. 

        Comunicar a los clientes con anticipación o dar updates regulares.  