package org.railway.chapter10.login;

import org.railway.BaseTest;
import org.railway.pages.BasePage;
import org.railway.pages.HomePage;
import org.railway.pages.LoginPage;
import org.railway.pages.RegisterPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class LoginTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    private final String email = "ltanhloannn@grr.la";
    private String mail = "abc123@gmail.com";
    private final String password = "123456789";
    private final String passport = "8748456789";
    private final String blankEmail = "";
    private final String invalidPassword = "";
    private String wrongPassword = "123";
    private final String expectedMess = "Welcome ltanhloannn@grr.la";
    private final String errorMessage = "There was a problem with your login and/or errors exist in your form.";
    private String errorMess = "Invalid username or password. Please try again.";
    private String attempErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

    @Test
    public void TC1() {
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(email, password);
        homePage.checkWelcomeMessage(expectedMess);
    }

    @Test
    public void TC2() {
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(blankEmail,password);
        loginPage.checkLoginErr(errorMessage);
    }

    @Test
    public void TC3() {
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(email,invalidPassword);
        loginPage.checkLoginErr(errorMessage);
    }

    @Test
    public void TC4(){
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        for(int i = 0; i < 5; i++){
            loginPage.login(email, wrongPassword);
            loginPage.clearEmailField();
            if(i<3){
                loginPage.checkLoginErr(errorMess);
            } else{
                try{
                    loginPage.checkLoginErr(attempErrorMessage);
                } catch (NoSuchElementException e){
                    System.out.println("Error message not found");
                }
            }
        }

    }

    @Test
    public void TC5(){
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail,password,password,passport);
        homePage.clickTab("Login");
        loginPage.login(mail, password);
        loginPage.checkLoginErr(errorMess);
    }

}
