# 📋 Matriz de Casos de Prueba

---

## Índice
1. [HU1 – Evaluar monto de la transacción](#hu1--evaluar-monto-de-la-transacción)
2. [HU2 – Evaluar ubicación de la transacción](#hu2--evaluar-ubicación-de-la-transacción)
3. [HU3 – Marcar transacción sospechosa](#hu3--marcar-transacción-sospechosa)
4. [HU4 – Clasificar nivel de riesgo](#hu4--clasificar-nivel-de-riesgo)
5. [Resumen de Cobertura](#resumen-de-cobertura)

---

## HU1 – Evaluar monto de la transacción

> **Como** sistema antifraude **quiero** revisar el monto de cada transacción que se realiza **para** identificar valores inusualmente altos.

### Casos de Prueba — HU1

| Campo | TC-001 |
| :--- | :--- |
| **ID** | TC-001 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de una transacción excede el umbral establecido (15,000) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema clasifica la transacción como "Inusual" |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible con la tabla de transacciones. Endpoint de evaluación de monto desplegado en ambiente QA. |
| **Datos de entrada** | `transaccion_id: TXN-1001`, `monto: 20000`, `fecha: 2026-03-24`, `usuario_id: USR-001` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto 20,000 en la base de datos. 2. Invocar el endpoint de evaluación de monto. 3. Verificar la respuesta del servicio. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-1001. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-002 |
| :--- | :--- |
| **ID** | TC-002 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es menor al umbral establecido (15,000) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema no clasifica la transacción como movimiento sospechoso |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible con la tabla de transacciones. Endpoint de evaluación de monto desplegado en ambiente QA. |
| **Datos de entrada** | `transaccion_id: TXN-1002`, `monto: 5000`, `fecha: 2026-03-24`, `usuario_id: USR-002` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto 5,000 en la base de datos. 2. Invocar el endpoint de evaluación de monto. 3. Verificar la respuesta del servicio. |
| **Resultado esperado** | El sistema retorna la clasificación `"Usual"` para la transacción TXN-1002. La transacción no es marcada como sospechosa. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-003 |
| :--- | :--- |
| **ID** | TC-003 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es exactamente igual al umbral establecido (15,000) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema no clasifica la transacción como movimiento sospechoso |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible con la tabla de transacciones. Endpoint de evaluación de monto desplegado en ambiente QA. |
| **Datos de entrada** | `transaccion_id: TXN-1003`, `monto: 15000`, `fecha: 2026-03-24`, `usuario_id: USR-003` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto exacto de 15,000 en la base de datos. 2. Invocar el endpoint de evaluación de monto. 3. Verificar la respuesta del servicio. |
| **Resultado esperado** | El sistema retorna la clasificación `"Usual"` para la transacción TXN-1003. El monto exactamente igual al umbral no se considera inusual (condición: `monto > umbral`). |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-004 |
| :--- | :--- |
| **ID** | TC-004 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción excede el umbral por una unidad mínima (15,001) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema clasifica la transacción como "Inusual" |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible con la tabla de transacciones. Endpoint de evaluación de monto desplegado en ambiente QA. |
| **Datos de entrada** | `transaccion_id: TXN-1004`, `monto: 15001`, `fecha: 2026-03-24`, `usuario_id: USR-004` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto 15,001 en la base de datos. 2. Invocar el endpoint de evaluación de monto. 3. Verificar la respuesta del servicio en el límite superior. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-1004. El monto que excede el umbral por la unidad mínima debe ser detectado correctamente. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-005 |
| :--- | :--- |
| **ID** | TC-005 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es un valor negativo **Cuando** la transacción es procesada por el sistema **Entonces** el sistema rechaza la transacción o la clasifica como dato inválido sin generar un error de sistema |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible. Endpoint de evaluación desplegado. |
| **Datos de entrada** | `transaccion_id: TXN-1005`, `monto: -500`, `fecha: 2026-03-24`, `usuario_id: USR-005` |
| **Pasos de ejecución** | 1. Intentar insertar la transacción con monto negativo. 2. Invocar el endpoint de evaluación de monto. 3. Verificar que el sistema no genere una excepción no controlada (crash). 4. Verificar el mensaje de error o respuesta de validación. |
| **Resultado esperado** | El sistema maneja el monto negativo de forma controlada: retorna un mensaje de validación indicando dato inválido **sin interrumpir su funcionamiento** (sin crash). |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-006 |
| :--- | :--- |
| **ID** | TC-006 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es nulo (null) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema rechaza la transacción o la clasifica como dato inválido sin generar un error de sistema |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible. Endpoint de evaluación desplegado. |
| **Datos de entrada** | `transaccion_id: TXN-1006`, `monto: null`, `fecha: 2026-03-24`, `usuario_id: USR-006` |
| **Pasos de ejecución** | 1. Intentar insertar la transacción con monto nulo. 2. Invocar el endpoint de evaluación de monto. 3. Verificar que el sistema no genere una excepción no controlada (crash). 4. Verificar el mensaje de error o respuesta de validación. |
| **Resultado esperado** | El sistema maneja el monto nulo de forma controlada: retorna un código de error apropiado (ej. 400 Bad Request) y un mensaje descriptivo **sin interrumpir su funcionamiento**. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-007 |
| :--- | :--- |
| **ID** | TC-007 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es un valor de tipo texto (string) en lugar de numérico **Cuando** la transacción es procesada por el sistema **Entonces** el sistema rechaza la transacción indicando tipo de dato incorrecto sin generar un error de sistema |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible. Endpoint de evaluación desplegado. |
| **Datos de entrada** | `transaccion_id: TXN-1007`, `monto: "abc"`, `fecha: 2026-03-24`, `usuario_id: USR-007` |
| **Pasos de ejecución** | 1. Enviar la transacción con monto de tipo texto al endpoint de evaluación. 2. Verificar que el sistema no genere una excepción no controlada (crash). 3. Verificar el código de respuesta HTTP y el mensaje de error. |
| **Resultado esperado** | El sistema rechaza la petición con un código de error apropiado (ej. 400 Bad Request) indicando que el tipo de dato del monto es incorrecto **sin interrumpir su funcionamiento**. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-008 |
| :--- | :--- |
| **ID** | TC-008 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es cero (0) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema clasifica la transacción como "Usual" sin generar error |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible. Endpoint de evaluación desplegado. |
| **Datos de entrada** | `transaccion_id: TXN-1008`, `monto: 0`, `fecha: 2026-03-24`, `usuario_id: USR-008` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto 0 en la base de datos. 2. Invocar el endpoint de evaluación de monto. 3. Verificar la clasificación retornada. |
| **Resultado esperado** | El sistema retorna la clasificación `"Usual"` para la transacción TXN-1008. Un monto de 0 está por debajo del umbral y no debe generar errores. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟡 Medio |

---

| Campo | TC-009 |
| :--- | :--- |
| **ID** | TC-009 |
| **Historia de Usuario** | HU1 – Evaluar monto de la transacción |
| **Escenario Gherkin** | **Dado** que el monto de la transacción es un valor extremadamente alto (ej. 999,999,999) **Cuando** la transacción es procesada por el sistema **Entonces** el sistema clasifica la transacción como "Inusual" sin desbordamiento ni error |
| **Precondiciones** | Umbral de monto configurado en 15,000. Base de datos disponible. Endpoint de evaluación desplegado. |
| **Datos de entrada** | `transaccion_id: TXN-1009`, `monto: 999999999`, `fecha: 2026-03-24`, `usuario_id: USR-009` |
| **Pasos de ejecución** | 1. Insertar la transacción con monto extremadamente alto. 2. Invocar el endpoint de evaluación de monto. 3. Verificar que no ocurra desbordamiento de tipo numérico. 4. Verificar la clasificación retornada. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-1009, sin errores de desbordamiento numérico (overflow). |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟡 Medio |

---

## HU2 – Evaluar ubicación de la transacción

> **Como** sistema antifraude **quiero** detectar la ubicación de cada transacción comparándola con la ubicación habitual del usuario **para** identificar si es sospechosa.

### Casos de Prueba — HU2

| Campo | TC-010 |
| :--- | :--- |
| **ID** | TC-010 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción desde una ubicación diferente ("Islas Caimán") **Entonces** el sistema detecta la transacción como "Inusual" |
| **Precondiciones** | Usuario USR-010 con ubicación habitual registrada como "Colombia" en la base de datos. Tabla de países y ubicaciones disponible. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2001`, `usuario_id: USR-010`, `pais_habitual: "Colombia"`, `pais_transaccion: "Islas Caimán"`, `ip: 192.168.10.50` |
| **Pasos de ejecución** | 1. Verificar que el usuario USR-010 tiene registrada "Colombia" como ubicación habitual. 2. Enviar la transacción con país de origen "Islas Caimán" al endpoint de evaluación. 3. Verificar la clasificación retornada por el servicio. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-2001. La ubicación "Islas Caimán" difiere de la ubicación habitual "Colombia". |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-011 |
| :--- | :--- |
| **ID** | TC-011 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción desde esa misma ubicación ("Colombia") **Entonces** el sistema detecta la transacción como "Usual" |
| **Precondiciones** | Usuario USR-011 con ubicación habitual registrada como "Colombia" en la base de datos. Tabla de países y ubicaciones disponible. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2002`, `usuario_id: USR-011`, `pais_habitual: "Colombia"`, `pais_transaccion: "Colombia"`, `ip: 181.49.12.100` |
| **Pasos de ejecución** | 1. Verificar que el usuario USR-011 tiene registrada "Colombia" como ubicación habitual. 2. Enviar la transacción con país de origen "Colombia" al endpoint de evaluación. 3. Verificar la clasificación retornada por el servicio. |
| **Resultado esperado** | El sistema retorna la clasificación `"Usual"` para la transacción TXN-2002. La ubicación coincide con la ubicación habitual del usuario. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-012 |
| :--- | :--- |
| **ID** | TC-012 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción desde un paraíso fiscal ("Panamá") **Entonces** el sistema detecta la transacción como "Inusual" |
| **Precondiciones** | Usuario USR-012 con ubicación habitual registrada como "Colombia". Lista de paraísos fiscales configurada en el sistema. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2003`, `usuario_id: USR-012`, `pais_habitual: "Colombia"`, `pais_transaccion: "Panamá"`, `ip: 200.46.81.15` |
| **Pasos de ejecución** | 1. Verificar que el usuario USR-012 tiene registrada "Colombia" como ubicación habitual. 2. Enviar la transacción desde "Panamá" (paraíso fiscal) al endpoint de evaluación. 3. Verificar la clasificación retornada. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-2003. Una transacción desde un paraíso fiscal fuera del país habitual del usuario debe ser detectada como inusual. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-013 |
| :--- | :--- |
| **ID** | TC-013 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que el usuario no tiene ubicación habitual registrada en el sistema **Cuando** se realiza una transacción desde cualquier ubicación **Entonces** el sistema maneja el caso sin generar error y clasifica la transacción según la política por defecto |
| **Precondiciones** | Usuario USR-013 sin registros de ubicación habitual en la base de datos. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2004`, `usuario_id: USR-013`, `pais_habitual: null`, `pais_transaccion: "México"`, `ip: 189.203.15.42` |
| **Pasos de ejecución** | 1. Verificar que el usuario USR-013 no tiene ubicación habitual registrada. 2. Enviar la transacción al endpoint de evaluación. 3. Verificar que el sistema no genere una excepción no controlada (crash). 4. Verificar la clasificación o mensaje de respuesta retornado. |
| **Resultado esperado** | El sistema maneja la ausencia de datos históricos de ubicación de forma controlada: no genera crash y aplica la política por defecto (ej. clasificar como `"Inusual"` o marcar para revisión). |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-014 |
| :--- | :--- |
| **ID** | TC-014 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción con el campo país vacío ("") **Entonces** el sistema rechaza la transacción o la clasifica como dato inválido sin generar un error de sistema |
| **Precondiciones** | Usuario USR-014 con ubicación habitual registrada. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2005`, `usuario_id: USR-014`, `pais_habitual: "Colombia"`, `pais_transaccion: ""`, `ip: 10.0.0.1` |
| **Pasos de ejecución** | 1. Enviar la transacción con campo país vacío al endpoint de evaluación. 2. Verificar que el sistema no genere una excepción no controlada (crash). 3. Verificar el código de respuesta y el mensaje de error. |
| **Resultado esperado** | El sistema retorna un código de error apropiado (ej. 400 Bad Request) indicando que el campo de ubicación es obligatorio, **sin interrumpir su funcionamiento**. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-015 |
| :--- | :--- |
| **ID** | TC-015 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción con un país que no existe en el catálogo del sistema ("PaísInexistente") **Entonces** el sistema rechaza la transacción indicando país no reconocido sin generar un error de sistema |
| **Precondiciones** | Usuario USR-015 con ubicación habitual registrada. Catálogo de países configurado en el sistema. Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2006`, `usuario_id: USR-015`, `pais_habitual: "Colombia"`, `pais_transaccion: "PaísInexistente"`, `ip: 10.0.0.2` |
| **Pasos de ejecución** | 1. Enviar la transacción con un país no existente en el catálogo. 2. Verificar que el sistema no genere una excepción no controlada (crash). 3. Verificar el código de respuesta y el mensaje de error. |
| **Resultado esperado** | El sistema retorna un código de error apropiado indicando que el país no fue reconocido en el catálogo, **sin interrumpir su funcionamiento**. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟡 Medio |

---

| Campo | TC-016 |
| :--- | :--- |
| **ID** | TC-016 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "colombia" (minúsculas) **Cuando** se realiza una transacción desde "Colombia" (capitalizado) **Entonces** el sistema detecta la transacción como "Usual" sin generar falso negativo por diferencia de formato |
| **Precondiciones** | Usuario USR-016 con ubicación habitual registrada como "colombia" (todo en minúsculas). Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2007`, `usuario_id: USR-016`, `pais_habitual: "colombia"`, `pais_transaccion: "Colombia"`, `ip: 181.49.12.200` |
| **Pasos de ejecución** | 1. Registrar la ubicación habitual del usuario en minúsculas ("colombia"). 2. Enviar la transacción con el país capitalizado ("Colombia"). 3. Verificar que la comparación sea case-insensitive. 4. Verificar la clasificación retornada. |
| **Resultado esperado** | El sistema retorna la clasificación `"Usual"` para la transacción TXN-2007. La comparación de ubicaciones debe ser insensible a mayúsculas/minúsculas para evitar falsos negativos. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-017 |
| :--- | :--- |
| **ID** | TC-017 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción desde "Estados Unidos" (país diferente pero no paraíso fiscal) **Entonces** el sistema detecta la transacción como "Inusual" |
| **Precondiciones** | Usuario USR-017 con ubicación habitual registrada como "Colombia". Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2008`, `usuario_id: USR-017`, `pais_habitual: "Colombia"`, `pais_transaccion: "Estados Unidos"`, `ip: 72.14.207.99` |
| **Pasos de ejecución** | 1. Verificar que el usuario USR-017 tiene registrada "Colombia" como ubicación habitual. 2. Enviar la transacción desde "Estados Unidos" al endpoint de evaluación. 3. Verificar la clasificación retornada. |
| **Resultado esperado** | El sistema retorna la clasificación `"Inusual"` para la transacción TXN-2008. Cualquier ubicación diferente a la habitual debe ser clasificada como inusual, independientemente de si es o no un paraíso fiscal. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-018 |
| :--- | :--- |
| **ID** | TC-018 |
| **Historia de Usuario** | HU2 – Evaluar ubicación de la transacción |
| **Escenario Gherkin** | **Dado** que la ubicación habitual del usuario está registrada como "Colombia" **Cuando** se realiza una transacción con un campo IP nulo (null) **Entonces** el sistema maneja el caso sin generar crash y clasifica según la información de país disponible |
| **Precondiciones** | Usuario USR-018 con ubicación habitual registrada como "Colombia". Endpoint de evaluación de ubicación desplegado en QA. |
| **Datos de entrada** | `transaccion_id: TXN-2009`, `usuario_id: USR-018`, `pais_habitual: "Colombia"`, `pais_transaccion: "Colombia"`, `ip: null` |
| **Pasos de ejecución** | 1. Enviar la transacción con campo IP nulo al endpoint de evaluación. 2. Verificar que el sistema no genere una excepción no controlada (crash). 3. Verificar que la evaluación se realice con base en el campo de país disponible. |
| **Resultado esperado** | El sistema maneja la IP nula de forma controlada: evalúa la transacción usando el campo de país y retorna una clasificación válida **sin interrumpir su funcionamiento**. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟡 Medio |

---

## HU3 – Marcar transacción sospechosa

> **Como** sistema antifraude **quiero** marcar transacciones como sospechosas cuando cumplan los criterios (ej. monto alto o ubicación inusual) **para** permitir su revisión.

### Casos de Prueba — HU3

| Campo | TC-019 |
| :--- | :--- |
| **ID** | TC-019 |
| **Historia de Usuario** | HU3 – Marcar transacción sospechosa |
| **Escenario Gherkin** | **Dado** que el monto de una transacción excede el umbral (20,000) **Y** la ubicación es usual ("Colombia") **Cuando** la transacción es procesada **Entonces** el sistema marca la transacción como "Sospechosa" |
| **Precondiciones** | Umbral: 15,000. Registro habitual: Colombia. |
| **Datos de entrada** | `monto: 20000`, `pais: "Colombia"` |
| **Pasos de ejecución** | 1. Enviar transacción con monto alto y ubicación usual. 2. Verificar campo `sospechosa` en la respuesta. |
| **Resultado esperado** | `sospechosa: true`. La regla de monto activa la alerta. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-020 |
| :--- | :--- |
| **ID** | TC-020 |
| **Historia de Usuario** | HU3 – Marcar transacción sospechosa |
| **Escenario Gherkin** | **Dado** que el monto de una transacción es normal (5,000) **Y** la ubicación es inusual ("Rusia") **Cuando** la transacción es procesada **Entonces** el sistema marca la transacción como "Sospechosa" |
| **Precondiciones** | Umbral: 15,000. Registro habitual: Colombia. |
| **Datos de entrada** | `monto: 5000`, `pais: "Rusia"` |
| **Pasos de ejecución** | 1. Enviar transacción con monto normal y ubicación inusual. 2. Verificar campo `sospechosa` en la respuesta. |
| **Resultado esperado** | `sospechosa: true`. La regla de ubicación activa la alerta. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-021 |
| :--- | :--- |
| **ID** | TC-021 |
| **Historia de Usuario** | HU3 – Marcar transacción sospechosa |
| **Escenario Gherkin** | **Dado** que el monto excede el umbral (30,000) **Y** la ubicación es inusual ("Islas Caimán") **Cuando** la transacción es procesada **Entonces** el sistema marca la transacción como "Sospechosa" |
| **Precondiciones** | Umbral: 15,000. Registro habitual: Colombia. |
| **Datos de entrada** | `monto: 30000`, `pais: "Islas Caimán"` |
| **Pasos de ejecución** | 1. Enviar transacción con ambos criterios de riesgo activos. 2. Verificar campo `sospechosa`. |
| **Resultado esperado** | `sospechosa: true`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-022 |
| :--- | :--- |
| **ID** | TC-022 |
| **Historia de Usuario** | HU3 – Marcar transacción sospechosa |
| **Escenario Gherkin** | **Dado** que el monto es normal (2,000) **Y** la ubicación es usual ("Colombia") **Cuando** la transacción es procesada **Entonces** el sistema marca la transacción como "No Sospechosa" |
| **Precondiciones** | Umbral: 15,000. Registro habitual: Colombia. |
| **Datos de entrada** | `monto: 2000`, `pais: "Colombia"` |
| **Pasos de ejecución** | 1. Enviar transacción legítima (monto bajo, país habitual). 2. Verificar campo `no sospechosa`. |
| **Resultado esperado** | `no sospechosa: true`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

## HU4 – Clasificar nivel de riesgo

> **Como** sistema antifraude **quiero** asignar una categoría de riesgo (Alto, Medio, Bajo) a cada transacción **para** priorizar su análisis.

### Casos de Prueba — HU4

| Campo | TC-023 |
| :--- | :--- |
| **ID** | TC-023 |
| **Historia de Usuario** | HU4 – Clasificar nivel de riesgo |
| **Escenario Gherkin** | **Dado** que una transacción tiene monto alto (25,000) **Y** ubicación inusual ("Corea del Norte") **Cuando** el sistema evalúa el riesgo **Entonces** asigna el nivel de riesgo "ALTO" |
| **Precondiciones** | Dos reglas de riesgo activadas simultáneamente. |
| **Datos de entrada** | `monto: 25000`, `pais: "Corea del Norte"` |
| **Pasos de ejecución** | 1. Ejecutar evaluación con doble factor de riesgo. 2. Validar el campo `nivel_riesgo` en la respuesta. |
| **Resultado esperado** | `nivel_riesgo: "ALTO"`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🔴 Crítico |

---

| Campo | TC-024 |
| :--- | :--- |
| **ID** | TC-024 |
| **Historia de Usuario** | HU4 – Clasificar nivel de riesgo |
| **Escenario Gherkin** | **Dado** que una transacción tiene monto alto (20,000) **PERO** ubicación habitual ("Colombia") **Cuando** el sistema evalúa el riesgo **Entonces** asigna el nivel de riesgo "MEDIO" |
| **Precondiciones** | Solo una regla de riesgo activada (Monto). |
| **Datos de entrada** | `monto: 20000`, `pais: "Colombia"` |
| **Pasos de ejecución** | 1. Ejecutar evaluación con un solo factor (monto). 2. Validar el campo `nivel_riesgo`. |
| **Resultado esperado** | `nivel_riesgo: "MEDIO"`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-025 |
| :--- | :--- |
| **ID** | TC-025 |
| **Historia de Usuario** | HU4 – Clasificar nivel de riesgo |
| **Escenario Gherkin** | **Dado** que una transacción tiene monto normal (1,000) **PERO** ubicación inusual ("Nigeria") **Cuando** el sistema evalúa el riesgo **Entonces** asigna el nivel de riesgo "MEDIO" |
| **Precondiciones** | Solo una regla de riesgo activada (Ubicación). |
| **Datos de entrada** | `monto: 1000`, `pais: "Nigeria"` |
| **Pasos de ejecución** | 1. Ejecutar evaluación con un solo factor (ubicación). 2. Validar el campo `nivel_riesgo`. |
| **Resultado esperado** | `nivel_riesgo: "MEDIO"`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟠 Alto |

---

| Campo | TC-026 |
| :--- | :--- |
| **ID** | TC-026 |
| **Historia de Usuario** | HU4 – Clasificar nivel de riesgo |
| **Escenario Gherkin** | **Dado** que una transacción tiene monto normal (500) **Y** ubicación habitual ("Colombia") **Cuando** el sistema evalúa el riesgo **Entonces** asigna el nivel de riesgo "BAJO" |
| **Precondiciones** | Ninguna regla de riesgo activada. |
| **Datos de entrada** | `monto: 500`, `pais: "Colombia"` |
| **Pasos de ejecución** | 1. Ejecutar evaluación de transacción sin riesgos. 2. Validar el campo `nivel_riesgo`. |
| **Resultado esperado** | `nivel_riesgo: "BAJO"`. |
| **Resultado obtenido** | Sin ejecutar |
| **Estado** | 🔘 Sin ejecutar |
| **Prioridad** | 🟡 Medio |

---

## Resumen de Cobertura

### Totales por Historia de Usuario

| Historia de Usuario | Total de Casos | Crítico | Alto | Medio | Bajo |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **HU1 – Evaluar monto** | 9 | 5 | 2 | 2 | 0 |
| **HU2 – Evaluar ubicación** | 9 | 4 | 3 | 2 | 0 |
| **HU3 – Marcar sospecha** | 4 | 4 | 0 | 0 | 0 |
| **HU4 – Nivel de riesgo** | 4 | 1 | 2 | 1 | 0 |
| **Total** | **26** | **14** | **7** | **5** | **0** |

### Estado General de Ejecución

| Estado | Cantidad | Porcentaje |
| :--- | :---: | :---: |
| 🔘 Sin ejecutar | 26 | 100% |
| ✅ Pasó | 0 | 0% |
| ❌ Falló | 0 | 0% |

---

> **Nota:** Este documento es un entregable documental. Ningún caso de prueba ha sido ejecutado. Los campos de "Resultado obtenido" y "Estado" se actualizarán conforme avance la ejecución del Micro-Sprint.
