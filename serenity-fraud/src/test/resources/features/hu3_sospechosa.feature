@HU3
Feature: HU3 – Marcar transacción sospechosa
  Como sistema antifraude
  Quiero marcar transacciones como sospechosas cuando cumplan los criterios
  Para permitir su revisión

  Background:
    Given el servicio de evaluación de fraude está disponible

  @TC-019 @critico
  Scenario: TC-019 – Monto alto con ubicación usual activa la alerta de sospecha
    Given una transacción con monto 20000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el campo suspicious es true
    And el nivel de riesgo es "MEDIUM"

  @TC-020 @critico
  Scenario: TC-020 – Monto normal con ubicación inusual activa la alerta de sospecha
    Given una transacción con monto 5000 desde el país "RU" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el campo suspicious es true
    And el nivel de riesgo es "MEDIUM"

  @TC-021 @critico
  Scenario: TC-021 – Monto alto con ubicación inusual activa la alerta de sospecha
    Given una transacción con monto 30000 desde el país "KY" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el campo suspicious es true
    And el nivel de riesgo es "HIGH"

  @TC-022 @critico
  Scenario: TC-022 – Monto normal con ubicación usual no genera alerta de sospecha
    Given una transacción con monto 2000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el campo suspicious es false
    And el nivel de riesgo es "LOW"
