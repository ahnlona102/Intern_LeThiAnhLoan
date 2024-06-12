package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.railway.utils.Action;

import java.util.Properties;

public class RegisterPage {

    protected static Properties properties;
    private final By emailtxtbox = By.name("email");
    private final By passwordtxtbox = By.name("password");
    private final By confirmPass = By.id("confirmPassword");
    private final By passporttxtbox = By.name("pid");
    private final By registerbtn = By.cssSelector("p.form-actions>input");
    private final By registerErr = By.xpath("//p[@class='message error']");
    private final String invalidError = "//label[contains(text(),'%s')]";

    public void RegisterAccount(String email, String password, String confirmpassword, String passport) {
        Action.sendKeys(Action.find(emailtxtbox), email);
        Action.sendKeys(Action.find(passwordtxtbox), password);
        Action.sendKeys(Action.find(confirmPass), confirmpassword);
        Action.sendKeys(Action.find(passporttxtbox), passport);
        clickRegisterButton();
    }

    public void clickRegisterButton() {
        WebElement button = Action.find(registerbtn);
        Action.scroll(button);
        Action.clickBeAble(button);
    }

    public boolean checkRegisterErr(String expectedMessage) {
        String actualMessage = Action.getText(Action.find(registerErr));
        return actualMessage.equals(expectedMessage);
    }

    public boolean checkpasswordFieldErr(String message) {
        By fieldErr = By.xpath(String.format(invalidError, message));
        return Action.isElementPresent(fieldErr);
    }

    public boolean checkpassportFieldErr(String message) {
        By fieldErr = By.xpath(String.format(invalidError, message));
        return Action.isElementPresent(fieldErr);
    }

}
