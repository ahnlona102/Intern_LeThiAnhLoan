package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.railway.utils.Action;
import org.railway.utils.Driver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class BookTicketPage {

    private String dateDate = "//select[@name='Date']//option[text()='%s']";
    private By amountTicket = By.cssSelector("select[name='TicketAmount']");
    private By bookTicketbutton = By.xpath("//input[@value='Book ticket']");
    //private By bookTicketTable = By.xpath("//table[@class='MyTable WideTable']");
    private By departStation = By.xpath("//select[@name='DepartStation']");
    private By arriveStation = By.xpath("//select[@name='ArriveStation']");
    private By seatType = By.xpath("//select[@name='SeatType']");
    //private String arriveStation = "//select[@name='ArriveStation']//option[text()='%s']";
    //private String departStation = "//select[@name='DepartStation']//option[text()='%s']";
    //private String seatType ="//select[@name='SeatType']//option[text()='%s']";


    public void selectDepartdate(int date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, date);
        Date afterDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatdate = dateFormat.format(afterDate);
        By selectDate = By.xpath(String.format(dateDate, formatdate));
        Action.click(Action.find(selectDate));
    }

    public void selectDepartStation(String depart){
        Action.selectByVisibleText(Action.find(departStation), depart);
    }

    public void selectArriveStation(String arrive) {
        Action.selectByVisibleText(Action.find(arriveStation), arrive);
    }

    public void selectSeatType(String seat) {
        Action.selectByVisibleText(Action.find(seatType), seat);
    }

    public void selectAmountTicket(int amount) {
        Action.selectByValue(Action.find(amountTicket), String.valueOf(amount));
    }

    public void bookTicketButton() {
        WebElement button = Action.find(bookTicketbutton);
        Action.scroll(button);
        Action.clickBeAble(button);
    }

}
