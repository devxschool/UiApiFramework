import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/restapi","classpath:features/expedia"},
        glue = {"step_defs"},
        tags = "@api_post",
        dryRun = false)
public class CucumberRunner {
}
