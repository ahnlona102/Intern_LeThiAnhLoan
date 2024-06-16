package railway.chapter3;

import data.TestDataProvider;
import org.railway.*;
import org.testng.annotations.Test;
import railway.BaseTest;

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
            homePage.clickTab("Register");
            registerPage.registerAccount(user);
            basePage.switchToEmail();
            basePage.refreshPage();
            mailPage.confirmEmail("Please confirm");
            basePage.switchToNewTab();
            basePage.navigateToRailway();
            homePage.clickTab("Login");
            loginPage.login(user);
            homePage.clickTab("Book ticket");
            bookTicketPage.select("ArriveStation", user.getArrive());
            bookTicketPage.selectDepartdate(3);
            bookTicketPage.select("SeatType", user.getSeatType());
            bookTicketPage.select("TicketAmount", user.getAmountTicket());
            bookTicketPage.bookTicketButton();
        }
    }

