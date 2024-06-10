package org.railway.chaper5;

import org.railway.chaper5.base.BaseTest;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTicketFromTimeTableTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    TimetablePage timeTablePage = new TimetablePage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();


    @Test
    public void Test() {
        homePage.clickTab("Login");
        loginPage.login(properties.getProperty("email"), properties.getProperty("password"));
        homePage.clickTab("Timetable");
        timeTablePage.checkPrice("Sài Gòn", "Đà Nẵng");
        ticketPricePage.selectSeatType("Soft seat");
        bookTicketPage.selectDepartdate(7);
        bookTicketPage.selectAmountTicket(2);
        bookTicketPage.bookTicketButton();

        String expectedMessage = "Ticket booked successfully!";
        String depart = "Sài Gòn";
        String arrive = "Đà Nẵng";
        String seatType = "Soft seat";
        String departDate = "6/17/2024";
        String amountTicket = "2";

        boolean result = bookTicketSuccessPage.checkInformation(expectedMessage, depart, arrive, seatType, departDate, amountTicket);
        Assert.assertTrue(result);
    }
}
