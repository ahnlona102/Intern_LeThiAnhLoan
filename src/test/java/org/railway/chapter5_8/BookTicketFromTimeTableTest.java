package org.railway.chapter5_8;

import org.railway.BaseTest;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BookTicketFromTimeTableTest extends BaseTest {
    BaseTest test = new BaseTest();
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    EmailPage emailPage = new EmailPage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    TimetablePage timeTablePage = new TimetablePage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();
    private String mail;
    private final String username = "ltanhloannn";
    private final String domain = "grr.la";
    private final String password = "123456789";
    private final String passport = "2984345829";
    private final String expectedMessage = "Ticket booked successfully!";
    private final String depart = "Sài Gòn";
    private final String arrive = "Đà Nẵng";
    private final String seatType = "Soft seat";
    private final String departDate = "6/19/2024";
    private final String amountTicket = "2";

    @BeforeTest
    public void RegisterAcc() {
        test.setUp();
        basePage.navigateToMailPage();
        mail = emailPage.getEmail(username, domain);
        BasePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab("Register");
        registerPage.RegisterAccount(mail, password, password, passport);
        basePage.switchToEmail();
        basePage.refreshPage();
        emailPage.confirmEmail();
    }

    @Test
    public void BookTicketTest() {
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab("Login");
        loginPage.login(mail, password);
        homePage.clickTab("Timetable");
        timeTablePage.checkPrice(depart, arrive);
        ticketPricePage.selectSeatType(seatType);
        bookTicketPage.selectDepartdate(7);
        bookTicketPage.selectAmountTicket(2);
        bookTicketPage.bookTicketButton();

        boolean result = bookTicketSuccessPage.checkInformation(expectedMessage, depart, arrive, seatType, departDate, amountTicket);
        Assert.assertTrue(result);
    }
}
