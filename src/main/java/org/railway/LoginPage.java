package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class LoginPage extends BasePage {

    private By emailtxtbox = By.id("username");
    private By passwordtxtbox = By.id("password");
    private By loginbtn = By.xpath("//input[@value='login']");
    private By loginErr = By.xpath("//p[contains(@class,'message error')]");



    public void login(User user) {
        Action.sendKeys(emailtxtbox, user.getEmail());
        Action.sendKeys(passwordtxtbox, user.getPassword());
        clickLoginButton();
    }

    public void clickLoginButton() {
        Action.scroll(loginbtn);
        Action.clickIfClickable(loginbtn);
    }

    public void clearEmailField() {
        Action.clearField(emailtxtbox);
    }

    public void enterPassword(User user) {
        Action.sendKeys(passwordtxtbox, user.getPassword());
    }

    public boolean checkLoginErr(String expectedMessage) {
        String actualMessage = Action.getText(loginErr);
        return actualMessage.equals(expectedMessage);
    }


}