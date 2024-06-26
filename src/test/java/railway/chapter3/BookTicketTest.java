package railway.chapter3;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.ConfirmEmail;
import org.railway.enums.RailwayTab;
import org.railway.models.User;
import org.railway.pages.*;
import org.railway.utils.report.listeners.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import railway.*;

@Listeners(ReportListener.class)
public class BookTicketTest extends BaseTest {

        BasePage basePage = new BasePage();
        HomePage homePage = new HomePage();
        EmailPage mailPage = new EmailPage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();

        @Test(dataProvider = "bookTicketChapter3", dataProviderClass = TestDataProvider.class)
        public void BookTicketTest(User user) {
            basePage.navigateToMailPage();
            mailPage.getEmail(user);
            basePage.switchToNewTab();
            basePage.navigateToRailway();
            homePage.clickTab(RailwayTab.REGISTER);
            registerPage.registerAccount(user);
            basePage.switchToEmail();
            basePage.refreshPage();
            mailPage.checkConfirmMess(ConfirmEmail.CONFIRM);
            mailPage.clickConfirmLink();
            basePage.switchToNewTab();
            basePage.navigateToRailway();
            homePage.clickTab(RailwayTab.LOGIN);
            loginPage.login(user);
            homePage.clickTab(RailwayTab.BOOKTICKET);
            bookTicketPage.select(BookTicket.ARRIVESTATION, user.getArrive());
            bookTicketPage.selectDepartdate(3);
            bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
            bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
            bookTicketPage.bookTicketButton();
        }
    }

