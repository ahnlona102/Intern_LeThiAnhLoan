package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class TicketPricePage {
    private String seatType = "//tr[td[text()='%s']]//a[text()='Book ticket']";

    public void selectSeatType(String seattype) {
        Action.click(Action.find(By.xpath(String.format(seatType, seattype))));
    }
}
