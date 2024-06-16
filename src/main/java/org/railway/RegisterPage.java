package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class RegisterPage extends BasePage{

    private By emailtxtbox = By.name("email");
    private By passwordtxtbox = By.name("password");
    private By confirmPass = By.id("confirmPassword");
    private By passporttxtbox = By.name("pid");
    private By registerbtn = By.xpath("//input[@value='Register']");
    private By registerErr = By.xpath("//p[@class='message error']");
    private String invalidError = "//label[contains(text(),'%s')]";
    private String text = "//p[contains(text(),'%s')]";

    public void registerAccount(User user) {
        Action.sendKeys(emailtxtbox, user.getEmail());
        Action.sendKeys(passwordtxtbox, user.getPassword());
        Action.sendKeys(confirmPass, user.getConfirmPassword());
        Action.sendKeys(passporttxtbox, user.getPassport());
        clickRegisterButton();
    }

    public void clickRegisterButton() {
        Action.scroll(registerbtn);
        Action.clickIfClickable(registerbtn);
    }

    public boolean checkRegisterError(String expectedMessage) {
        String actualMessage = Action.getText(registerErr);
        return actualMessage.equals(expectedMessage);
    }

    public boolean checkPasswordFieldError(String message) {
        By fieldErr = By.xpath(String.format(invalidError, message));
        return Action.isElementPresent(fieldErr);
    }

    public boolean checkPassportFieldError(String message) {
        By fieldErr = By.xpath(String.format(invalidError, message));
        return Action.isElementPresent(fieldErr);
    }

    public boolean checkMessage(String message) {
        By checkText = By.xpath(String.format(text, message));
        return Action.isDisplayed(checkText);
    }

}

