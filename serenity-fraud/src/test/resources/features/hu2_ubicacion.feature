@HU2
Feature: HU2 – Evaluar ubicación de la transacción
  Como sistema antifraude
  Quiero detectar la ubicación de cada transacción comparándola con la habitual del usuario
  Para identificar si es sospechosa

  Background:
    Given el servicio de evaluación de fraude está disponible

  @TC-010 @critico
  Scenario: TC-010 – Transacción desde país diferente es clasificada como Inusual
    Given una transacción con monto 1000 desde el país "KY" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true

  @TC-011 @critico
  Scenario: TC-011 – Transacción desde el mismo país es clasificada como Usual
    Given una transacción con monto 1000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Usual"
    And el campo suspicious es false

  @TC-012 @critico
  Scenario: TC-012 – Transacción desde paraíso fiscal es clasificada como Inusual
    Given una transacción con monto 1000 desde el país "PA" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true

  @TC-016 @alto
  Scenario: TC-016 – La comparación de países es case-insensitive
    Given una transacción con monto 1000 desde el país "co" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Usual"
    And el campo suspicious es false

  @TC-017 @alto
  Scenario: TC-017 – Transacción desde país diferente pero no paraíso fiscal es Inusual
    Given una transacción con monto 1000 desde el país "US" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true
