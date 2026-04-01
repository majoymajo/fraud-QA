# Technology Assignment Matrix

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

## Gold Rule

- **Serenity/Cucumber** validates acceptance criteria (Gherkin scenarios)
- **Karate** validates HTTP API contract (status codes, fields, types)
- **k6** measures response times under load
