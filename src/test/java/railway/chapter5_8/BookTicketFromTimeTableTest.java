package railway.chapter5_8;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.RailwayTab;
import org.railway.models.User;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import railway.*;

public class BookTicketFromTimeTableTest extends BaseTest {

    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    TimetablePage timetablePage = new TimetablePage();
    HomePage homePage = new HomePage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();

    @Test(dataProvider = "bookTicketChapter5_8", dataProviderClass = TestDataProvider.class)
    public void BookTicketTest(User user, String expectedMessage, int date) {
        basePage.switchToNewTab();
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.TIMETABLE);
        timetablePage.checkPrice(user);
        ticketPricePage.selectSeatType(user);
        bookTicketPage.selectDepartdate(date);
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.bookTicketButton();
        Assert.assertTrue(bookTicketSuccessPage.isSuccessMessageDisplayed(expectedMessage), "Success message is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user.getDepart()), "Depart information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user.getArrive()), "Arrive information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user.getSeatType()), "Seat type information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user.getDepartDate()), "Depart date information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.isInformationDisplayed(user.getAmountTicket()), "Amount ticket information is not displayed correctly");
    }
}
