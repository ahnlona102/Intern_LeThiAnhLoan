package org.test.chapter2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class confirmTitle {
    WebDriver driver;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void testTitle(){
        String url = "https://www.selenium.dev/documentation/";
        String expectedTitle = "The Selenium Browser Automation Project | Selenium";
        driver.get(url);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Test failed!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
