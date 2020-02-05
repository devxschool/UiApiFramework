package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.Driver;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utilities.ConfigReader;

public class ExpediaNewWindowVerificationSteps {
    WebDriver driver;
    BasePage expediaHome = new BasePage();
    @Given("^the user is on the main expedia page$")
    public void the_user_is_on_the_main_expedia_page() throws Throwable {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @When("^the user clicks on 'list your property button'$")
    public void the_user_clicks_on_list_your_property_button() throws Throwable {
        expediaHome.ListYourPropertyButton.click();
    }

    @Then("^the new tab should open$")
    public void the_new_tab_should_open() throws Throwable {

    }

    @Then("^how much could you earn text is be displayed$")
    public void how_much_could_you_earn_text_is_be_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

}
