@HU1 @monto
Feature: HU1 – Evaluar monto de la transacción
  Como sistema antifraude quiero revisar el monto de cada transacción
  para identificar valores inusualmente altos.

  Background:
    Given el umbral de monto configurado es 15000

  @TC-001 @critico
  Scenario: Monto mayor al umbral es clasificado como Inusual
    Given una transacción con id "TXN-1001" y monto 20000 del usuario "USR-001"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Inusual"

  @TC-002 @critico
  Scenario: Monto menor al umbral es clasificado como Usual
    Given una transacción con id "TXN-1002" y monto 5000 del usuario "USR-002"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Usual"
    And la transacción no es marcada como sospechosa

  @TC-003 @critico
  Scenario: Monto exactamente igual al umbral es clasificado como Usual
    Given una transacción con id "TXN-1003" y monto 15000 del usuario "USR-003"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Usual"

  @TC-004 @alto
  Scenario: Monto apenas superior al umbral es clasificado como Inusual
    Given una transacción con id "TXN-1004" y monto 15001 del usuario "USR-004"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Inusual"

  @TC-008 @medio
  Scenario: Monto cero es clasificado como Usual
    Given una transacción con id "TXN-1008" y monto 0 del usuario "USR-008"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Usual"

  @TC-009 @medio
  Scenario: Monto extremadamente alto sin desbordamiento
    Given una transacción con id "TXN-1009" y monto 999999999 del usuario "USR-009"
    When la transacción es procesada por el sistema
    Then la clasificación retornada es "Inusual"
    And no hay error de desbordamiento numérico
