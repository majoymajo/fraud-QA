---
name: test-implementation
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

## Quick Navigation

| Resource | Purpose |
|----------|---------|
| [Technology Matrix](./references/technology-matrix.md) | Which framework for each TC (authoritative) |
| [Domain Rules](./references/domain-rules.md) | Monto thresholds, risk levels, validation rules |
| [Folder Structure](./references/folder-structure.md) | Canonical project layout |
| [Execution Guide](./references/execution-guide.md) | Maven and k6 commands with examples |
| [SerenityBDD Setup](./references/serenity-setup.md) | HU1-HU4 functional tests (14 TCs) |
| [Karate Setup](./references/karate-setup.md) | API contract tests (10 TCs) |
| [k6 Setup](./references/k6-setup.md) | Performance testing (TC-PERF) |

## Context Documents

| Document | Location |
|---|---|
| Test Plan | `	../../../Docs/TEST_PlAN.md` |
| Test Cases | `	../../../Docs/TEST_CASES.md` |
| User Stories | `	../../../Docs/USER_STORIES.md` |

---

## Core Workflow

When the user asks to **implement, create, automate, or write code** for one or more test cases:

### Step 0 – Identify what to implement

1. Read the TC IDs mentioned by the user.
2. Look up each ID in [Technology Assignment Matrix](./references/technology-matrix.md).
3. Group them by technology: **Serenity**, **Karate**, or **k6**.

### Step 1 – Project Structure

Follow [Canonical Folder Structure](./references/folder-structure.md) to organize:
- `serenity-fraud/` for acceptance tests
- `karate-fraud/` for API contract tests
- `k6-fraud/` for performance tests

### Step 2 – Implement SerenityBDD + Cucumber

**Applicable TCs:** TC-001–TC-004, TC-008–TC-012, TC-016–TC-022 (14 tests)

1. Write Gherkin features in `scripts/features/hu*.feature`
2. Create Java step definitions in `scripts/steps/HU*.java`
3. Use runner class from `scripts/runners/FraudTestSuite.java`
4. Configure with `scripts/config/serenity.conf`

📖 **Full guide:** [SerenityBDD Setup](./references/serenity-setup.md)

### Step 3 – Implement Karate Tests

**Applicable TCs:** TC-005–TC-007, TC-013–TC-015, TC-018, TC-023–TC-026 (10 tests)

1. Write feature files in `scripts/features/hu*_negative.feature` and `hu4_riesgo.feature`
2. Configure with `scripts/config/karate-config.js`
3. Use runner from `scripts/runners/KarateRunner.java`

📖 **Full guide:** [Karate Setup](./references/karate-setup.md)

### Step 4 – Implement k6 Performance Test

**Scope:** TC-PERF → Full evaluation flow (HU1 + HU3)

1. Use template from `scripts/k6/fraud_load_test.js`
2. Target thresholds: p95 < 500ms, error rate < 5%

📖 **Full guide:** [k6 Setup](./references/k6-setup.md)

---

## Execution

Run tests with commands from [Execution Guide](./references/execution-guide.md):

```bash
# Serenity
mvn clean verify -f serenity-fraud/pom.xml

# Karate
mvn clean test -f karate-fraud/pom.xml

# k6
k6 run k6-fraud/fraud_load_test.js
```

---

## Post-Implementation Checklist

After implementing tests:

- [ ] Feature files created in `scripts/features/`
- [ ] Step definitions created in `scripts/steps/`
- [ ] Runner configured (Serenity/Karate)
- [ ] Config files in place (serenity.conf, karate-config.js)
- [ ] Tests executed and passing
- [ ] Update [TEST_CASES.md](../../../Docs/TEST_CASES.md) with results:
  - Change `**Estado**` from `🔘 Sin ejecutar` → `✅ Pasó` or `❌ Falló`
  - Fill `**Resultado obtenido**` with actual response
  - Update **Estado General de Ejecución** table

---

## File Structure

```
.agent/skills/fraud_detection_test_implementation/
├── SKILL.md                        ← Core instructions (this file)
├── scripts/                        ← Ready-to-use code templates
│   ├── features/                   ← Gherkin feature files
│   ├── steps/                      ← Java step definitions
│   ├── runners/                    ← Test runners
│   ├── config/                     ← Config files
│   └── k6/                         ← k6 test scripts
├── references/                     ← Domain knowledge & guides
│   ├── technology-matrix.md        ← TC-to-framework mapping
│   ├── domain-rules.md             ← Business rules
│   ├── execution-guide.md          ← Commands
│   ├── serenity-setup.md           ← SerenityBDD guide
│   ├── karate-setup.md             ← Karate guide
│   ├── k6-setup.md                 ← k6 guide
│   └── folder-structure.md         ← Project layout
├── assets/                         ← Templates & data
│   ├── pom-serenity-template.xml
│   └── pom-karate-template.xml
└── examples/                       ← Sample inputs/outputs
    ├── request-payloads.json
    └── expected-responses.json
```
