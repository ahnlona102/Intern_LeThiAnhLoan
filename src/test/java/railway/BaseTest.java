package railway;

import org.railway.pages.BasePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.railway.utils.DriverManager;

import java.net.MalformedURLException;

public class BaseTest extends BasePage {

    @Parameters({"browser", "type"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("local") String type) throws MalformedURLException {
        if (type.equalsIgnoreCase("remote")) {
            DriverManager.initRemoteDriver(browser);
            DriverManager.getDriver().manage().window().maximize();
        } else {
            DriverManager.initLocalDriver(browser);
            DriverManager.getDriver().manage().window().maximize();
        }
    }


    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
