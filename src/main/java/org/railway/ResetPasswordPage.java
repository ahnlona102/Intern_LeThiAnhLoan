package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class ResetPasswordPage extends BasePage{

    private String field = "//input[@title='%s']";
    private String button = "//input[@value='%s']";
    private String formName = "//legend[text()='%s']";
    private By passToken = By.xpath("//input[@id='resetToken']");

    public void enterField(String fieldName, String value) {
        By fieldLocator = By.xpath(String.format(field, fieldName));
        Action.sendKeys(fieldLocator, value);
    }

    public void clickButton(String buttonName) {
        By buttonLocator = By.xpath(String.format(button, buttonName));
        Action.scroll(buttonLocator);
        Action.click(buttonLocator);
    }

    public boolean checkTitleForm(String title){
        By titleName = By.xpath(String.format(formName, title));
        return Action.isDisplayed(titleName);
    }

    public boolean checkPassToken() {
        return Action.isDisplayed(passToken);
    }

    public void checkTitleFormWithPassToken(String title){
        checkTitleForm(title);
        checkPassToken();
    }

}
