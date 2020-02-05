package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TestActionsClass {
    @Test
    public void simpleForm(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        driver.get("https://demoqa.com/keyboard-events-sample-form/");

        WebElement current = driver.findElement(By.id("currentAddress"));
        WebElement permanent= driver.findElement(By.id("permanentAddress"));

        current.sendKeys("2400 Devon Ave, Des Plaines");
        // select, copy and paste

        Actions action = new Actions(driver);

        current.click();
        action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
        action.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
        permanent.click();
        action.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();


    }

    @Test
    public void testDoubleClick(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://demoqa.com/tooltip-and-double-click/");
        WebElement button = driver.findElement(By.id("doubleClickBtn"));

        Actions action = new Actions(driver);

        action.doubleClick(button).perform();

    }

    @Test
    public void testRightClick() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/tooltip-and-double-click/");
        WebElement element = driver.findElement(By.id("rightClickBtn"));
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
        WebElement textToVerify = driver.findElement(By.xpath("//div[contains(text(),'Edit this')]"));
        //textToVerify.click();
        Assert.assertTrue(textToVerify.isDisplayed());

        driver.close();



    }





}
