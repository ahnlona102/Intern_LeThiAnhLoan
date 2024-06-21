package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class BookTicketSuccessPage extends BasePage{

    private String checkmessage = "//h1[contains(text(),'%s')]";
    private String option = "//table[@class='MyTable WideTable']//tr[@class='OddRow']//td[text()='%s']";

    public boolean isSuccessMessageDisplayed(String message) {
        By messageLocator = By.xpath(String.format(checkmessage, message));
        return Action.isDisplayed(messageLocator);
    }

    public boolean isInformationDisplayed(String expectedInfo) {
        By checkInfo = By.xpath(String.format(option, expectedInfo));
        return Action.isDisplayed(checkInfo);
    }
}
