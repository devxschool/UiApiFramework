import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber","json:target/cucumber.json"},
        features = {"classpath:features/restapi","classpath:features/expedia"},
        glue = {"step_defs"},
        tags = "@jira_api",
        dryRun = false)
public class CucumberRunner {
}
