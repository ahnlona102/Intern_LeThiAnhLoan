package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.railway.utils.Action;
import org.railway.utils.Driver;
import org.testng.Assert;

public class HomePage extends BasePage{
    private By welcomeMessage = By.xpath("//div[@class='account']//strong[1]");
    private By titleHomepage = By.xpath("content//h1[@align='center']");
    private By logOutTab = By.xpath("//a[.='Log out']");

    public boolean checkWelcomeMessage(String expectedMessage){
        String actualMessage = Action.getText(Action.find(welcomeMessage));
        return actualMessage.equals(expectedMessage);
    }
    public boolean checkTitleHomepage(String expectedMessage){
        String actualMessage = Action.getText(Action.find(titleHomepage));
        return actualMessage.equals(expectedMessage);
    }

    public boolean checkLogOutTab(){
        try {
            Driver.wait.until(ExpectedConditions.invisibilityOfElementLocated(logOutTab));
            System.out.println("Logout tab is disappeared");
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Logout tab is appeared");
            return false;
        }
    }
}


