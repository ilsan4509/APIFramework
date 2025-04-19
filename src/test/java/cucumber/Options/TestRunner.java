package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)  // Cucumber test with JUnit
@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinations"})  // Specify the location of feature files and step definitions
public class TestRunner {
	

}
