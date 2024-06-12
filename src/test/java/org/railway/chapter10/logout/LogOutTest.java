package org.railway.chapter10.logout;

import org.railway.BaseTest;
import org.railway.pages.BasePage;
import org.railway.pages.HomePage;
import org.railway.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    private final String email = "ltanhloannn@grr.la";;
    private final String password = "123456789";
    private final String title = "Welcome to Safe Railway";

    @Test
    public void TC6() {
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(email, password);
        homePage.clickTab("FAQ");
        homePage.clickTab("Log out");
        homePage.checkTab("Home");
        homePage.checkLogOutTab();
    }
}
