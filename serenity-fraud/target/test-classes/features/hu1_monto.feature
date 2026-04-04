# language: es
@HU1
Feature: HU1 – Evaluar monto de la transacción
  Como sistema antifraude
  Quiero revisar el monto de cada transacción
  Para identificar valores inusualmente altos

  Background:
    Given el servicio de evaluación de fraude está disponible

  @TC-001 @critico
  Scenario: TC-001 – Monto mayor al umbral es clasificado como Inusual
    Given una transacción con monto 20000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true

  @TC-002 @critico
  Scenario: TC-002 – Monto menor al umbral es clasificado como Usual
    Given una transacción con monto 5000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Usual"
    And el campo suspicious es false

  @TC-003 @critico
  Scenario: TC-003 – Monto exactamente igual al umbral es clasificado como Usual
    Given una transacción con monto 15000 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Usual"
    And el campo suspicious es false

  @TC-004 @alto
  Scenario: TC-004 – Monto que excede el umbral por una unidad es clasificado como Inusual
    Given una transacción con monto 15001 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true

  @TC-008 @medio
  Scenario: TC-008 – Monto igual a cero es clasificado como Usual
    Given una transacción con monto 0 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Usual"
    And el campo suspicious es false

  @TC-009 @medio
  Scenario: TC-009 – Monto extremadamente alto es clasificado como Inusual sin desbordamiento
    Given una transacción con monto 999999999 desde el país "CO" para el usuario del país "CO"
    When la transacción es procesada por el sistema
    Then el sistema clasifica la transacción como "Inusual"
    And el campo suspicious es true
