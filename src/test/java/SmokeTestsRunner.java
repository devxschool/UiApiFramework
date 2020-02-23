import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber","json:target/cucumber.json"},
        features = {"classpath:features"},
        glue = {"step_defs"},
        tags = "@a",
        dryRun = false)
public class SmokeTestsRunner {

}
