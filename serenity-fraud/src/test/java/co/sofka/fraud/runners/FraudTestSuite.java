package co.sofka.fraud.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Main JUnit 5 Suite runner for all Serenity + Cucumber acceptance tests.
 *
 * Run all:     mvn clean verify -f serenity-fraud/pom.xml
 * By HU tag:  mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@HU1"
 * By TC tag:  mvn clean verify -f serenity-fraud/pom.xml -Dcucumber.filter.tags="@TC-001"
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.sofka.fraud.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.cucumber.core.plugin.SerenityReporterParallelPlugin")
public class FraudTestSuite {
    // Runner class — no body required for JUnit Platform Suite
}
