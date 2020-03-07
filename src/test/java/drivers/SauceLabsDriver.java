package drivers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SauceLabsDriver {

    public static final String USERNAME = "devxschoolSauceLabs";
    public static final String ACCESS_KEY = "9395d058-e796-4e90-b3e9-21899d3f4132";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    public static WebDriver loadSauceLabs() throws Exception {

        MutableCapabilities sauceOptions = new MutableCapabilities();

        ChromeOptions browserOptions = new ChromeOptions();

        browserOptions.setExperimentalOption("w3c", true);
        browserOptions.setCapability("platformName", "macOS 10.14");
        browserOptions.setCapability("browserVersion", "80.0");
        browserOptions.setCapability("name", "DevXSchool Selenium Tests on Mac OS and Chrome");
        sauceOptions.setCapability("name","DevXSchool Selenium Tests on Mac OS and Chrome");
        browserOptions.setCapability("sauce:options", sauceOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), browserOptions);
        return driver;
    }

    public static WebDriver loadSauceLabsMacAndFireFox() throws Exception {

        MutableCapabilities sauceOptions = new MutableCapabilities();

        FirefoxOptions browserOptions = new FirefoxOptions();

        browserOptions.setCapability("w3c", true);
        browserOptions.setCapability("platformName", "macOS 10.14");
        browserOptions.setCapability("browserVersion", "latest");
        sauceOptions.setCapability("name","DevXSchool Selenium Tests on Mac OS and Firefox");
        browserOptions.setCapability("sauce:options", sauceOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), browserOptions);
        return driver;
    }


    public static WebDriver loadSauceLabsWindows10AndFireFox() throws Exception {

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("screenResolution", "2560x1600");

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setCapability("w3c", true);
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "latest");
        browserOptions.setCapability("sauce:options", sauceOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), browserOptions);
        return driver;
    }
}
