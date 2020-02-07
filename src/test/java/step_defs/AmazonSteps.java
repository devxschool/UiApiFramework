package step_defs;
import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
public class AmazonSteps {
    WebDriver driver;
    WebDriverWait wait;
    @Given("^User is on the main page$")
    public void user_is_on_the_main_page() throws Throwable {
        System.out.println("About Run Given Step");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,4);
        driver.get("https://www.amazon.com/");
        System.out.println("Given Step finished");
    }
    @When("^User enters '(.*)' in the search box$")
    public void user_enters_Iphone_in_the_search_box(String item) throws Throwable {
        System.out.println("When Step started");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInput.sendKeys(item + Keys.ENTER);
        System.out.println("When Step finished");
    }
    @Then("^All results should contain '(.*)' in the title$")
    public void all_results_should_contain_Iphone_in_the_title(String item) throws Throwable {
        System.out.println("Then Step started");
        //Assert.assertEquals("iphone", driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).getAttribute("value"));
        WebElement search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.textToBePresentInElementValue(search, item));
        System.out.println(driver.findElement(By.xpath("//span[contains(text(), 'results')]")).getText());
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        List<WebElement> iphoneResults = driver.findElements(By.xpath("//a/span[contains(text(),'iPhone')]"));
        for(WebElement link:iphoneResults){
            System.out.println(link.getText());
        }
        iphoneResults.get(4).click();
        driver.close();
        System.out.println("Then Step finished");
    }
    @When("^User selects the first 'Iphone'$")
    public void user_selects_the_first_Iphone() throws Throwable {
        System.out.println("User selects the first Iphone step");
    }
    @Then("^User should see the price in the detail$")
    public void user_should_see_the_price_in_the_detail() throws Throwable {
        System.out.println("User should see the price");
    }
    @And("^User is logged in$")
    public void userIsLoggedIn() {
    }
    @And("^User adds the selected item to the cart$")
    public void userAddsTheSelectedItemToTheCart() {
    }
    @Then("^User should see 'Iphone' in the cart$")
    public void userShouldSeeIphoneInTheCart() {
    }
    @But("^User shouldnt see the 'Iphone' characterics$")
    public void userShouldntSeeTheIphoneCharacterics() {
    }
    @And("^User clicks on the item number (\\d+)$")
    public void userClicksOnTheItemNumber(int pictureIndex) {

        List<WebElement> pictures = wait.
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[@class='s-result-list s-search-results sg-row']//div[@class='a-section aok-relative s-image-square-aspect']")));
        if (pictures.size() > 0 && pictures != null) {
            pictures.get(pictureIndex-1).click();
        }
        else {
            Assert.fail("No such element located");
        }
    }
    @And("^User click on Add to Cart$")
    public void userClickOnAddToCart() {
        WebElement addToCartButton = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
        addToCartButton.click();
    }
    @Then("^User should see 'Added to Cart'$")
    public void userShouldSeeAddedToCart() {
        WebElement addToCartMessage = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        addToCartMessage.getText();
        Assert.assertTrue("Add to cart message not displayed", addToCartMessage.isDisplayed());
        assertEquals("Added to cart", addToCartMessage.getText());
    }
    @When("^User click on the sign in$")
    public void userClickOnTheSignIn() {
        WebElement signInButton = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
        signInButton.click();
    }
    @And("^User enters '(.*)' and '(.*)'$")
    public void userEntersEmailAndPassword(String email, String password) {
        WebElement emailTB = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailTB.sendKeys(email+Keys.ENTER);
        WebElement passwordTB = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordTB.sendKeys(password + Keys.ENTER);
    }
    @Then("^User Login should be '(.*)'$")
    public void userLoginShouldBeResult(String result) {
        if (result.equalsIgnoreCase(("fail"))) {
            WebElement signInMessage = wait.
                    until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-accountList']/span[contains(@class,'1')]")));
            String expected = "Hello " + result;
            assertEquals("Wrong Username displayed" + expected, signInMessage.getText());
        } else {
            WebElement important = wait.
                    until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/h4[contains(text(),'Important Message!')]")));
            assertEquals("Important Message ", important.getText());
        }
    }
}