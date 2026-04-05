package test.java.co.sofka.fraud;

import com.intuit.karate.junit5.Karate;

public class FraudApiTest {

	@Karate.Test
	Karate runAll() {
		return Karate.run("classpath:karate")
			.tags("@karate")
			.relativeTo(getClass());
	}
}
