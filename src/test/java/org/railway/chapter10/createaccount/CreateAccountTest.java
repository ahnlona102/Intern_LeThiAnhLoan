package org.railway.chapter10.createaccount;

import org.railway.BaseTest;
import org.railway.pages.BasePage;
import org.railway.pages.EmailPage;
import org.railway.pages.HomePage;
import org.railway.pages.RegisterPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {
    BaseTest baseTest = new BaseTest();
    BasePage basePage = new BasePage();
    EmailPage mailPage = new EmailPage();
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    private String mail;
    private final String username = "leloan012345678";
    private final String domain = "grr.la";
    private final String password = "123456789";
    private final String passport = "2984345829";
    private final String blankPassword = "";
    private final String blankPassport = "";
    private final String errorMessage = "This email address is already in use.";
    private final String message = "There're errors in the form. Please correct the errors and try again.";
    private final String passwordFieldErr = "Invalid password length.";
    private final String passportFieldErr = "Invalid ID length";

    @BeforeTest
    public void Account(){
        baseTest.setUp();
        basePage.navigateToMailPage();
        mail = mailPage.getEmail(username, domain);
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail, password, password, passport);
        BasePage.switchToEmail();
        BasePage.refreshPage();
        mailPage.confirmEmail();
        baseTest.tearDown();
    }

    @Test
    public void TC7(){
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail, password, password, passport);
        registerPage.checkRegisterErr(errorMessage);
    }

    @Test
    public void TC8(){
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail, blankPassword, blankPassword, blankPassport);
        registerPage.checkRegisterErr(message);
        registerPage.checkpasswordFieldErr(passportFieldErr);
        registerPage.checkpassportFieldErr(passportFieldErr);
    }
}
