package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExpediaTest {

    @Test
    public void testDropdown() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        driver.findElement(By.id("tab-cruise-tab-hp")).click();
        //Thread.sleep(2000);
        WebElement selectElement = driver.findElement(By.id("cruise-destination-hp-cruise"));

        Select destination = new Select(selectElement);
        List<WebElement> myOptions = destination.getOptions();

        for(WebElement option: myOptions){
            System.out.println(option.getText());
        }
        //Thread.sleep(1000);

        destination.selectByValue("alaska");
        //Thread.sleep(2000);
        destination.selectByVisibleText("Africa");
        //Thread.sleep(2000);
        destination.selectByIndex(5);

        List<WebElement> selectedDestinations = destination.getAllSelectedOptions();

        Assert.assertEquals("Europe", selectedDestinations.get(0).getText());

    }

    @Test
    public void testDropdown2() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.toolsqa.com/automation-practice-form");

        WebElement continents = driver.findElement(By.id("continentsmultiple"));

        Select destination = new Select(continents);

        destination.selectByVisibleText("Asia");
        destination.selectByIndex(1);
        destination.selectByValue("AUS");

        Thread.sleep(2000);
        destination.deselectByVisibleText("Europe");
        Thread.sleep(1000);
        List<WebElement> selectedOptions = destination.getAllSelectedOptions();

        for (WebElement option:selectedOptions){
            System.out.println(option.getText());
        }

    }
    //Macintosh HD⁩ ▸ ⁨Users⁩ ▸ ⁨devxschool⁩ ▸ ⁨Desktop⁩

    @Test
    public void testFileUpload() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/keyboard-events/");

        driver.findElement(By.id("browseFile")).sendKeys("/Users/devxschool/Desktop/DevX-School.png");
        Thread.sleep(1000);
        driver.findElement(By.id("uploadButton")).click();
    }

    @Test
    public void testFileDownload() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);


        driver.get("https://www.seleniumeasy.com/test/generate-file-to-download-demo.html");
        driver.findElement(By.id("textbox")).sendKeys("DevX School");

        WebElement button = driver.findElement(By.id("create"));
        Thread.sleep(1000);
        Assert.assertTrue(button.isEnabled());
        button.click();
        driver.findElement(By.linkText("Download")).click();




    }


}
