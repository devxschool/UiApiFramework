package step_defs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.UserAccount;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EtsyLoginSteps {

    WebDriver driver;

    @Given("^user is on th etsy home page$")
    public void user_is_on_th_etsy_home_page() throws Throwable {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("etsyUrl"));
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
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='sign-in']"))).click();
    }

    @Then("^verify the error message user account has been deactivated is displayed$")
    public void verify_the_error_message_user_account_has_been_deactivated_is_displayed() throws Throwable {
        WebElement error = driver.findElement(By.xpath("//div[@class='alert-message']"));

        Assert.assertEquals(error.getText().trim(), "Account has been deactivated.");
        driver.close();
    }


    @And("^user inputs his username '(.*)'$")
    public void userInputsHisUsername(String username) {
        driver.findElement(By.xpath("//input[@id='join_neu_email_field']"))
                .sendKeys(username);
    }

    @Then("^verify the error message user account has been '(.*)' is displayed$")
    public void verifyTheErrorMessageUserAccountHasBeenStatusIsDisplayed(String status) {
        WebElement error = null;
        if (status.contains("deactivated")) {
            error = driver.findElement(By.xpath("//div[@class='alert-message']"));
        } else if (status.contains("Password")) {
            error = driver.findElement(By.xpath("//div[@id='aria-join_neu_password_field-error']"));
        } else if (status.contains("Email")) {
            error = driver.findElement(By.xpath("//div[@id='aria-join_neu_email_field-error']"));
        } else {
            Assert.fail("invalid error message provided");
        }

        Assert.assertEquals(status, error.getText().trim());
        driver.close();
    }


    @And("^user inputs his password '(.*)'$")
    public void userInputsHisPasswordPassword(String password) {
        driver.findElement(By.xpath("//input[@id='join_neu_password_field']"))
                .sendKeys(password);
    }

    @When("^user adds the following items to the cart$")
    public void userEntersTheFollowingItems(List<String> items) {
        System.out.println(items);

        for (String item : items) {
            // searchTB.sendKeys(item + Keys.ENTER);
            //locate first item
            // add it to the cart
            //searchTB.clear();
        }
    }

    //@Then("^the following items should be present in the cart$")
    //public void theFollowingItemsShouldBePresentInTheCart(List<Map<String, String>> itemsAndCounts) {
        //System.out.println(itemsAndCounts);

    //}
    //
    @Then("^the following items should be present in the cart$")
    public void theFollowingItemsShouldBePresentInTheCart(DataTable itemsAndCounts) {
        List<List<String>> table = itemsAndCounts.raw();

        System.out.println(table.get(1).get(0));

    }

    @When("^User creates accounts with the following info$")
    public void userCreatesAccountsWithTheFollowingInfo(List<UserAccount> accounts) {

        for (UserAccount account : accounts) {
            //firstNameTextBox.sendKeys(account.getFirstName);
            //lastNameTextBox.sendKeys(account.getLastName);

            System.out.println(account.getFirstName());
            System.out.println(account.getPhoneNum());
        }

    }

    @Then("^the following accounts should be created$")
    public void theFollowingAccountsShouldBeCreated(List<UserAccount> accounts) {
        System.out.println(accounts);

        //List<WebElement> actualAccounts = driver.find(xpath(toCreatedAccount)



        for (UserAccount expectedAccount : accounts) {

            for (UserAccount actualAccount : accounts) //change accounts to actualAccounts
            {
                if (accounts.contains(actualAccount)) {

                    if (actualAccount.getFirstName().equalsIgnoreCase(expectedAccount.getFirstName())) {
                        Assert.assertEquals(expectedAccount.getPhoneNum(), actualAccount.getPhoneNum());
                        Assert.assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
                        Assert.assertEquals(expectedAccount.getLastName(), actualAccount.getLastName());
                    }
                } else {
                    Assert.fail("Account was not created");
                }
            }
        }
    }
}
