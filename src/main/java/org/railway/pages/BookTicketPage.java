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

    public void selectDate(String date) {
        By dateField = By.xpath(String.format(dateDate, date));
        Action.selectByVisibleText(Action.find(dateField), date);
    }

    public void selectDepartdate(int date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, date);
        Date afterDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatdate = dateFormat.format(afterDate);
        By selectDate = By.xpath(String.format(dateDate, formatdate));
        Action.click(Action.find(selectDate));
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
