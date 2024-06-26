package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.enums.ResetPassword;
import org.railway.enums.Title;
import org.railway.models.User;
import org.railway.utils.Action;

public class ResetPasswordPage extends BasePage{

    private String fieldLocator = "//input[@title='%s']";
    private String formNameLocator = "//legend[contains(text(),'%s')]";

    private By passTokenLocator = By.xpath("//input[@id='resetToken']");
    private By messageAboveForm = By.xpath("//p[contains(@class,'message')]");
    private By messageNextConfirmPassword = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    public void enterField(ResetPassword fieldName, String value) {
        By fieldLocate = By.xpath(String.format(fieldLocator, fieldName.getValue()));
        Action.scroll(fieldLocate);
        Action.enter(fieldLocate, value);
    }

    public void clickButton(ResetPassword buttonName) {
        By buttonLocator = By.xpath(String.format(fieldLocator, buttonName.getValue()));
        Action.scroll(buttonLocator);
        Action.click(buttonLocator);
    }

    public boolean isTitleFormDisplayed(Title name){
        By titleName = By.xpath(String.format(formNameLocator, name));
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
