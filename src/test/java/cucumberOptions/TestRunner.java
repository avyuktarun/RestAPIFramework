package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features= "src/main/java/features"	
	,glue="stepDefinitions"	
	//,tags= {"@DeletePlace"}
	,plugin = {"json:target/jsonReports/Cucumber-Report.json" }
	,monochrome = true
	,strict=true
		)

public class TestRunner {
	
}
