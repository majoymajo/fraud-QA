@HU1 @negative @karate
Feature: HU1 – Validaciones del endpoint de evaluación (casos negativos)

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  @TC-005 @critico
  Scenario: Monto negativo se rechaza con validación controlada
    Given path 'fraud', 'evaluate'
    And request { amount: -500, transactionCountry: 'CO', userCountry: 'CO', ip: '192.168.1.1' }
    When method post
    Then status 400
    And match response.error == 'Validation failed'
    And match response.fields.amount contains 'greater than or equal to 0'

  @TC-006 @critico
  Scenario: Monto null se rechaza con 400 Bad Request (validación)
    Given path 'fraud', 'evaluate'
    And request { amount: null, transactionCountry: 'CO', userCountry: 'CO', ip: '192.168.1.1' }
    When method post
    Then status 400
    And match response.error == 'Validation failed'
    And match response.fields.amount == 'amount is required'

  @TC-007 @alto
  Scenario: Monto de tipo texto se rechaza sin crash
    Given path 'fraud', 'evaluate'
    And request { amount: 'abc', transactionCountry: 'CO', userCountry: 'CO', ip: '192.168.1.1' }
    When method post
    Then status 400
    And match response.error == '#string'
