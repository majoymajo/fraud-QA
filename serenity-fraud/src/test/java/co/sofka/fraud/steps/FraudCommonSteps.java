package co.sofka.fraud.steps;

import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;

import java.math.BigDecimal;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Shared step definitions used across HU1, HU2, and HU3 feature files.
 *
 * API Contract (from fraud-service source):
 *   POST /api/v1/fraud/evaluate
 *   Body: { "amount": <BigDecimal>, "transactionCountry": "<ISO-2/3>", "userCountry": "<ISO-2/3>", "ip": null }
 *   Response: { "suspicious": <boolean>, "riskLevel": "LOW|MEDIUM|HIGH", "reasons": [...] }
 */
public class FraudCommonSteps {

    private static final String BASE_URL = System.getProperty(
        "fraud.service.base.url", "http://localhost:8080"
    );
    private static final String EVALUATE_ENDPOINT = BASE_URL + "/api/v1/fraud/evaluate";

    private BigDecimal amount;
    private String transactionCountry;
    private String userCountry;

    @Before
    public void setupRestAssured() {
        SerenityRest.setDefaultBasePath("");
    }

    @Dado("el servicio de evaluación de fraude está disponible")
    public void elServicioEstaDisponible() {
        SerenityRest
            .given()
            .when().get(BASE_URL + "/api/v1/fraud/health")
            .then().statusCode(200);
    }

    @Dado("una transacción con monto {int} desde el país {string} para el usuario del país {string}")
    public void unaTransaccionConMontoDesdeElPaisParaElUsuarioDelPais(
            int monto, String paisTransaccion, String paisUsuario) {
        this.amount = BigDecimal.valueOf(monto);
        this.transactionCountry = paisTransaccion;
        this.userCountry = paisUsuario;
    }

    @Cuando("la transacción es procesada por el sistema")
    public void laTransaccionEsProcesadaPorElSistema() {
        String body = String.format(
            "{\"amount\": %s, \"transactionCountry\": \"%s\", \"userCountry\": \"%s\"}",
            amount.toPlainString(), transactionCountry, userCountry
        );

        SerenityRest
            .given()
            .contentType("application/json")
            .body(body)
            .when().post(EVALUATE_ENDPOINT);
    }

    @Entonces("el sistema clasifica la transacción como {string}")
    public void elSistemaClasificaLaTransaccionComo(String clasificacion) {
        assertThat(lastResponse().statusCode()).isEqualTo(200);

        boolean suspicious = lastResponse().jsonPath().getBoolean("suspicious");
        String riskLevel = lastResponse().jsonPath().getString("riskLevel");

        if ("Inusual".equalsIgnoreCase(clasificacion)) {
            // Inusual = suspicious or riskLevel != LOW
            assertThat(suspicious || !"LOW".equalsIgnoreCase(riskLevel))
                .as("Se esperaba clasificación Inusual pero suspicious=%s, riskLevel=%s", suspicious, riskLevel)
                .isTrue();
        } else if ("Usual".equalsIgnoreCase(clasificacion)) {
            assertThat(suspicious)
                .as("Se esperaba clasificación Usual (suspicious=false) pero fue suspicious=%s", suspicious)
                .isFalse();
            assertThat(riskLevel)
                .as("Se esperaba riskLevel=LOW para Usual pero fue %s", riskLevel)
                .isEqualToIgnoringCase("LOW");
        }
    }

    @Entonces("el campo suspicious es true")
    public void elCampoSuspiciousEsTrue() {
        assertThat(lastResponse().jsonPath().getBoolean("suspicious"))
            .as("Se esperaba suspicious=true")
            .isTrue();
    }

    @Entonces("el campo suspicious es false")
    public void elCampoSuspiciousEsFalse() {
        assertThat(lastResponse().jsonPath().getBoolean("suspicious"))
            .as("Se esperaba suspicious=false")
            .isFalse();
    }

    @Entonces("el nivel de riesgo es {string}")
    public void elNivelDeRiesgoEs(String expectedRiskLevel) {
        String actualRiskLevel = lastResponse().jsonPath().getString("riskLevel");
        assertThat(actualRiskLevel)
            .as("Se esperaba riskLevel=%s pero fue %s", expectedRiskLevel, actualRiskLevel)
            .isEqualToIgnoringCase(expectedRiskLevel);
    }
}
