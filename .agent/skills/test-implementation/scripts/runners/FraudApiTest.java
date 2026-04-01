package co.sofka.fraud;

import com.intuit.karate.junit5.Karate;

public class FraudApiTest {
    
    @Karate.Test
    Karate testFraudApi() {
        return Karate.run("classpath:karate")
                     .tags("@karate")
                     .relativeTo(getClass());
    }

    @Karate.Test
    Karate testHU1Negative() {
        return Karate.run("classpath:karate/hu1_negative.feature")
                     .relativeTo(getClass());
    }

    @Karate.Test
    Karate testHU2Negative() {
        return Karate.run("classpath:karate/hu2_ubicacion_negative.feature")
                     .relativeTo(getClass());
    }

    @Karate.Test
    Karate testHU4RiskLevels() {
        return Karate.run("classpath:karate/hu4_riesgo.feature")
                     .relativeTo(getClass());
    }
}
