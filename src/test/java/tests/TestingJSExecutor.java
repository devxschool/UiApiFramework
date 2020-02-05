package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestingJSExecutor {
    @Test

    public void test1(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");
        //get title using js
        JavascriptExecutor js = (JavascriptExecutor)driver;
        System.out.println(js.executeScript("return document.title").toString());
        //twotabsearchtextbox
        js.executeScript("document.getElementById('twotabsearchtextbox').value = 'apple';");

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        js.executeScript("arguments[0].click();", submitButton);

        ////div[text()='Get to Know Us']

        WebElement element = driver.findElement(By.xpath("//div[text()='Get to Know Us']"));

        js.executeScript("arguments[0].scrollIntoView(true);", element);











    }

}
