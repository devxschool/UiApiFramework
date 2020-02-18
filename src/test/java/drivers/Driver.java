package drivers;

import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

public class Driver {
      //this is our driver factory - pattern- you manage all the drivers in one class
    //driver class
    private Driver(){};

    private static WebDriver driver;

    public static WebDriver getDriver() {
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

       if(driver == null){
           switch(ConfigReader.getProperty("browser").toLowerCase()){
               case "chrome":
                   driver = ChromeWebDriver.loadChromeDriver(headless);
                   break;
               case "firefox":
                   driver = FirefoxWebDriver.loadFirefoxDriver(headless);
                   break;
               case "sauceLabs":
                   try {
                       driver = SauceLabsDriver.loadSauceLabs();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   break;
               default:
                   driver = ChromeWebDriver.loadChromeDriver(false);
           }
       }
       return driver;
    }


    public static void closeDriver(){
        if(driver == null) return;
        try{
            driver.close();
            driver.quit();
            driver = null;
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
