package co.sofka.fraud.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import lombok.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for HU3 – Marcar transacción sospechosa
 * Tests: TC-019 to TC-022
 */
@Slf4j
public class HU3SospechosaSteps {

    private int umbralMonto;
    private String ubicacionHabitual;
    private int monto;
    private String pais;
    private boolean esSospechosa;

    @Given("el umbral de monto es {int}")
    public void configurarUmbral(int umbral) {
        this.umbralMonto = umbral;
        log.info("Umbral configurado: {}", umbral);
    }

    @And("la ubicación habitual es {string}")
    public void configurarUbicacionHabitual(String ubicacion) {
        this.ubicacionHabitual = ubicacion;
        log.info("Ubicación habitual: {}", ubicacion);
    }

    @Given("una transacción con monto {int} y país {string}")
    public void crearTransaccion(int monto, String pais) {
        this.monto = monto;
        this.pais = pais;
        log.info("Transacción: monto={}, país={}", monto, pais);
    }

    @When("la transacción es evaluada")
    public void evaluarTransaccion() {
        boolean montoInusual = monto > umbralMonto;
        boolean ubicacionInusual = !pais.equalsIgnoreCase(ubicacionHabitual);
        
        // Regla: Sospechosa si monto inusual O ubicación inusual
        this.esSospechosa = montoInusual || ubicacionInusual;
        
        log.info("Evaluación: montoInusual={}, ubicacionInusual={}, sospechosa={}", 
                montoInusual, ubicacionInusual, esSospechosa);
    }

    @Then("la transacción es marcada como sospechosa")
    public void verificarSospechosa() {
        assertThat(esSospechosa).isTrue();
        log.info("Transacción marcada como sospechosa");
    }

    @Then("la transacción no es marcada como sospechosa")
    public void verificarNoSospechosa() {
        assertThat(esSospechosa).isFalse();
        log.info("Transacción no marcada como sospechosa");
    }
}
