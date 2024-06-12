package org.railway;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.railway.utils.ConfigDriver;
import org.railway.utils.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        String browser = ConfigDriver.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            Driver.driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            Driver.driver = new FirefoxDriver();
        }

        Driver.driver.manage().window().maximize();
        Driver.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        Driver.driver.quit();
    }
}
