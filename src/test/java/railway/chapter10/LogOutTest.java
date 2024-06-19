package railway.chapter10;

import data.TestDataProvider;
import org.railway.BasePage;
import org.railway.HomePage;
import org.railway.LoginPage;
import org.railway.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import railway.BaseTest;

public class LogOutTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test(dataProvider = "chapter10Testcase6", dataProviderClass = TestDataProvider.class, description = "User is redirected to Home page after logging out")
    public void Testcase6(User user){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        homePage.clickTab("FAQ");
        homePage.clickTab("Log out");
        basePage.checkTab("Home");
        homePage.checkDisappear("Log out");
    }

}
