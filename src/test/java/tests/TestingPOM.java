package tests;

import drivers.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.FlightsPage;
import utilities.ConfigReader;

public class TestingPOM {

    @Test
    public void test1(){
        WebDriver driver = Driver.getDriver();
        String url = ConfigReader.getProperty("baseUrl");
        driver.get(url);

        BasePage expediaHome = new BasePage();
        //expediaHome.ListYourPropertyButton.click();
        //expediaHome.FlightsButton.click();
        expediaHome.clickFlightsButton();
    }

    @Test
    public void test2(){
        WebDriver driver = Driver.getDriver();
        String url = ConfigReader.getProperty("baseUrl");
        driver.get(url);

        BasePage expediaHome = new BasePage();
        expediaHome.FlightsButton.click();

        FlightsPage flight = new FlightsPage();
        flight.originInputField.sendKeys("Chicago, IL");
        flight.destinationInputField.sendKeys("Tampa, FL");
        flight.searchButton.click();
    }

    @Test
    public void test3(){
        WebDriver driver = Driver.getDriver();
        String url = ConfigReader.getProperty("baseUrl");
        driver.get(url);

        BasePage expediaHome = new BasePage();

        expediaHome.moveToTheBottomOfThePage();

        expediaHome.globalSites.get(2).click();




    }




}
