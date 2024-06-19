package railway.chapter10;

import data.TestDataProvider;
import org.railway.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import railway.BaseTest;

public class LoginTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Test(dataProvider = "chapter10Testcase1", dataProviderClass = TestDataProvider.class, description = "User can login to Railway with valid Username and password")
    public void Testcase1(User user, String expectedMessage){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        homePage.checkWelcomeMessage(expectedMessage);
    }

    @Test(dataProvider = "chapter10Testcase2", dataProviderClass = TestDataProvider.class, description = "User cannot login with blank Username textbox")
    public void Testcase2(User user, String errorMessage){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        loginPage.checkLoginErr(errorMessage);
    }

    @Test(dataProvider = "chapter10Testcase3", dataProviderClass = TestDataProvider.class, description = "User cannot login with blank Password textbox")
    public void Testcase3(User user, String errorMessage){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        loginPage.checkLoginErr(errorMessage);
    }

    @Test(dataProvider = "chapter10Testcase4", dataProviderClass = TestDataProvider.class, description = "System shows message when user enters wrong password many times")
    public void Testcase4(User user, String errorMessage, String attempErrorMessage){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        loginPage.checkLoginErr(errorMessage);
        loginPage.loginManyTimes(user,4);
        loginPage.checkLoginErr(attempErrorMessage);
    }

    @Test(dataProvider = "chapter10Testcase5", dataProviderClass = TestDataProvider.class, description = "User can't login with an account hasn't been activated")
    public void Testcase5(User user, String errorMessage){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(user);
        loginPage.checkLoginErr(errorMessage);
    }
}
