package railway.chapter10;

import data.TestDataProvider;
import org.railway.enums.BookTicket;
import org.railway.enums.RailwayTab;
import org.railway.enums.SeatType;
import org.railway.models.User;
import org.railway.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import railway.BaseTest;

public class BookTicketTest extends BaseTest {
    BasePage basePage = new BasePage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    BookTicketSuccessPage bookTicketSuccessPage = new BookTicketSuccessPage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    TimetablePage timetablePage = new TimetablePage();

    @Test(dataProvider = "chapter10Testcase12", dataProviderClass = TestDataProvider.class, description = "User can book 1 ticket at a time")
    public void Testcase12(User user, int date, String expectedMessage){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.BOOKTICKET);
        bookTicketPage.selectDepartdate(date);
        bookTicketPage.select(BookTicket.DEPARTSTATION, user.getDepart());
        bookTicketPage.select(BookTicket.ARRIVESTATION, user.getArrive());
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
        bookTicketPage.bookTicketButton();
        bookTicketSuccessPage.isSuccessMessageDisplayed(expectedMessage);
        bookTicketSuccessPage.isInformationDisplayed(user.getDepart());
        bookTicketSuccessPage.isInformationDisplayed(user.getArrive());
        bookTicketSuccessPage.isInformationDisplayed(user.getSeatType());
        bookTicketSuccessPage.isInformationDisplayed(user.getAmountTicket());
        bookTicketSuccessPage.isInformationDisplayed(user.getDepartDate());
    }

    @Test(dataProvider = "chapter10Testcase13", dataProviderClass = TestDataProvider.class, description = "User can book many tickets at a time")
    public void Testcase13(User user, int date, String expectedMessage){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.BOOKTICKET);
        bookTicketPage.selectDepartdate(date);
        bookTicketPage.select(BookTicket.DEPARTSTATION, user.getDepart());
        bookTicketPage.select(BookTicket.ARRIVESTATION, user.getArrive());
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
        bookTicketPage.bookTicketButton();
        bookTicketSuccessPage.isSuccessMessageDisplayed(expectedMessage);
        bookTicketSuccessPage.isInformationDisplayed(user.getDepart());
        bookTicketSuccessPage.isInformationDisplayed(user.getArrive());
        bookTicketSuccessPage.isInformationDisplayed(user.getSeatType());
        bookTicketSuccessPage.isInformationDisplayed(user.getAmountTicket());
        bookTicketSuccessPage.isInformationDisplayed(user.getDepartDate());
    }

    @Test(dataProvider = "chapter10Testcase14", dataProviderClass = TestDataProvider.class, description = "User can check price of ticket from Timetable")
    public void Testcase14(User user, String title, String expectedMessage, int HS, int SS, int SB, int SSC, int SBC, int HB){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.TIMETABLE);
        timetablePage.checkPrice(user);
        basePage.isTitleDisplay(title);
        Assert.assertEquals(ticketPricePage.getHeaderOfSeatPriceTable(), expectedMessage);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.HARDSEAT), HS);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTSEAT), SS);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTSEATWITHAIRCONDITIONER), SSC);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.HARDBED), HB);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTBED), SB);
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType(SeatType.SOFTBEDWITHAIRCONDITIONER), SBC);
    }

    @Test(dataProvider = "chapter10Testcase15", dataProviderClass = TestDataProvider.class, description = "User can book ticket from Timetable")
    public void Testcase15(User user, int date, String expectedMessage){
        basePage.navigateToRailway();
        homePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        homePage.clickTab(RailwayTab.TIMETABLE);
        timetablePage.bookTicket(user);
        bookTicketPage.selectDepartdate(date);
        bookTicketPage.select(BookTicket.AMOUNTTICKET, user.getAmountTicket());
        bookTicketPage.select(BookTicket.SEATTYPE, user.getSeatType());
        bookTicketPage.bookTicketButton();
        bookTicketSuccessPage.isSuccessMessageDisplayed(expectedMessage);
        bookTicketSuccessPage.isInformationDisplayed(user.getDepart());
        bookTicketSuccessPage.isInformationDisplayed(user.getArrive());
        bookTicketSuccessPage.isInformationDisplayed(user.getSeatType());
        bookTicketSuccessPage.isInformationDisplayed(user.getAmountTicket());
        bookTicketSuccessPage.isInformationDisplayed(user.getDepartDate());
    }

}
