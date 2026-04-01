@HU4 @riesgo @karate
Feature: HU4 – Clasificación de nivel de riesgo

  Background:
    * url baseUrl

  @TC-023 @critico
  Scenario: Doble riesgo (monto alto + país inusual) → nivel ALTO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 25000, pais: 'Corea del Norte', usuario_id: 'USR-023' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'ALTO'

  @TC-024 @alto
  Scenario: Solo monto alto → nivel MEDIO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 20000, pais: 'Colombia', usuario_id: 'USR-024' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'MEDIO'

  @TC-025 @alto
  Scenario: Solo ubicación inusual → nivel MEDIO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 1000, pais: 'Nigeria', usuario_id: 'USR-025' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'MEDIO'

  @TC-026 @medio
  Scenario: Sin riesgo → nivel BAJO
    Given path '/transacciones/clasificar-riesgo'
    And request { monto: 500, pais: 'Colombia', usuario_id: 'USR-026' }
    When method POST
    Then status 200
    And match response.nivel_riesgo == 'BAJO'
