@HU2 @negative @karate@HU1 @negative @karate



































    And match response.clasificacion == '#present'    Then status 200    When method POST    And request { usuario_id: 'USR-018', pais_habitual: 'Colombia', pais_transaccion: 'Colombia', ip: null }    Given path '/transacciones/evaluar-ubicacion'  Scenario: IP null - manejo controlado  @TC-018 @medio    Then status 400    When method POST    And request { usuario_id: 'USR-015', pais_habitual: 'Colombia', pais_transaccion: 'PaísInexistente', ip: '10.0.0.2' }    Given path '/transacciones/evaluar-ubicacion'  Scenario: País inexistente es rechazado  @TC-015 @medio    Then status 400    When method POST    And request { usuario_id: 'USR-014', pais_habitual: 'Colombia', pais_transaccion: '', ip: '10.0.0.1' }    Given path '/transacciones/evaluar-ubicacion'  Scenario: País vacío es rechazado con 400 Bad Request  @TC-014 @alto    And match response.clasificacion == '#present'    Then status 200    When method POST    And request { usuario_id: 'USR-013', pais_transaccion: 'México', ip: '189.203.15.42' }    Given path '/transacciones/evaluar-ubicacion'  Scenario: Sin ubicación registrada - manejo controlado  @TC-013 @critico    * url baseUrl  Background:Feature: HU2 – Casos negativos de evaluación de ubicaciónFeature: HU1 – Casos negativos de evaluación de monto

  Background:
    * url baseUrl

  @TC-005 @critico
  Scenario: Monto negativo es rechazado como dato inválido
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1005', monto: -500, fecha: '2026-03-24', usuario_id: 'USR-005' }
    When method POST
    Then status 400
    And match response.mensaje contains 'inválido'

  @TC-006 @critico
  Scenario: Monto null es rechazado con 400 Bad Request
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1006', monto: null, fecha: '2026-03-24', usuario_id: 'USR-006' }
    When method POST
    Then status 400
    And match response.mensaje == '#present'

  @TC-007 @alto
  Scenario: Monto de tipo texto es rechazado con 400 Bad Request
    Given path '/transacciones/evaluar-monto'
    And request { transaccion_id: 'TXN-1007', monto: 'abc', fecha: '2026-03-24', usuario_id: 'USR-007' }
    When method POST
    Then status 400
    And match response.codigo == 400
