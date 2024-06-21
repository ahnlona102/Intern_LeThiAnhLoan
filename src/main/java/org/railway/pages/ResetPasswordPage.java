package org.railway.pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.railway.enums.ResetPassword;
import org.railway.utils.Action;

public class ResetPasswordPage extends BasePage{

    private String fieldLocator = "//input[@title='%s']";
    private String formNameLocator = "//legend[text()='%s']";
    private By passTokenLocator = By.xpath("//input[@id='resetToken']");
    private By messageAboveForm = By.xpath("//p[contains(@class,'message')]");
    private By messageNextConfirmPassword = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    public void enterField(ResetPassword fieldName, String value) {
        By fieldLocate = By.xpath(String.format(fieldLocator, fieldName.getValue()));
        Action.enter(fieldLocate, value);
    }

    public void clickButton(ResetPassword buttonName) {
        By buttonLocator = By.xpath(String.format(fieldLocator, buttonName.getValue()));
        Action.scroll(buttonLocator);
        Action.click(buttonLocator);
    }

    public boolean isTitleFormDisplayed(String title){
        By titleName = By.xpath(String.format(formNameLocator, title));
        return Action.isDisplayed(titleName);
    }

    public String getResetToken() {
        return Action.find(passTokenLocator).getAttribute("value");
    }

    public String getMessageAboveForm() {
        return Action.getText(messageAboveForm);
    }

    public String getMessageNextConfirmPassword() {
        return Action.getText(messageNextConfirmPassword);
    }
}
