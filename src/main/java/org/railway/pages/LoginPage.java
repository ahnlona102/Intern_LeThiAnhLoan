package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class LoginPage extends BasePage{

    private By emailtxtbox = By.id("username");
    private By passwordtxtbox = By.id("password");
    private By loginbtn = By.xpath("//input[@value='login']");

    public void login(String email, String password) {
        Action.sendKeys(Action.find(emailtxtbox), email);
        Action.sendKeys(Action.find(passwordtxtbox), password);
        Action.click(Action.find(loginbtn));
    }
}
