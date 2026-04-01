# Key Domain Rules

Rules extracted from TEST_PLAN.md and TEST_CASES.md that govern fraud detection logic.

| Rule | Value |
|---|---|
| Monto umbral | `> 15,000` = Inusual |
| Monto exactamente al umbral | `= 15,000` = Usual |
| Ubicación habitual por defecto | Colombia |
| **Clasificación de riesgo ALTO** | Monto INUSUAL **AND** Ubicación INUSUAL |
| **Clasificación de riesgo MEDIO** | Monto INUSUAL **OR** Ubicación INUSUAL (solo uno) |
| **Clasificación de riesgo BAJO** | Monto USUAL **AND** Ubicación USUAL |
| **Sospechosa = true** | Si `nivel_riesgo` es ALTO o MEDIO |
| **Sospechosa = false** | Si `nivel_riesgo` es BAJO |

## Validation Rules

- Monto: Must be numeric and non-negative; null/negative → 400 Bad Request
- País: Must exist in catalog; empty/invalid → 400 Bad Request
- Ubicación: Case-insensitive comparison
- IP: Optional; null is acceptable with country-based fallback
