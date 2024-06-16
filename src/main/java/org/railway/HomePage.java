package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class HomePage extends BasePage{
    private By welcomeMessage = By.xpath("//strong[normalize-space()='%s']");

    public boolean checkWelcomeMessage(String expectedMessage) {
        By welcomemessage = By.xpath(String.format(welcomeMessage.toString(), expectedMessage));
        return Action.isDisplayed(welcomeMessage);
    }
}


