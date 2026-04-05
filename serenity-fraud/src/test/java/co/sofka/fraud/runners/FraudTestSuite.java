package co.sofka.fraud.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Main JUnit 5 Suite runner for all Serenity + Cucumber acceptance tests.
 *
 * This runner uses the JUnit Platform Suite engine to discover and execute Cucumber features.
 * Standard configuration properties are used as literal strings to ensure stability across 
 * different dependency versions.
 *
 * Run all:    mvn clean verify -f serenity-fraud/pom.xml
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "co.sofka.fraud.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
public class FraudTestSuite {
    // Runner class — no body required for JUnit Platform Suite
}
