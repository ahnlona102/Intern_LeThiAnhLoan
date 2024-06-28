package railway.seleniumE2E;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.ConfirmEmail;
import org.railway.enums.RailwayTab;
import org.railway.enums.SeatType;
import org.railway.models.User;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import railway.BaseTest;

public class TestE2E extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    EmailPage emailPage = new EmailPage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    TimetablePage timetablePage = new TimetablePage();
    MyTicketPage myTicketPage = new MyTicketPage();


    @Test(dataProvider = "TestE2E", dataProviderClass = TestDataProvider.class)
    public void TestE2E(User user, String message, String expectedMessage, int HS, int SS, int SSC, int HB, int SB, int SBC, String expecMessage) {
        basePage.navigateToMailPage();
        emailPage.getEmail(user);
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.REGISTER);
        registerPage.registerAccount(user);
        registerPage.isTitleDisplay(expectedMessage);
        basePage.switchToEmail();
        basePage.refreshPage();
        emailPage.confirmEmail(ConfirmEmail.CONFIRM);
        registerPage.isMessageDisplayed(message);
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.TIMETABLE);
        timetablePage.checkPrice(user);
        Assert.assertEquals(ticketPricePage.getHeaderOfSeatPriceTable(), expectedMessage);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.HARDSEAT), HS);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTSEAT), SS);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTSEATWITHAIRCONDITIONER), SSC);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.HARDBED), HB);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTBED), SB);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTBEDWITHAIRCONDITIONER), SBC);
        ticketPricePage.selectSeatType(user);
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.select(BookTicket.DEPARTDATE, user.getDepartDate());
        bookTicketPage.bookTicketButton();
        Assert.assertTrue(bookTicketSuccessPage.isSuccessMessageDisplayed(expecMessage), "Message is not displayed ");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user), "Information is incorrectly ");
        homePage.clickTab(RailwayTab.BOOKTICKET);
        bookTicketPage.select(BookTicket.DEPARTDATE, user.getDepartDate());
        bookTicketPage.select(BookTicket.DEPARTSTATION, user.getDepart());
        bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
        bookTicketPage.select(BookTicket.ARRIVESTATION, user.getArrive());
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.bookTicketButton();
        Assert.assertTrue(bookTicketSuccessPage.isSuccessMessageDisplayed(expecMessage), "Message is not displayed ");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user), "Information is incorrectly ");
        bookTicketSuccessPage.clickTab(RailwayTab.LOGOUT);
        homePage.isTabDisplayed(RailwayTab.HOME);
        homePage.doesTabExist(RailwayTab.LOGOUT);
    }
}
