package railway.chapter10;

import data.TestDataProvider;
import org.railway.enums.RailwayTab;
import org.railway.pages.BasePage;
import org.railway.pages.HomePage;
import org.railway.pages.LoginPage;
import org.railway.models.User;
import org.testng.annotations.Test;
import railway.BaseTest;

public class LogOutTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test(dataProvider = "chapter10Testcase6", dataProviderClass = TestDataProvider.class, description = "User is redirected to Home page after logging out")
    public void Testcase6(User user){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.FAQ);
        homePage.clickTab(RailwayTab.LOGOUT);
        basePage.isTabDisplayed(RailwayTab.HOME);
        homePage.doesTabExist(RailwayTab.LOGOUT);
    }

}
