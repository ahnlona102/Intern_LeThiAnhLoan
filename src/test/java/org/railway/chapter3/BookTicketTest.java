package org.railway.chapter3;

import org.railway.BaseTest;
import org.railway.pages.*;
import org.testng.annotations.Test;

public class BookTicketTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    EmailPage mailPage = new EmailPage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    private String mail;
    private String username = "abcd097676";
    private String domain = "grr.la";
    private String password = "123456789";
    private String passport = "2984345829";
    private String arrive = "Phan Thiết";
    //private String depart = "Đà Nẵng";
    private String seatType = "Soft seat";
    private String amountTicket = "2";

    @Test
    public void BookTicketTest() {
        BasePage.navigateToMailPage();
        mail = mailPage.getEmail(username, domain);
        BasePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail, password, password, passport);
        BasePage.switchToEmail();
        BasePage.refreshPage();
        mailPage.confirmEmail();
        BasePage.switchToNewTab();
        BasePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(mail, password);
        homePage.clickTab("Book ticket");
        bookTicketPage.selectArriveStation(arrive);
        bookTicketPage.selectDepartdate(3);
        bookTicketPage.selectSeatType(seatType);
        bookTicketPage.selectAmountTicket(2);
        bookTicketPage.bookTicketButton();
    }

}
