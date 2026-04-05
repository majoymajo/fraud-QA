@HU2 @negative @karate
Feature: HU2 – Validaciones y tolerancia a datos faltantes (API)

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  @TC-013 @critico
  Scenario: Sin ubicación habitual (userCountry faltante) es manejado sin crash
    Given path 'fraud', 'evaluate'
    And request { amount: 1000, transactionCountry: 'MX', ip: '189.203.15.42' }
    When method post
    Then status 400
    And match response.error == 'Validation failed'
    And match response.fields.userCountry == 'userCountry is required'

  @TC-014 @alto
  Scenario: País de transacción vacío se rechaza con 400
    Given path 'fraud', 'evaluate'
    And request { amount: 1000, transactionCountry: '', userCountry: 'CO', ip: '10.0.0.1' }
    When method post
    Then status 400
    And match response.error == 'Validation failed'
    And match response.fields.transactionCountry == 'transactionCountry is required'

  @TC-015 @medio
  Scenario: País de transacción inválido (longitud fuera de rango) se rechaza
    Given path 'fraud', 'evaluate'
    And request { amount: 1000, transactionCountry: 'PAISINEX', userCountry: 'CO', ip: '10.0.0.2' }
    When method post
    Then status 400
    And match response.error == 'Validation failed'
    And match response.fields.transactionCountry contains 'length between 2 and 3'

  @TC-018 @medio
  Scenario: IP null no rompe la evaluación
    Given path 'fraud', 'evaluate'
    And request { amount: 1000, transactionCountry: 'CO', userCountry: 'CO', ip: null }
    When method post
    Then status 200
    And match response == { suspicious: false, riskLevel: 'LOW', reasons: [] }
