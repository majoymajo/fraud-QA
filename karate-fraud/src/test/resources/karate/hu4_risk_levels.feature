@HU4 @risk @karate
Feature: HU4 – Clasificación de nivel de riesgo (vía /fraud/evaluate)

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  @TC-023 @critico
  Scenario: Doble riesgo (monto alto + ubicación inusual) → HIGH
    Given path 'fraud', 'evaluate'
    And request { amount: 25000, transactionCountry: 'US', userCountry: 'CO' }
    When method post
    Then status 200
    And match response.suspicious == true
    And match response.riskLevel == 'HIGH'
    And match response.reasons contains ['HIGH_AMOUNT', 'UNUSUAL_LOCATION']

  @TC-024 @alto
  Scenario: Solo monto alto → MEDIUM
    Given path 'fraud', 'evaluate'
    And request { amount: 20000, transactionCountry: 'CO', userCountry: 'CO' }
    When method post
    Then status 200
    And match response.suspicious == true
    And match response.riskLevel == 'MEDIUM'
    And match response.reasons contains ['HIGH_AMOUNT']
    And match response.reasons !contains ['UNUSUAL_LOCATION']

  @TC-025 @alto
  Scenario: Solo ubicación inusual → MEDIUM
    Given path 'fraud', 'evaluate'
    And request { amount: 1000, transactionCountry: 'NG', userCountry: 'CO' }
    When method post
    Then status 200
    And match response.suspicious == true
    And match response.riskLevel == 'MEDIUM'
    And match response.reasons contains ['UNUSUAL_LOCATION']
    And match response.reasons !contains ['HIGH_AMOUNT']

  @TC-026 @medio
  Scenario: Sin reglas de riesgo activadas → LOW
    Given path 'fraud', 'evaluate'
    And request { amount: 500, transactionCountry: 'CO', userCountry: 'CO' }
    When method post
    Then status 200
    And match response == { suspicious: false, riskLevel: 'LOW', reasons: [] }
