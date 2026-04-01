# SerenityBDD + Cucumber – Setup & Examples

## Overview

SerenityBDD tests validate **acceptance criteria** using Gherkin scenarios. Used for TCs: **TC-001 to TC-004, TC-008 to TC-012, TC-016 to TC-022**.

## Step-by-Step

### 1. Write Feature Files

Create `.feature` files in `src/test/resources/features/`.
Use exact Gherkin text from TEST_CASES.md `Escenario Gherkin` column.

**Example file location:** `scripts/features/hu1_monto.feature`

### 2. Write Step Definitions

Create Java step classes in `src/test/java/co/sofka/fraud/steps/`.
Use `@Given`, `@When`, `@Then` annotations matching feature file steps.

**Example file location:** `scripts/steps/HU1MontoSteps.java`

### 3. Create Runner Class

Create a JUnit 5 test runner in `src/test/java/co/sofka/fraud/runners/`.

**Example file location:** `scripts/runners/FraudTestSuite.java`

### 4. Configure serenity.conf

Place in `src/test/resources/serenity.conf`.

**Example file location:** `scripts/config/serenity.conf`

## File References

- **Feature files:** See `scripts/features/`
- **Step definitions:** See `scripts/steps/`
- **Runner:** See `scripts/runners/FraudTestSuite.java`
- **Config:** See `scripts/config/serenity.conf`

## Execution

```bash
# All tests
mvn clean verify -f serenity-fraud/pom.xml

# By tag
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@TC-001"

# by HU
mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@HU1"
```

## Reports

Reports generated in `target/site/serenity/index.html` after execution.
