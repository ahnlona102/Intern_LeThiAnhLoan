package org.railway.chaper5.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.railway.utils.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;

    @BeforeMethod
    public void setUp() {
        properties = new Properties();
        try {
            FileInputStream emp = new FileInputStream("D:\\Selenium_LoanLe\\Exercise\\src\\main\\java\\org\\railway\\config\\config.properties");
            properties.load(emp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        Driver.driver = driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(properties.getProperty("url"));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
