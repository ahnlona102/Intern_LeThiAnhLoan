package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class LoginPage extends BasePage {

    private final By emailtxtbox = By.id("username");
    private final By passwordtxtbox = By.id("password");
    private final By loginbtn = By.xpath("//input[@value='login']");
    private final By loginErr = By.xpath("//p[contains(@class,'message error')]");

    public void login(User user) {
        enterEmail(user);
        enterPassword(user);
        clickLoginButton();
    }

    public void loginManyTimes(User user, int number) {
        for (int i = 0; i < number - 1; i++) {
            login(user);
        }
    }

    public void enterEmail(User user) {
        Action.sendKeys(emailtxtbox, user.getEmail());
    }

    public void enterPassword(User user) {
        Action.sendKeys(passwordtxtbox, user.getPassword());
    }

    public void clickLoginButton() {
        Action.scroll(loginbtn);
        Action.click(loginbtn);
    }

    public boolean checkLoginErr(String expectedMessage) {
        String actualMessage = Action.getText(loginErr);
        return actualMessage.equals(expectedMessage);
    }
}
