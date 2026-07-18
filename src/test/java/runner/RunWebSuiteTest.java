package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/spec/features/feature_fils"},
		plugin = {"pretty","html:target/cucumber-report.html"} ,
		tags = ("@tasks"),
		glue = {"Step_definitions", "Pages", "utils"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE
		)

public class RunWebSuiteTest {

	

	

}
