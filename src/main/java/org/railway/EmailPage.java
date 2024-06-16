package org.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.railway.utils.Action;
import org.railway.utils.Driver;

public class EmailPage {

    private By usernametxtbox = By.xpath("//span[@id='inbox-id']");
    //private String userInput = "//input[@value='%s']";
    private String usertxtbox = "//span[@id='inbox-id']//input[@type='text']";
    private By domainselect = By.id("gm-host-select");
    private By setbtn = By.xpath("//span[@id='inbox-id']//button[1]");
    private By uncheckbox = By.xpath("(//input[@type='checkbox'])[1]");
    private By emailField = By.id("email-widget");
    private By confirmLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private String confirmMess = "//td[contains(text(), '%s')]";

    public String getEmail(User user) {
        Action.clickIfClickable(usernametxtbox);
        enterUsername(user);
        selectDomain(user);
        Action.click(setbtn);
        Action.click(uncheckbox);
        String getemail = Action.getText(emailField);
        System.out.println("Get Email: " + getemail);
        return getemail;
    }

    public void enterUsername(User user) {
        String xpath = String.format(usertxtbox, user.getUsername());
        WebElement mailPath = Action.find(By.xpath(xpath));
        mailPath.sendKeys(user.getUsername());
    }

    public void selectDomain(User user) {
        Action.selectByValue(domainselect, user.getDomain());
    }

    public void clickSetBtn() {
        Action.click(setbtn);
    }

    public void setMail(User user) {
        enterUsername(user);
        selectDomain(user);
        clickSetBtn();
        Action.getWait(20);
    }

    public void checkConfirmMess(String text) {
        By mess = By.xpath(String.format(confirmMess, text));
        Action.clickIfClickable(mess);
    }

    public void clickConfirmLink() {
        Action.clickIfClickable(confirmLink);
    }

    public void confirmEmail(String text) {
        Action.getWait(20);
        checkConfirmMess(text);
        clickConfirmLink();
    }
}
