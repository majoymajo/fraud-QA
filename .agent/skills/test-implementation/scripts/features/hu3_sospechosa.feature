@HU3 @sospechosa
Feature: HU3 – Marcar transacción sospechosa
  Como sistema antifraude quiero marcar transacciones como sospechosas
  cuando cumplan los criterios (ej. monto alto o ubicación inusual)
  para permitir su revisión.

  Background:
    Given el umbral de monto es 15000
    And la ubicación habitual es "Colombia"

  @TC-019 @critico
  Scenario: Monto alto + ubicación usual → Sospechosa
    Given una transacción con monto 20000 y país "Colombia"
    When la transacción es evaluada
    Then la transacción es marcada como sospechosa

  @TC-020 @critico
  Scenario: Monto normal + ubicación inusual → Sospechosa
    Given una transacción con monto 5000 y país "Rusia"
    When la transacción es evaluada
    Then la transacción es marcada como sospechosa

  @TC-021 @critico
  Scenario: Monto alto + ubicación inusual → Sospechosa
    Given una transacción con monto 30000 y país "Islas Caimán"
    When la transacción es evaluada
    Then la transacción es marcada como sospechosa

  @TC-022 @critico
  Scenario: Monto normal + ubicación usual → No Sospechosa
    Given una transacción con monto 2000 y país "Colombia"
    When la transacción es evaluada
    Then la transacción no es marcada como sospechosa
