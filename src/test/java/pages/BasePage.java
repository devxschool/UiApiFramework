package pages;

import drivers.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(){
        driver = Driver.getDriver();
       //this will instantiate every single webelement
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a[contains(text(),'List your property')]")
    public WebElement ListYourPropertyButton;

    @FindBy(id="tab-flight-tab-hp")
    public WebElement FlightsButton;

    @FindBys({@FindBy(xpath = "//ul[@id='global-sites-links']/li")})
    public List<WebElement> globalSites;

    @FindBy(xpath = "//img[@id='EG-logo']")
    public WebElement logoAtTheBottom;


    public void moveToTheBottomOfThePage(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", logoAtTheBottom);
    }

    public void clickFlightsButton(){
        FlightsButton.click();
    }


}
