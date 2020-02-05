package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmokeTest {

    @Test
    public void testAmazon() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInput.sendKeys("iphone"+ Keys.ENTER);
        //Thread.sleep(2000);

        //Assert.assertEquals("iphone", driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).getAttribute("value"));
        WebElement search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.textToBePresentInElementValue(search, "apple"));


        System.out.println(driver.findElement(By.xpath("//span[contains(text(), 'results')]")).getText());
                       driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        List<WebElement> iphoneResults = driver.findElements(By.xpath("//a/span[contains(text(),'iPhone')]"));

        for(WebElement link:iphoneResults){
            System.out.println(link.getText());
        }

        iphoneResults.get(4).click();
        driver.close();


    }


    @Test
    public void testKyrgyzstan() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("kyrgyzstan");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='kyrgyzstan']")).click();

        Assert.assertEquals("kyrgyzstan",driver.findElement(By.xpath("//input[@name='q']")).getAttribute("value"));

        List<WebElement> links = driver.findElements(By.xpath("//h3[contains(text(),'yrgyzstan')]"));
        //Kyrgyzstan – Travel guide at Wikivoyage

        for(WebElement link:links){
            if(link.getText().equals("Kyrgyzstan – Travel guide at Wikivoyage"))
                link.click();
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Visit the main page']")).isDisplayed());




    }

    @Test
    public void testRadioButton() throws InterruptedException{
         WebDriverManager.chromedriver().setup();
         WebDriver driver = new ChromeDriver();
         //driver.manage().window().maximize();
         driver.get("https://www.toolsqa.com/automation-practice-form");
         Thread.sleep(3000);
         WebElement Male = driver.findElement(By.xpath("//input[@id='sex-0']"));
         Male.click();
         Thread.sleep(1000);
         Assert.assertTrue(Male.isSelected());
         driver.close();
    }

    @Test
    public void etsyTest(){
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      driver.manage().window().maximize();

      driver.get("https://www.etsy.com/");
      driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

      WebDriverWait w = new WebDriverWait(driver, 4);
      w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Register')]")));

      driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();

      WebElement textToVerify = driver.findElement(By.id("join-neu-overlay-title"));
      Assert.assertTrue(textToVerify.isDisplayed());

      driver.close();

    }

    @Test
    public void testMoveToElement(){
         WebDriverManager.chromedriver().setup();
         WebDriver driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
         driver.get("https://www.toolsqa.com/automation-practice-form");

           WebElement checkbox = driver.findElement(By.id("exp-2"));

        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(By.id("profession-0"))).perform();

        checkbox.click();

        Assert.assertTrue(checkbox.isSelected());

        driver.close();

         
    }

    @Test
    public void testHoveringOverTheElement() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/tooltip-and-double-click/");

        WebElement element = driver.findElement(By.id("tooltipDemo"));

        Actions mouseMover = new Actions(driver);

        mouseMover.moveToElement(element).perform();

        WebElement tooltip = driver.findElement(By.className("tooltiptext"));

        Assert.assertTrue(tooltip.isDisplayed());

        Thread.sleep(4000);
        driver.close();


    }

    @Test
    public void testSlider(){
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      driver.get("https://demoqa.com/slider/") ;

      WebElement  slider = driver.findElement(By.xpath("//div[@id='slider']/span")) ;

      Actions slideRight = new Actions(driver);

      slideRight.clickAndHold(slider).moveByOffset(50,0).release().perform();
      //verify that it actually moved the slider   -- verify percentage in a style attribute is > 0
      

    }

    @Test
    public void testDragging(){
     WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
     driver.get("https://demoqa.com/droppable/")  ;

     WebElement source = driver.findElement(By.id("draggable"));

     WebElement target = driver.findElement(By.id("droppable"));

     Actions action = new Actions(driver);

     action.dragAndDrop(source, target).perform();

     Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droppable']/p")).isDisplayed());
     



    }



}
