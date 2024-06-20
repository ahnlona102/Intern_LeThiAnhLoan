package railway.chapter10;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.RailwayTab;
import org.railway.models.User;
import org.railway.pages.*;
import org.railway.utils.DateUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import railway.BaseTest;

public class CancelTicketTest extends BaseTest {
    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    HomePage homePage = new HomePage();
    MyTicketPage myTicketPage = new MyTicketPage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();

    @Test(dataProvider = "chapter10Testcase16", dataProviderClass = TestDataProvider.class, description = "User can cancel a ticket")
    public void Testcase16(User user){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        loginPage.clickTab(RailwayTab.BOOKTICKET);
        bookTicketPage.select(BookTicket.DEPARTSTATION, user.getDepart());
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
        bookTicketPage.select(BookTicket.DEPARTDATE, user.getDepartDate());
        bookTicketPage.select(BookTicket.ARRIVESTATION, user.getArrive());
        bookTicketPage.bookTicketButton();
        bookTicketSuccessPage.clickTab(RailwayTab.MYTICKET);
        myTicketPage.cancelTicket(user);
        myTicketPage.acceptCancelTicket();
        myTicketPage.isCancelledTicketDisplayed(user);
    }
}
