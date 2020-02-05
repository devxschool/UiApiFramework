package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeTest2 {

    @Test
    public void testAmazon() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        driver.get("https://www.amazon.com/");

        //Thread.sleep(2000);
        driver.findElement(By.id("nav-hamburger-menu")).click();
       //Thread.sleep(2000);

        WebElement text = driver.findElement(By.id("hmenu-customer-name"));
        Assert.assertTrue(text.isDisplayed());
        driver.close();
    }

    @Test
    public void testTea() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.practiceselenium.com/");
        driver.findElement(By.linkText("Let's Talk Tea")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("name")).sendKeys("John");
        driver.findElement(By.name("email")).sendKeys("abc123@gmail.com");
        driver.findElement(By.name("subject")).sendKeys("special request");
        driver.findElement(By.name("message")).sendKeys("some random text");
        driver.findElement(By.className("form-submit")).click();

    }
}
