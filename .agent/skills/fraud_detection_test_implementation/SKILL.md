---
name: fraud-detection-test-implementation
description: |
  Implements the automated test cases defined in TEST_CASES.md for the Fraud Detection Engine project
  (HU1–HU4), using the exact tools described in TEST_PLAN.md:
  - SerenityBDD + Cucumber → Functional/acceptance tests (HU1, HU2, HU3, HU4)
  - Karate               → API integration/contract tests (HU2, HU3, HU4)
  - k6                   → Performance/load tests (HU1 + HU3 combined flow)
  
  Use this skill whenever the user asks to "implement", "create", "automate", or "write code for"
  one or more test cases from TEST_CASES.md.
---

# 🛠️ Skill: Fraud Detection – Test Case Implementation

## Context

| Document | Path |
|---|---|
| Test Plan | `TEST_PlAN.md` |
| Test Cases | `TEST_CASES.md` |
| User Stories | `USER_STORIES.md` |

## Technology Assignment Matrix

The following table defines which framework implements each test case.
**This mapping is authoritative** — never use a different tool for a TC already assigned here.

| TC ID | HU | Test Type | Technology | Scope |
|---|---|---|---|---|
| TC-001 | HU1 | Functional (acceptance) | **SerenityBDD + Cucumber** | Monto > umbral → Inusual |
| TC-002 | HU1 | Functional | **SerenityBDD + Cucumber** | Monto < umbral → Usual |
| TC-003 | HU1 | Functional (boundary) | **SerenityBDD + Cucumber** | Monto = umbral → Usual |
| TC-004 | HU1 | Functional (boundary) | **SerenityBDD + Cucumber** | Monto = umbral+1 → Inusual |
| TC-005 | HU1 | Negative / Edge | **Karate** | Monto negativo → 400 / dato inválido |
| TC-006 | HU1 | Negative / Edge | **Karate** | Monto null → 400 Bad Request |
| TC-007 | HU1 | Negative / Edge | **Karate** | Monto string → 400 Bad Request |
| TC-008 | HU1 | Boundary | **SerenityBDD + Cucumber** | Monto = 0 → Usual |
| TC-009 | HU1 | Boundary | **SerenityBDD + Cucumber** | Monto extremo → Inusual sin overflow |
| TC-010 | HU2 | Functional | **SerenityBDD + Cucumber** | País diferente → Inusual |
| TC-011 | HU2 | Functional | **SerenityBDD + Cucumber** | País igual → Usual |
| TC-012 | HU2 | Functional | **SerenityBDD + Cucumber** | Paraíso fiscal → Inusual |
| TC-013 | HU2 | Negative / Edge | **Karate** | Sin ubicación registrada → manejo controlado |
| TC-014 | HU2 | Negative / Edge | **Karate** | País vacío "" → 400 Bad Request |
| TC-015 | HU2 | Negative / Edge | **Karate** | País inexistente → error controlado |
| TC-016 | HU2 | Boundary | **SerenityBDD + Cucumber** | Case-insensitive country match → Usual |
| TC-017 | HU2 | Functional | **SerenityBDD + Cucumber** | País diferente (no paraíso) → Inusual |
| TC-018 | HU2 | Negative / Edge | **Karate** | IP null → manejo controlado |
| TC-019 | HU3 | Functional | **SerenityBDD + Cucumber** | Monto alto + país usual → Sospechosa |
| TC-020 | HU3 | Functional | **SerenityBDD + Cucumber** | Monto normal + país inusual → Sospechosa |
| TC-021 | HU3 | Functional | **SerenityBDD + Cucumber** | Monto alto + país inusual → Sospechosa |
| TC-022 | HU3 | Functional | **SerenityBDD + Cucumber** | Monto normal + país usual → No Sospechosa |
| TC-023 | HU4 | API Integration | **Karate** | Doble riesgo → nivel ALTO |
| TC-024 | HU4 | API Integration | **Karate** | Un riesgo (monto) → nivel MEDIO |
| TC-025 | HU4 | API Integration | **Karate** | Un riesgo (ubicación) → nivel MEDIO |
| TC-026 | HU4 | API Integration | **Karate** | Sin riesgo → nivel BAJO |
| TC-PERF | HU1+HU3 | Performance | **k6** | Carga básica: latencia en flujo de evaluación |

> **Regla de oro:** Serenity/Cucumber valida criterios de aceptación (Gherkin). Karate valida el contrato HTTP del API (códigos, campos, tipos). k6 mide tiempos de respuesta bajo carga.

---

## Instructions

When the user asks to implement one or more test cases, follow this exact workflow:

### Step 0 – Identify what to implement

1. Read the TC IDs mentioned by the user.
2. Look up each ID in the **Technology Assignment Matrix** above.
3. Group them by technology: Serenity, Karate, k6.
4. Confirm with the user which project/repo to write into, **or** create new files in the agreed folder structure below.

### Step 1 – Folder Structure

Use the following canonical folder structure (create if it does not exist):

```
Fraud_Detection/
├── serenity-fraud/                    # SerenityBDD + Cucumber project
│   ├── pom.xml
│   └── src/
│       └── test/
│           ├── java/
│           │   └── co/sofka/fraud/
│           │       ├── runners/       # JUnit5 @Suite runners
│           │       ├── steps/         # @Given/@When/@Then definitions
│           │       └── tasks/         # Serenity Tasks (if using Screenplay)
│           └── resources/
│               └── features/
│                   ├── hu1_monto.feature
│                   ├── hu2_ubicacion.feature
│                   ├── hu3_sospechosa.feature
│                   └── hu4_riesgo.feature
│
├── karate-fraud/                      # Karate API tests
│   ├── pom.xml
│   └── src/
│       └── test/
│           ├── java/
│           │   └── co/sofka/fraud/
│           │       └── FraudApiTest.java   # @Karate.Test runner
│           └── resources/
│               └── karate/
│                   ├── karate-config.js
│                   ├── hu1_negative.feature
│                   ├── hu2_negative.feature
│                   └── hu4_risk_levels.feature
│
└── k6-fraud/                          # k6 performance tests
    ├── fraud_load_test.js
    └── thresholds.js
```

### Step 2 – Implement SerenityBDD + Cucumber Tests

**Applicable TCs:** TC-001 to TC-004, TC-008 to TC-012, TC-016 to TC-022

#### 2a. Write the `.feature` file

For each TC assigned to Serenity, create or append a Gherkin `Scenario` in the corresponding feature file.
Use the exact Gherkin text from the `Escenario Gherkin` column in `TEST_CASES.md`.

```gherkin
# Example: hu1_monto.feature
@HU1 @monto
Feature: HU1 – Evaluar monto de la transacción
  Como sistema antifraude quiero revisar el monto de cada transacción
  para identificar valores inusualmente altos.

  Background:
    Given el umbral de monto configurado es 15000

  @TC-001 @critico
  Scenario: Monto mayor al umbral es clasificado como Inusual
    Given una transacción con id "TXN-1001" y monto 20000 del usuario "USR-001"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Inusual"

  @TC-002 @critico
  Scenario: Monto menor al umbral es clasificado como Usual
    Given una transacción con id "TXN-1002" y monto 5000 del usuario "USR-002"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Usual"
    And la transacción no es marcada como sospechosa
```

#### 2b. Write the Step Definitions

Create a Java step definition class, annotated with `@Steps` for Serenity tasks if using Screenplay, or plain `@Given`/`@When`/`@Then` if using step libraries.

```java
// HU1MontoSteps.java
@Slf4j
public class HU1MontoSteps {

    @Steps
    EvaluarMontoTask evaluarMonto;

    private String clasificacion;

    @Given("el umbral de monto configurado es {int}")
    public void configurarUmbral(int umbral) {
        evaluarMonto.configurarUmbral(umbral);
    }

    @Given("una transacción con id {string} y monto {int} del usuario {string}")
    public void crearTransaccion(String txnId, int monto, String usuarioId) {
        evaluarMonto.prepararTransaccion(txnId, monto, usuarioId);
    }

    @When("la transacción es procesada por el sistema")
    public void procesarTransaccion() {
        clasificacion = evaluarMonto.ejecutar();
    }

    @Then("la clasificación retornada es {string}")
    public void verificarClasificacion(String esperada) {
        assertThat(clasificacion).isEqualTo(esperada);
    }

    @Then("la transacción no es marcada como sospechosa")
    public void verificarNoSospechosa() {
        assertThat(evaluarMonto.esSospechosa()).isFalse();
    }
}
```

#### 2c. Runner class

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.sofka.fraud.steps")
public class FraudTestSuite {}
```

#### 2d. `serenity.conf` (minimum required)

```hocon
serenity {
  project.name = "Fraud Detection – HU1-HU4"
  take.screenshots = FOR_FAILURES
}
cucumber.publish.quiet = true
```

---

### Step 3 – Implement Karate Tests

**Applicable TCs:** TC-005, TC-006, TC-007, TC-013, TC-014, TC-015, TC-018, TC-023 to TC-026

#### 3a. `karate-config.js`

```js
function fn() {
  var env = karate.env || 'qa';
  var config = {
    baseUrl: 'http://localhost:8080/api/v1'
  };
  if (env === 'qa') {
    config.baseUrl = 'http://qa.fraud-engine.sofka.co/api/v1';
  }
  return config;
}
```

#### 3b. Feature file – Negativos HU1

```gherkin
# hu1_negative.feature
@HU1 @negative @karate
Feature: HU1 – Casos negativos de evaluación de monto

  Background:
    * url baseUrl

  @TC-005
  Scenario: Monto negativo es rechazado como dato inválido
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1005', monto: -500, fecha: '2026-03-24', usuario_id: 'USR-005' }
    When method POST
    Then status 400
    And match response.mensaje contains 'inválido'

  @TC-006
  Scenario: Monto null es rechazado con 400 Bad Request
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1006', monto: null, fecha: '2026-03-24', usuario_id: 'USR-006' }
    When method POST
    Then status 400
    And match response.mensaje == '#present'

  @TC-007
  Scenario: Monto de tipo texto es rechazado con 400 Bad Request
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1007', monto: 'abc', fecha: '2026-03-24', usuario_id: 'USR-007' }
    When method POST
    Then status 400
    And match response.codigo == 400
```

#### 3c. Feature file – Niveles de riesgo HU4

```gherkin
# hu4_risk_levels.feature
@HU4 @karate
Feature: HU4 – Clasificación de nivel de riesgo

  Background:
    * url baseUrl

  @TC-023 @critico
  Scenario: Doble riesgo (monto alto + país inusual) → nivel ALTO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 25000, pais: 'Corea del Norte', usuario_id: 'USR-023' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'ALTO'

  @TC-024
  Scenario: Solo monto alto → nivel MEDIO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 20000, pais: 'Colombia', usuario_id: 'USR-024' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'MEDIO'

  @TC-025
  Scenario: Solo ubicación inusual → nivel MEDIO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 1000, pais: 'Nigeria', usuario_id: 'USR-025' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'MEDIO'

  @TC-026
  Scenario: Sin riesgo → nivel BAJO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 500, pais: 'Colombia', usuario_id: 'USR-026' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'BAJO'
```

#### 3d. Karate Runner

```java
@Karate.Test
Karate testFraudApi() {
    return Karate.run("classpath:karate")
                 .tags("@karate")
                 .relativeTo(getClass());
}
```

---

### Step 4 – Implement k6 Performance Test

**Applicable scope:** TC-PERF → Full evaluation flow (HU1 amount check + HU3 suspicious marking)

**Target thresholds (from TEST_PLAN.md Criterios de Salida):**
- p95 response time < 500 ms
- Error rate < 5% at 50 VUs

```js
// fraud_load_test.js
import http from 'k6/http';
import { check, sleep, group } from 'k6';
import { Trend, Rate } from 'k6/metrics';

const evaluateTrend  = new Trend('evaluate_amount_duration');
const suspectRate    = new Rate('suspicious_correctly_flagged');

export const options = {
  stages: [
    { duration: '30s', target: 10 },  // ramp-up
    { duration: '1m',  target: 50 },  // steady load
    { duration: '20s', target: 0  },  // ramp-down
  ],
  thresholds: {
    http_req_duration:            ['p(95)<500'],
    http_req_failed:              ['rate<0.05'],
    evaluate_amount_duration:     ['p(95)<400'],
    suspicious_correctly_flagged: ['rate>0.95'],
  },
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080/api/v1';

export default function () {
  group('HU1 – Evaluación de monto', () => {
    const payload = JSON.stringify({
      transaccion_id: `TXN-PERF-${__VU}-${__ITER}`,
      monto: 20000,
      fecha: '2026-03-24',
      usuario_id: 'USR-PERF',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/transacciones/evaluar-monto`, payload, params);

    evaluateTrend.add(res.timings.duration);
    check(res, {
      'status 200':              (r) => r.status === 200,
      'clasificacion Inusual':   (r) => JSON.parse(r.body).clasificacion === 'Inusual',
    });
  });

  group('HU3 – Marcado de sospecha', () => {
    const payload = JSON.stringify({
      monto: 20000,
      pais: 'Islas Caimán',
      usuario_id: 'USR-PERF',
    });
    const params = { headers: { 'Content-Type': 'application/json' } };
    const res = http.post(`${BASE_URL}/transacciones/marcar-sospecha`, payload, params);

    const body = JSON.parse(res.body);
    suspectRate.add(body.sospechosa === true);
    check(res, {
      'status 200':          (r) => r.status === 200,
      'marcado como Sospechosa': (r) => body.sospechosa === true,
    });
  });

  sleep(1);
}
```

**Run command:**
```bash
k6 run --env BASE_URL=http://qa.fraud-engine.sofka.co/api/v1 k6-fraud/fraud_load_test.js
```

---

## Execution Commands

| Technology | Command |
|---|---|
| Serenity (all) | `mvn clean verify -f serenity-fraud/pom.xml` |
| Serenity (tag) | `mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@TC-001"` |
| Karate (all) | `mvn clean test -f karate-fraud/pom.xml` |
| Karate (tag) | `mvn clean test -f karate-fraud/pom.xml -Dkarate.options="--tags @TC-005"` |
| k6 | `k6 run k6-fraud/fraud_load_test.js` |

---

## Post-Implementation Checklist

After writing each test case, update `TEST_CASES.md`:

- [ ] Change `**Estado**` from `🔘 Sin ejecutar` → `✅ Pasó` or `❌ Falló`
- [ ] Fill in `**Resultado obtenido**` with the actual API response or assertion result
- [ ] Update the **Estado General de Ejecución** summary table at the bottom of `TEST_CASES.md`

---

## Key Domain Rules (from TEST_PLAN.md)

| Rule | Value |
|---|---|
| Monto umbral | `> 15,000` = Inusual |
| Monto exactamente al umbral | `= 15,000` = Usual |
| Ubicación habitual por defecto | Colombia |
| Clasificación de riesgo ALTO | Monto INUSUAL **AND** Ubicación INUSUAL |
| Clasificación de riesgo MEDIO | Monto INUSUAL **OR** Ubicación INUSUAL (solo uno) |
| Clasificación de riesgo BAJO | Monto USUAL **AND** Ubicación USUAL |
| Sospechosa = true | Si `nivel_riesgo` es ALTO o MEDIO |
| Sospechosa = false | Si `nivel_riesgo` es BAJO |
