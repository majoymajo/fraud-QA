# Motor de Reglas Antifraude

## Visión del Producto

Desarrollar un sistema antifraude que permita detectar transacciones sospechosas de acuerdo al monto y ubicación al ubicar montos superiores a un umbral o ubicaciones inusuales, se conseguirá aumentar la seguridad de las transacciones y reducir el riego de fraudes, garantizando una mejor experiencia del usuario.

## Estructura del proyecto

•	PRD → Contiene la visión del producto, objetivos y alcance (PRD.md)

•	Historias de Usuario (HU) → Definen los requerimientos funcionales (USER_STORIES.md)

•	Tareas (DEV y QA) → Detallan la implementación técnica y pruebas (SUBTASKS.md)


## Criterios de la evaluación de las HUs 

Se utilizó la escala **Fibonacci (3, 5, 8)** para representar el trabajo que requiere cada HU.

Consideraciones:

- Complejidad técnica  
- Tamaño del trabajo  
- Incertidumbre


---


| HU   | Descripción                      | Story Points | Justificación |
|------|----------------------------------|-------------|--------------|
| HU1  | Evaluar monto                   | 3           | Lógica simple de validación y comparación contra umbral. Baja complejidad y poca incertidumbre. |
| HU2  | Evaluar ubicación               | 5           | Requiere comparar con historial del usuario y definir qué es “inusual”. Mayor lógica e incertidumbre. |
| HU3  | Marcar sospechosa               | 3           | Orquesta resultados de otras HUs. No agrega lógica compleja nueva. |
| HU4  | Clasificar el riesgo               | 5           | Combina múltiples condiciones (monto y/ o ubicación) e introduce reglas de negocio adicionales. |
| HU5  | Generar alerta                  | 5           | Incluye lógica de duplicidad y emisión de eventos. Depende de otras HUs. |
| HU6  | Registrar transacciones         | 5           | Implementación de persistencia, endpoint, filtros y paginación.  |
| HU7  | Revisión manual                 | 5           | Manejo de estados y reglas de negocio adicionales. Persistencia y consultas. |
| HU8  | Configurar monto                | 3           | CRUD simple con validaciones básicas e integración con HU1. Baja complejidad. |
| HU9  | Ubicaciones habituales          | 8           | Manejo de historial por usuario e integración directa con HU2. Alta complejidad y crecimiento potencial. |
| HU10 | Configurar niveles de riesgo    | 5           | Configuración dinámica de reglas. Requiere validación e integración con clasificación. |
