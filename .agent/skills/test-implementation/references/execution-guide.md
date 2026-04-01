# Execution Guide

Commands to run tests by framework and scope.

## SerenityBDD + Cucumber

```bash
# Run all SerenityBDD tests
mvn clean verify -f serenity-fraud/pom.xml

# Run tests by tag (e.g., only HU1 tests)
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@HU1"

# Run specific test case (e.g., TC-001)
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@TC-001"

# Run critical tests only
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@critico"
```

## Karate

```bash
# Run all Karate tests
mvn clean test -f karate-fraud/pom.xml

# Run tests by tag
mvn clean test -f karate-fraud/pom.xml -Dkarate.options="--tags @TC-005"

# Run specific feature
mvn clean test -f karate-fraud/pom.xml -Dtest=FraudApiTest
```

## k6 Performance Tests

```bash
# Run against local environment
k6 run k6-fraud/fraud_load_test.js

# Run against QA environment
k6 run --env BASE_URL=http://qa.fraud-engine.sofka.co/api/v1 k6-fraud/fraud_load_test.js

# Run with custom VU count
k6 run --vus 100 --duration 5m k6-fraud/fraud_load_test.js
```

## Reporting

After running any suite, update [TEST_CASES.md](../../TEST_CASES.md):
- Change **Estado** from `🔘 Sin ejecutar` → `✅ Pasó` or `❌ Falló`
- Fill **Resultado obtenido** with actual response
- Update **Estado General de Ejecución** table
