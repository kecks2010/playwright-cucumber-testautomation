package de.mirko_werner.playwright_cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.mirko_werner.playwright_cucumber")
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PARALLEL_CONFIG_FIXED_MAX_POOL_SIZE_PROPERTY_NAME, value = "4")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value="junit:target/cucumber-reports/Cucumber.xml, " +
                "json:target/cucumber-reports/Cucumber.json, " +
                "html:target/cucumber-reports/Cucumber.html, " +
                "timeline:target/cucumber-reports/CucumberTimeline")
public class RunCucumberTest {
}