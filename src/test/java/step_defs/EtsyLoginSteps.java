package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigReader;

import java.util.concurrent.TimeUnit;

public class EtsyLoginSteps {
    WebDriver driver;
    @Given("^user is on th etsy home page$")
    public void user_is_on_th_etsy_home_page() throws Throwable {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("baseUrl"));

    }

    @When("^user clicks on sign in button$")
    public void user_clicks_on_sign_in_button() throws Throwable {
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
    }

    @When("^user inputs his username$")
    public void user_inputs_his_username() throws Throwable {

        driver.findElement(By.xpath("//input[@id='join_neu_email_field']")).sendKeys(ConfigReader.getProperty("username"));
    }

    @When("^user inputs his password$")
    public void user_inputs_his_password() throws Throwable {
        driver.findElement(By.xpath("//input[@id='join_neu_password_field']")).sendKeys(ConfigReader.getProperty("password"));
    }

    @When("^user clicks on sign it button$")
    public void user_clicks_on_sign_it_button() throws Throwable {
        driver.findElement(By.name("submit_attempt")).click();
    }

    @Then("^verify the error message user account has been deactivated is displayed$")
    public void verify_the_error_message_user_account_has_been_deactivated_is_displayed() throws Throwable {
        WebElement error = driver.findElement(By.xpath("//div[@class='alert-message']"));

        Assert.assertEquals(error.getText().trim(),"Account has been deactivated." );
        driver.close();
    }


}
