# 📗 Plan de Pruebas
**Proyecto:** 💳FinTech: Motor de Reglas de Fraude

**Sistema bajo pruebas:** HU1, HU2, HU3 y HU4

**Fecha:** 25/03/2026

**Version:** 1.0

**Equipo:** Silvania Ramos y Maria Jose Castro 

## 1. Contexto
El sistema a probar es un **Motor de Detección de Fraude** en su etapa inicial. El problema de negocio que resuelve es la necesidad de filtrar transacciones sospechosas mediante el análisis de valores inusualmente altos y ubicaciones geográficas no habituales, protegiendo así los activos de los usuarios ante movimientos anómalos.

## 2. Alcance de las Pruebas

### Historias Incluidas:
*   **HU1 – Evaluar monto:** Revisión de cada transacción contra un umbral establecido.
*   **HU2 – Evaluar ubicación:** Comparación de la ubicación actual vs. la habitual del usuario.
*   **HU3 – Marcar sospecha:** Etiquetado de transacciones que no cumplen criterios de seguridad.
*   **HU4 – Nivel de riesgo:** Asignación de categorías (Alto, Medio, Bajo) para priorizar el análisis.

### Historias Excluidas:
*   **HU5 a HU10** (Alertas, registro histórico, revisión manual y configuraciones de administrador).

## 3. Estrategia de Pruebas
La estrategia es de ejecución rápida y automatizada:
*   **Funcional (SerenityBDD + Cucumber):** Validación de los criterios de aceptación de montos (HU1), ubicaciones (HU2), reglas de marcado de sospecha (HU3) y asignación de niveles de riesgo (HU4).
*   **Integración de API (Karate):** Pruebas de contrato y lógica para asegurar que los microservicios devuelven la clasificación y el nivel de riesgo correcto.
*   **Rendimiento (k6):** Prueba de carga básica para validar que la lógica de evaluación y clasificación no degrade los tiempos de respuesta.

## 4. Criterios de Entrada y Salida

### Criterios de Entrada:
*   Entidades de transacción (ID, monto, fecha) y ubicación (País, IP) creadas en base de datos.
*   Ambiente de QA con endpoints de evaluación desplegados.

### Criterios de Salida:
*   100% de los **26 escenarios de prueba** definidos para HU1, HU2, HU3 y HU4 ejecutados.
*   95% de éxito en la ejecución de scripts automatizados (Serenity/Karate).
*   Validación de marcado correcto de sospecha basado en la combinación de HU1/HU2 (HU3).
*   Verificación de la asignación de categorías (Bajo, Medio, Alto) según reglas de negocio (HU4).
*   Manejo exitoso de casos negativos y de borde (Nulos, negativos, tipos incorrectos).

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
*   Pruebas de valores límite y formatos del monto (HU1).
*   Validación de ubicaciones usuales e inusuales (HU2).
*   Pruebas de la lógica de marcado "Sospechosa" / "No Sospechosa" (HU3).
*   Verificación de la jerarquía y lógica de asignación de riesgos (HU4).

### DEV:
*   Creación de tablas y entidades de datos (Transacciones, Ubicaciones, Riesgos).
*   Implementación de la lógica de comparación, marcado de sospecha y categorización de riesgo.

## 8. Cronograma y Estimación 

| Día | Actividad | Esfuerzo (TCs) | Story Points (SP) | Tiempo Estimado |
| :--- | :--- | :---: | :---: | :---: |
| **Día 1** | Setup, Preparación de DB y Datos de Prueba (Mocks). | 2 | 1 | 7-8 horas |
| **Día 2-3** | Automatización HU1 (Montos) y HU3 (Marcado de Sospecha). | 11 | 5 | 14-16 horas |
| **Día 3-4** | Automatización HU2 (Ubicación) y HU4 (Niveles de Riesgo). | 11 | 5 | 14-16 horas |
| **Día 5** | Pruebas de Integración API (Karate) + Performance (k6). | 1 | 0.5 | 7-8 horas |
| **Día 6** | Ejecución Completa, Debugging y Reportes Finales. | 1 | 0.5 | 7-8 horas |
| **Total** | **Cobertura completa de reglas de detección y riesgo** | **26 TCs** | **12 SP** | **~56-64 horas (5-7 días)** |

## 9. Entregables de Prueba
*   Scripts de automatización (Cucumber y Karate).
*   Reporte de ejecución de Serenity con el estatus de las HU1, HU2, HU3 y HU4.
*   Métricas de rendimiento iniciales (k6) sobre los flujos de evaluación completos.
*   Ejecución manual (pantallazo o video corto)
*   Los repositorios de las pruebas de forma independiente
*   Reporte de bug o incidencias

## 10. Riesgos y Contingencias

*   **Riesgo:** Inexistencia de datos históricos de ubicación para comparar en HU2.
    **Mitigación:** Generar un set de datos "Mock" con ubicaciones predefinidas para el usuario de prueba.
*   **Riesgo:** El tiempo de 2 días es ajustado para automatización completa.
    **Mitigación:** Priorizar pruebas de API (Karate) sobre UI/E2E para asegurar la lógica de negocio primero.

---

### Resumen de Prioridades (Micro-Sprint)
1.  **HU1/HU2:** Que la detección de montos inusuales y ubicaciones sea precisa.
2.  **HU3/HU4:** Que toda transacción detectada como inusual sea marcada correctamente como sospechosa y se le asigne el riesgo correspondiente sin errores de flujo.
