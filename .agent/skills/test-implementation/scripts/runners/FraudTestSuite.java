package co.sofka.fraud.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "co.sofka.fraud.steps")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = Constants.PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class FraudTestSuite {
    // JUnit 5 Cucumber test runner for SerenityBDD
    // Executes all features in src/test/resources/features/
    // Reports generated to target/site/serenity/index.html
}
