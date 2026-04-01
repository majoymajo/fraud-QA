# Karate – Setup & Examples

## Overview

Karate tests validate **HTTP API contracts** (status codes, fields, types).
Used for TCs: **TC-005 to TC-007, TC-013 to TC-015, TC-018, TC-023 to TC-026**.

## Step-by-Step

### 1. Create karate-config.js

Place in `src/test/resources/karate/karate-config.js`.
Defines base URL and environment-specific settings.

**Example file location:** `scripts/config/karate-config.js`

### 2. Write Feature Files

Create `.feature` files in `src/test/resources/karate/`.
Use Karate syntax: `Given`, `When`, `Then`, `And`, with `path`, `request`, `method`, `status`, `match`.

**Example file locations:**
- `scripts/features/hu1_negative.feature` (HU1 negative cases: TC-005, TC-006, TC-007)
- `scripts/features/hu2_negative.feature` (HU2 negative cases: TC-013 to TC-015, TC-018)
- `scripts/features/hu4_risk_levels.feature` (HU4 risk classification: TC-023 to TC-026)

### 3. Create Test Runner

Create Java test class in `src/test/java/co/sofka/fraud/FraudApiTest.java`.
Use `@Karate.Test` annotation.

**Example file location:** `scripts/runners/KarateRunner.java`

## File References

- **Config:** See `scripts/config/karate-config.js`
- **Feature files:** See `scripts/features/hu*_negative.feature` and `hu4_risk_levels.feature`
- **Runner:** See `scripts/runners/KarateRunner.java`

## Execution

```bash
# All tests
mvn clean test -f karate-fraud/pom.xml

# By tag (e.g., TC-005)
mvn clean test -f karate-fraud/pom.xml -Dkarate.options="--tags @TC-005"

# Specific feature
mvn clean test -f karate-fraud/pom.xml -Dtest=FraudApiTest
```

## Reports

Reports generated in `target/surefire-reports/`.
HTML summary available in `target/site/surefire-report.html`.
