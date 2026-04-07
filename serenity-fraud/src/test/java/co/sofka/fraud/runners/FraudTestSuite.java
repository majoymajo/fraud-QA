package co.sofka.fraud.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "co.sofka.fraud.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
public class FraudTestSuite {
}
