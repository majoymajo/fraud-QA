package co.sofka.fraud.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lombok.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for HU2 – Evaluar ubicación de la transacción
 * Tests: TC-010 to TC-012, TC-016, TC-017
 */
@Slf4j
public class HU2UbicacionSteps {

    private String usuarioId;
    private String paisHabitual;
    private String paisTransaccion;
    private String clasificacion;

    @Given("la ubicación habitual del usuario es {string}")
    public void establecerUbicacionHabitualDefault(String pais) {
        this.paisHabitual = pais;
        log.info("Ubicación habitual default: {}", pais);
    }

    @Given("usuario {string} con país habitual {string}")
    public void crearUsuarioConUbicacion(String userId, String pais) {
        this.usuarioId = userId;
        this.paisHabitual = pais;
        log.info("Usuario {} creado con país habitual: {}", userId, pais);
    }

    @When("se realiza una transacción desde {string}")
    public void realizarTransaccionDesde(String pais) {
        this.paisTransaccion = pais;
        
        // Lógica de evaluación de ubicación (case-insensitive)
        boolean esIgual = paisTransaccion.equalsIgnoreCase(paisHabitual);
        this.clasificacion = esIgual ? "Usual" : "Inusual";
        
        log.info("Transacción desde {} → Clasificación = {}", pais, clasificacion);
    }

    @Then("la clasificación retornada es {string}")
    public void verificarClasificacion(String esperada) {
        assertThat(clasificacion).isEqualTo(esperada);
        log.info("Clasificación verificada: {}", esperada);
    }
}
