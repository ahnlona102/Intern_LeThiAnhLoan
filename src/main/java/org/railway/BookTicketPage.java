package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookTicketPage extends BasePage{

    private String dateDate = "//select[@name='Date']//option[text()='%s']";
    private By bookTicketbutton = By.xpath("//input[@value='Book ticket']");
    //private By bookTicketTable = By.xpath("//table[@class='MyTable WideTable']");
    private String selectOption = "//select[@name='%s']";


    public void selectDepartdate(int date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, date);
        Date afterDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatdate = dateFormat.format(afterDate);
        By selectDate = By.xpath(String.format(dateDate, formatdate));
        Action.find(selectDate).click();
    }

    public void select(String optionName, String value) {
        By option = By.xpath(String.format(selectOption, optionName));
        Action.scroll(option);
        Action.clickIfClickable(option);
        try {
            int intValue = Integer.parseInt(value);
            if (intValue >= 1 && intValue <= 10) {
                Action.selectByValue(option, value);
            } else {
                Action.selectByVisibleText(option, value);
            }
        } catch (NumberFormatException e) {
            Action.selectByVisibleText(option, value);
        }
    }

    public void bookTicketButton() {
        Action.scroll(bookTicketbutton);
        Action.clickIfClickable(bookTicketbutton);
    }
}
