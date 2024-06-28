package org.railway.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {
    public static WebDriver driver;
    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static WebDriverWait wait;
    private static final String HUB_URL = "http://192.168.60.22:4444";
    public static void setupDriver(String browser) throws MalformedURLException {
        if (browser.equals("chrome")) {
            capabilities.setPlatform(Platform.ANY);
            capabilities.setBrowserName("chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } else if (browser.equals("firefox")) {
            capabilities.setPlatform(Platform.ANY);
            capabilities.setBrowserName("firefox");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(capabilities);
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

}
