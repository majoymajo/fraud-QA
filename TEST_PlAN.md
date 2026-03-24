# 📗 Plan de Pruebas: Micro-Sprints Antifraude (HU1 & HU2)

## 1. Contexto
El sistema a probar es un **Motor de Detección de Fraude** en su etapa inicial. El problema de negocio que resuelve es la necesidad de filtrar transacciones sospechosas mediante el análisis de valores inusualmente altos y ubicaciones geográficas no habituales, protegiendo así los activos de los usuarios ante movimientos anómalos.

## 2. Alcance de las Pruebas

### Historias Incluidas:
*   **HU1 – Evaluar monto:** Revisión de cada transacción contra un umbral establecido.
*   **HU2 – Evaluar ubicación:** Comparación de la ubicación actual vs. la habitual del usuario.

### Historias Excluidas:
*   HU3 a HU10 (Marcado de sospecha, riesgos, alertas y configuraciones).

## 3. Estrategia de Pruebas
Dado el ciclo de 2 días, la estrategia es de ejecución rápida y automatizada:

*   **Funcional (SerenityBDD + Cucumber):** Validación de los criterios de aceptación de montos (Escenarios 1 y 2) y ubicaciones (Escenarios 1 y 2).
*   **Integración de API (Karate):** Pruebas de contrato y lógica para asegurar que los microservicios de evaluación devuelven la clasificación correcta ("Inusual" / "Usual").
*   **Rendimiento (k6):** Prueba de carga básica para validar que la consulta del umbral y ubicación no exceda los tiempos de respuesta aceptables.

## 4. Criterios de Entrada y Salida

### Criterios de Entrada:
*   Entidades de transacción (ID, monto, fecha) y ubicación (País, IP) creadas en base de datos.
*   Ambiente de QA con endpoints de evaluación desplegados.

### Criterios de Salida:
*   100% de los escenarios de aceptación de HU1 y HU2 aprobados.
*   Validación exitosa de casos de borde: montos negativos, nulos y tipos de datos incorrectos en HU1.
*   Verificación de ubicaciones con y sin actividad previa en HU2.

## 5. Entorno de Pruebas
*   **Ambiente:** QA / Local consolidado.
*   **Base de Datos:** Tablas de transacciones y países registradas por usuario.
*   **Configuración:** Datos de prueba inyectados manually para simular el "Umbral X" y la "Ubicación Registrada".

## 6. Herramientas

| Herramienta | Propósito |
| :--- | :--- |
| **SerenityBDD / Cucumber** | Automatización de historias de usuario y reportes de QA. |
| **Karate** | Validación de los servicios de comparación de monto y país. |
| **k6** | Medición de latencia en la lógica de evaluación. |

## 7. Roles y Responsabilidades

### QA:
*   Pruebas de valores límite (mayores, menores e iguales al umbral).
*   Pruebas de formatos distintos (negativos, texto, nulos).
*   Validación de ubicaciones usuales e inusuales.

### DEV:
*   Creación de tablas y entidades de datos.
*   Implementación de la lógica de comparación de montos y países.

## 8. Cronograma y Estimación (Micro-Sprint 2 Días)

| Día | Actividad | Story Points (SP) |
| :--- | :--- | :--- |
| **Día 1** | Pruebas de HU1: Límites de montos y manejo de errores de formato. | 3 |
| **Día 2** | Pruebas de HU2: Validación de país/IP y ejecución de scripts de rendimiento. | 5 |
| **Total** | **Foco en estabilidad de datos base** | **8 SP** |

## 9. Entregables de Prueba
*   Scripts de automatización (Cucumber y Karate).
*   Reporte de ejecución de Serenity con el estatus de las HU1 y HU2.
*   Métricas de rendimiento iniciales (k6).

## 10. Riesgos y Contingencias

*   **Riesgo:** Inexistencia de datos históricos de ubicación para comparar en HU2.
    *   *Mitigación:* Generar un set de datos "Mock" con ubicaciones predefinidas para el usuario de prueba.
*   **Riesgo:** El tiempo de 2 días es ajustado para automatización completa.
    *   *Mitigación:* Priorizar pruebas de API (Karate) sobre UI/E2E para asegurar la lógica de negocio primero.

---

### Resumen de Prioridades (Micro-Sprint)
Para estos 2 días, la prioridad absoluta es la robustez de los datos:
1.  **HU1:** Que el sistema no falle (crashee) ante montos inválidos o vacíos.
2.  **HU2:** Que la lógica de comparación entre el país actual y el registrado sea exacta, sin falsos negativos.
