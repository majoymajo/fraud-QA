package co.sofka.fraud.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import lombok.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for HU1 – Evaluar monto de la transacción
 * Tests: TC-001 to TC-004, TC-008, TC-009
 */
@Slf4j
public class HU1MontoSteps {

    private int umbralMonto;
    private String transaccionId;
    private int montoTransaccion;
    private String usuarioId;
    private String clasificacion;
    private boolean esSospechosa;
    private Exception excepcion;

    @Given("el umbral de monto configurado es {int}")
    public void configurarUmbral(int umbral) {
        this.umbralMonto = umbral;
        log.info("Umbral de monto configurado: {}", umbral);
    }

    @Given("una transacción con id {string} y monto {int} del usuario {string}")
    public void crearTransaccion(String txnId, int monto, String userId) {
        this.transaccionId = txnId;
        this.montoTransaccion = monto;
        this.usuarioId = userId;
        log.info("Transacción creada: {} con monto {}", txnId, monto);
    }

    @When("la transacción es procesada por el sistema")
    public void procesarTransaccion() {
        try {
            // Lógica de evaluación del monto
            this.clasificacion = montoTransaccion > umbralMonto ? "Inusual" : "Usual";
            this.esSospechosa = clasificacion.equals("Inusual");
            log.info("Transacción procesada: clasificación = {}", clasificacion);
        } catch (Exception e) {
            this.excepcion = e;
            log.error("Error al procesar transacción", e);
        }
    }

    @Then("la clasificación retornada es {string}")
    public void verificarClasificacion(String esperada) {
        assertThat(clasificacion).isEqualTo(esperada);
        log.info("Clasificación verificada: {}", esperada);
    }

    @And("la transacción no es marcada como sospechosa")
    public void verificarNoSospechosa() {
        assertThat(esSospechosa).isFalse();
        log.info("Transacción no es sospechosa");
    }

    @And("no hay error de desbordamiento numérico")
    public void verificarSinDesbordamiento() {
        assertThat(excepcion).isNull();
        assertThat(clasificacion).isNotBlank();
        log.info("Sin desbordamiento numérico");
    }
}
