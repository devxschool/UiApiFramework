import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/expedia"},
        glue = {"step_defs"},
        //tags = "@expedia",
        dryRun = true)
public class CucumberRunner {
}
