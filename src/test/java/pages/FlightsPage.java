package pages;

import drivers.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsPage extends BasePage{
    WebDriver driver;

    public FlightsPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="flight-origin-hp-flight")
    public WebElement originInputField;

    @FindBy(id="flight-destination-hp-flight")
    public WebElement destinationInputField;

    @FindBy(xpath = "//div/label/button[@type='submit']")
    public WebElement searchButton;


}
