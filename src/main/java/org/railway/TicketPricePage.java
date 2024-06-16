package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class TicketPricePage extends BasePage{
    private String seatType = "//tr[td[text()='%s']]//a[text()='Book ticket']";

    public void selectSeatType(User user) {
        By seattype = By.xpath(String.format(seatType, user.getSeatType()));
        Action.click(seattype);
    }
}
