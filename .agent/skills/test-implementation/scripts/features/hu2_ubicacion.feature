@HU2 @ubicacion
Feature: HU2 – Evaluar ubicación de la transacción
  Como sistema antifraude quiero detectar la ubicación de cada transacción
  comparándola con la ubicación habitual del usuario
  para identificar si es sospechosa.

  Background:
    Given la ubicación habitual del usuario es "Colombia"

  @TC-010 @critico
  Scenario: Ubicación diferente a la habitual es clasificada como Inusual
    Given usuario "USR-010" con país habitual "Colombia"
    When se realiza una transacción desde "Islas Caimán"
    Then la clasificación retornada es "Inusual"

  @TC-011 @critico
  Scenario: Ubicación igual a la habitual es clasificada como Usual
    Given usuario "USR-011" con país habitual "Colombia"
    When se realiza una transacción desde "Colombia"
    Then la clasificación retornada es "Usual"

  @TC-012 @critico
  Scenario: Ubicación en paraíso fiscal es clasificada como Inusual
    Given usuario "USR-012" con país habitual "Colombia"
    When se realiza una transacción desde "Panamá"
    Then la clasificación retornada es "Inusual"

  @TC-016 @alto
  Scenario: Comparación de ubicación es case-insensitive
    Given usuario "USR-016" con país habitual "colombia"
    When se realiza una transacción desde "Colombia"
    Then la clasificación retornada es "Usual"

  @TC-017 @alto
  Scenario: País diferente no paraíso fiscal es Inusual
    Given usuario "USR-017" con país habitual "Colombia"
    When se realiza una transacción desde "Estados Unidos"
    Then la clasificación retornada es "Inusual"
