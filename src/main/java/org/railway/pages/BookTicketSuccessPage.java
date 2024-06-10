package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class BookTicketSuccessPage {

    private final By checkmessage = By.cssSelector("h1[align='center']");
    private final By checkdepartStation = By.cssSelector("#content > div > table > tbody > tr.OddRow > td:nth-child(1)");
    private final By checkarriveStation = By.cssSelector("#content > div > table > tbody > tr.OddRow > td:nth-child(2)");
    private final By checkseatType = By.cssSelector("#content > div > table > tbody > tr.OddRow > td:nth-child(3)");
    private final By checkdepartDate = By.cssSelector("#content > div > table > tbody > tr.OddRow > td:nth-child(4)");
    private final By checkamountTicket = By.cssSelector("#content > div > table > tbody > tr.OddRow > td:nth-child(7)");


    public boolean checkInformation(String expectedMessage, String depart, String arrive, String seatType, String departDate, String amountTicket) {
        return Action.getText(Action.find(checkmessage)).equals(expectedMessage) &&
                Action.getText(Action.find(checkdepartStation)).equals(depart) &&
                Action.getText(Action.find(checkarriveStation)).equals(arrive) &&
                Action.getText(Action.find(checkseatType)).equals(seatType) &&
                Action.getText(Action.find(checkdepartDate)).equals(departDate) &&
                Action.getText(Action.find(checkamountTicket)).equals(amountTicket);
    }
}
