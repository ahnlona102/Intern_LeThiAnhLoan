package org.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.railway.utils.Action;

public class HomePage extends BasePage{
    private By welcomeMessage = By.xpath("//div[@id='banner']//strong");;

    public boolean checkWelcomeMessage(String expectedMessage) {
        String actualMessage = Action.getText(welcomeMessage);
        return actualMessage.equals(expectedMessage);
    }

}


