package co.sofka.fraud.steps;

import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;

import java.math.BigDecimal;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;
public class FraudCommonSteps {

    private static final String SERVICE_BASE_URL = System.getProperty("fraud.service.base.url", "http://localhost:8080");
    private static final String FRAUD_API_PREFIX = "/api/v1/fraud";
    private static final String HEALTH_URL = SERVICE_BASE_URL + FRAUD_API_PREFIX + "/health";
    private static final String EVALUATE_URL = SERVICE_BASE_URL + FRAUD_API_PREFIX + "/evaluate";
    private static final String CLASSIFICATION_INUSUAL = "Inusual";
    private static final String CLASSIFICATION_USUAL = "Usual";
    private static final String RISK_LEVEL_LOW = "LOW";

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
            .when().get(HEALTH_URL)
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
        String body = buildEvaluateRequestBody();

        SerenityRest
            .given()
            .contentType("application/json")
            .body(body)
            .when().post(EVALUATE_URL);
    }

    @Entonces("el sistema clasifica la transacción como {string}")
    public void elSistemaClasificaLaTransaccionComo(String clasificacion) {
        var response = lastResponse();
        assertThat(response.statusCode()).isEqualTo(200);

        var jsonPath = response.jsonPath();
        boolean suspicious = jsonPath.getBoolean("suspicious");
        String riskLevel = jsonPath.getString("riskLevel");

        if (CLASSIFICATION_INUSUAL.equalsIgnoreCase(clasificacion)) {
            assertInusualClassification(suspicious, riskLevel);
            return;
        }
        if (CLASSIFICATION_USUAL.equalsIgnoreCase(clasificacion)) {
            assertUsualClassification(suspicious, riskLevel);
            return;
        }

        throw new IllegalArgumentException("Clasificación no soportada: " + clasificacion);
    }

    private static void assertInusualClassification(boolean suspicious, String riskLevel) {
        assertThat(suspicious || !RISK_LEVEL_LOW.equalsIgnoreCase(riskLevel))
            .as("Se esperaba clasificación %s pero suspicious=%s, riskLevel=%s", CLASSIFICATION_INUSUAL, suspicious, riskLevel)
            .isTrue();
    }

    private static void assertUsualClassification(boolean suspicious, String riskLevel) {
        assertThat(suspicious)
            .as("Se esperaba clasificación %s pero suspicious=%s", CLASSIFICATION_USUAL, suspicious)
            .isFalse();
        assertThat(riskLevel)
            .as("Se esperaba riskLevel=%s pero fue %s", RISK_LEVEL_LOW, riskLevel)
            .isEqualToIgnoringCase(RISK_LEVEL_LOW);
    }

    private String buildEvaluateRequestBody() {
        return String.format(
            "{\"amount\": %s, \"transactionCountry\": \"%s\", \"userCountry\": \"%s\"}",
            amount.toPlainString(), transactionCountry, userCountry
        );
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
