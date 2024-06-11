package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class EmailPage {

    private final By usernametxtbox = By.xpath("//span[@id='inbox-id']");
    private final By usernameinput = By.xpath("//span[@title='Click to Edit']//input[1]");
    private final By domainselect = By.id("gm-host-select");
    private final By setbtn = By.xpath("//span[@id='inbox-id']//button[1]");
    private final By uncheckbox = By.xpath("(//input[@type='checkbox'])[1]");
    private final By emailField = By.id("email-widget");
    private final By confirmMess = By.xpath("//td[contains(text(), 'Please confirm')]");
    private final By confirmLink = By.xpath("//a[contains(@href, 'saferailway')]");

    public String getEmail(String username, String domain) {
        Action.click(Action.find(usernametxtbox));
        Action.sendKeys(Action.find(usernameinput), username);
        Action.selectByValue(Action.find(domainselect), domain);
        Action.click(Action.find(setbtn));
        Action.click(Action.find(uncheckbox));
        String getemail = Action.getText(Action.find(emailField));
        System.out.println("Get Email:" + getemail);
        return getemail;
    }

    public void confirmEmail() {
        Action.click(Action.find(confirmMess));
        Action.click(Action.find(confirmLink));
    }
}
