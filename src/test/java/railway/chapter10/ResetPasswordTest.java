package railway.chapter10;

import data.TestDataProvider;
import org.railway.enums.*;
import org.railway.models.User;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import railway.BaseTest;

public class ResetPasswordTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    EmailPage mailPage = new EmailPage();
    LoginPage loginPage = new LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

    @Test(dataProvider = "chapter10Testcase10", dataProviderClass = TestDataProvider.class, description = "Reset password shows error if the new password is same as current")
    public void Testcase10(User user,  String errorMessage){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.clickHyperlinkByName(NameHyperlink.FORGOTPASSWORD);
        forgotPasswordPage.submitResetPasswordForm(user);
        basePage.switchToNewTab();
        basePage.navigateToMailPage();
        mailPage.setMail(user);
        mailPage.checkConfirmMess(ConfirmEmail.RESET);
        //String resetToken = mailPage.getResetPasswordToken();
        mailPage.clickConfirmLink();
        resetPasswordPage.isTitleFormDisplayed(Title.PASSWORD_CHANGE_FORM);
        resetPasswordPage.enterField(ResetPassword.NEWPASS, user.getPassword());
        resetPasswordPage.enterField(ResetPassword.CONFIRMNEWPASS, user.getConfirmPassword());
        resetPasswordPage.clickButton(ResetPassword.RESETBUTTON);
        //Assert.assertEquals(resetPasswordPage.getResetToken(), resetToken );
        Assert.assertEquals(resetPasswordPage.getMessageNextConfirmPassword(), errorMessage);
    }

    @Test(dataProvider = "chapter10Testcase11", dataProviderClass = TestDataProvider.class, description = "Reset password shows error if the new password and confirm password doesn't match")
    public void Testcase11(User user, String errorMess, String errorMessage){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.clickHyperlinkByName(NameHyperlink.FORGOTPASSWORD);
        forgotPasswordPage.submitResetPasswordForm(user);
        basePage.switchToNewTab();
        basePage.navigateToMailPage();
        mailPage.setMail(user);
        mailPage.checkConfirmMess(ConfirmEmail.RESET);
        //String resetToken = mailPage.getResetPasswordToken();
        mailPage.clickConfirmLink();
        resetPasswordPage.isTitleFormDisplayed(Title.PASSWORD_CHANGE_FORM);
        resetPasswordPage.isTitleFormDisplayed(Title.PASSWORD_CHANGE_FORM);
        resetPasswordPage.enterField(ResetPassword.NEWPASS, user.getPassword());
        resetPasswordPage.enterField(ResetPassword.CONFIRMNEWPASS, user.getConfirmPassword());
        resetPasswordPage.clickButton(ResetPassword.RESETBUTTON);
        //Assert.assertEquals(resetPasswordPage.getResetToken(), resetToken);
        Assert.assertEquals(resetPasswordPage.getMessageAboveForm(), errorMess);
        Assert.assertEquals(resetPasswordPage.getMessageNextConfirmPassword(), errorMessage);
    }
}
