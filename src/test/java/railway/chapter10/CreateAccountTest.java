package railway.chapter10;

import data.TestDataProvider;
import org.railway.enums.ConfirmEmail;
import org.railway.enums.RailwayTab;
import org.railway.models.User;
import org.railway.pages.BasePage;
import org.railway.pages.EmailPage;
import org.railway.pages.HomePage;
import org.railway.pages.RegisterPage;
import org.testng.annotations.Test;
import railway.BaseTest;

public class CreateAccountTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    EmailPage emailPage = new EmailPage();
    RegisterPage registerPage = new RegisterPage();

    @Test(dataProvider = "chapter10Testcase7", dataProviderClass = TestDataProvider.class, description = "User can't create account with an already in-use email")
    public void Testcase7(User user, String errorMessage) {
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.REGISTER);
        registerPage.registerAccount(user);
        registerPage.isRegisterErrorDisplayed(errorMessage);
    }

    @Test(dataProvider = "chapter10Testcase8", dataProviderClass = TestDataProvider.class, description = "User can't create account while password and PID fields are empty")
    public void Testcase8(User user, String errorMessage, String passwordFieldError, String passportFieldError) {
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.REGISTER);
        registerPage.registerAccount(user);
        registerPage.isRegisterErrorDisplayed(errorMessage);
        registerPage.isPasswordFieldError(passwordFieldError);
        registerPage.isPassportFieldError(passportFieldError);
    }

    @Test(dataProvider = "chapter10Testcase9", dataProviderClass = TestDataProvider.class, description = "User create and activate account")
    public void Testcase9(User user, String successMessage, String title) {
        basePage.navigateToMailPage();
        emailPage.getEmail(user);
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.REGISTER);
        registerPage.registerAccount(user);
        registerPage.isTitleDisplay(title);
        basePage.switchToEmail();
        basePage.refreshPage();
        emailPage.confirmEmail(ConfirmEmail.CONFIRM);
        registerPage.isMessageDisplayed(successMessage);
    }
}
