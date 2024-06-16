package railway.chapter5_8;

import data.TestDataProvider;
import org.railway.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import railway.BaseTest;

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
        homePage.clickTab("Login");
        loginPage.login(user);
        homePage.clickTab("Timetable");
        timetablePage.checkPrice(user);
        ticketPricePage.selectSeatType(user);
        bookTicketPage.selectDepartdate(date);
        bookTicketPage.select("TicketAmount", user.getAmountTicket());
        bookTicketPage.bookTicketButton();
        Assert.assertTrue(bookTicketSuccessPage.checkSuccessMessage(expectedMessage), "Success message is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.checkInformation(user.getDepart()), "Depart information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.checkInformation(user.getArrive()), "Arrive information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.checkInformation(user.getSeatType()), "Seat type information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.checkInformation(user.getDepartDate()), "Depart date information is not displayed correctly");
        Assert.assertTrue(bookTicketSuccessPage.checkInformation(user.getAmountTicket()), "Amount ticket information is not displayed correctly");
    }
}
