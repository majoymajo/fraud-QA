# Canonical Folder Structure

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
│           │       └── tasks/         # Serenity Tasks (Screenplay pattern)
│           └── resources/
│               ├── features/
│               │   ├── hu1_monto.feature
│               │   ├── hu2_ubicacion.feature
│               │   ├── hu3_sospechosa.feature
│               │   └── hu4_riesgo.feature
│               └── serenity.conf
│
├── karate-fraud/                      # Karate API tests
│   ├── pom.xml
│   └── src/
│       └── test/
│           ├── java/
│           │   └── co/sofka/fraud/
│           │       └── FraudApiTest.java
│           └── resources/
│               └── karate/
│                   ├── karate-config.js
│                   ├── hu1_negative.feature
│                   ├── hu2_negative.feature
│                   └── hu4_risk_levels.feature
│
├── k6-fraud/                          # k6 performance tests
│   ├── fraud_load_test.js
│   └── thresholds.js
│
└── .agent/skills/fraud_detection_test_implementation/
    ├── SKILL.md                        # Core instructions (lean)
    ├── scripts/                        # Ready-to-use code templates
    │   ├── features/                   # Gherkin feature files
    │   ├── steps/                      # Java step definitions
    │   ├── runners/                    # Test runners
    │   ├── config/                     # Config files (serenity.conf, karate-config.js)
    │   └── k6/                         # k6 test scripts
    ├── references/                     # Domain knowledge & guides
    │   ├── technology-matrix.md
    │   ├── domain-rules.md
    │   ├── execution-guide.md
    │   └── folder-structure.md
    ├── assets/                         # Templates & data files
    │   ├── pom-serenity-template.xml
    │   └── pom-karate-template.xml
    └── examples/                       # Sample inputs/outputs
        ├── request-payloads.json
        └── expected-responses.json
```
