package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.railway.utils.Action;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private By emailtxtbox = By.id("username");
    private By passwordtxtbox = By.id("password");
    private By loginbtn = By.xpath("//input[@value='login']");
    private By loginErr = By.xpath("//p[contains(@class,'message error')]");

    public void login(String email, String password) {
        Action.sendKeys(Action.find(emailtxtbox), email);
        Action.sendKeys(Action.find(passwordtxtbox), password);
        clickLoginButton();
    }
    public void clickLoginButton(){
        WebElement button = Action.find(loginbtn);
        Action.scroll(button);
        Action.clickBeAble(button);
    }

    public void clearEmailField(){
        Action.clearField(Action.find(emailtxtbox));
    }

    public void enterPassword(String password){
        Action.sendKeys(Action.find(passwordtxtbox), password);
    }

    public boolean checkLoginErr(String expectedMessage) {
        String actualMessage = Action.getText(Action.find(loginErr));
        return actualMessage.equals(expectedMessage);
    }

}