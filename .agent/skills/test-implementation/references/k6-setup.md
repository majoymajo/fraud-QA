# k6 – Performance Testing Setup

## Overview

k6 tests measure **response times under load**.
Scope: **TC-PERF** → Full evaluation flow (HU1 amount check + HU3 suspicious marking)

## Target Thresholds

From TEST_PLAN.md Criterios de Salida:
- **p95 response time** < 500 ms
- **Error rate** < 5% at 50 VUs
- **Suspicious detection rate** > 95%

## Load Profile

```
Stage 1: Ramp-up    → 30s, target 10 VUs
Stage 2: Steady     → 1m,  target 50 VUs
Stage 3: Ramp-down  → 20s, target 0 VUs
```

## File References

- **Main test script:** `scripts/k6/fraud_load_test.js`

## Execution

### Local Environment
```bash
k6 run scripts/k6/fraud_load_test.js
```

### QA Environment
```bash
k6 run --env BASE_URL=http://qa.fraud-engine.sofka.co/api/v1 scripts/k6/fraud_load_test.js
```

### Custom Load Profile
```bash
k6 run --vus 100 --duration 5m scripts/k6/fraud_load_test.js
```

## Output

k6 prints summary to console:
```
  ✓ status 200
  ✓ clasificacion Inusual
  ✓ marcado como Sospechosa

  http_req_duration....................: avg=240ms p(95)=450ms
  http_req_failed.......................: 0.00%
  evaluate_amount_duration..............: avg=200ms p(95)=380ms
  suspicious_correctly_flagged..........: 96.00%
```

## Thresholds Check

- If `http_req_duration p(95)` > 500ms → **FAIL**
- If `http_req_failed` > 5% → **FAIL**
- If `suspicious_correctly_flagged` < 95% → **FAIL**

Fix by optimizing endpoint or adjusting load profile.
