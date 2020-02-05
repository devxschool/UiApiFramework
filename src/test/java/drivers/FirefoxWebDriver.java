package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxWebDriver {

    public static WebDriver loadFirefoxDriver(boolean headless){
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(headless);

        WebDriver driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(17, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }
}
