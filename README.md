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