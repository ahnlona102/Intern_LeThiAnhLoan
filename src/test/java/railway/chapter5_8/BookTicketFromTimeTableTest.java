package railway.chapter5_8;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.RailwayTab;
import org.railway.models.User;
import org.railway.pages.*;
import org.railway.utils.report.listeners.ReportListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import railway.*;

@Listeners(ReportListener.class)
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
        bookTicketSuccessPage.isSuccessMessageDisplayed(expectedMessage);
        bookTicketSuccessPage.isInformationDisplayed(user);
    }
}
