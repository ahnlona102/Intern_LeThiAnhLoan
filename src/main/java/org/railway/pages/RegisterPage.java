package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;
import org.railway.utils.Driver;

import java.time.Duration;

public class RegisterPage {

    private final By usernametxtbox = By.xpath("//span[@id='inbox-id']");
    private final By usernameinput = By.xpath("//span[@title='Click to Edit']//input[1]");
    private final By domainselect = By.id("gm-host-select");
    private final By setbtn = By.xpath("//span[@id='inbox-id']//button[1]");
    private final By uncheckbox = By.xpath("(//input[@type='checkbox'])[1]");
    private final By emailField = By.id("email-widget");
    private final By confirmMess = By.xpath("//td[contains(text(), 'Please confirm')]");
    private final By confirmLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private final By emailtxtbox = By.name("email");
    private final By passwordtxtbox = By.name("password");
    private final By confirmPass = By.id("confirmPassword");
    private final By passporttxtbox = By.name("pid");
    private final By registerbtn = By.cssSelector("p.form-actions>input");

    public void getEmail(String username, String domain) {
        Driver.driver.get("https://www.guerrillamail.com/inbox");
        Action.click(Action.find(usernametxtbox));
        Action.sendKeys(Action.find(usernameinput), username);
        Action.selectByValue(Action.find(domainselect), domain);
        Action.click(Action.find(setbtn));
        Action.click(Action.find(uncheckbox));
        String email = Action.getText(Action.find(emailField));
        System.out.println("Get Email:" + email);
    }

    public void RegisterAccount(String email, String password, String confirmpassword, String passport) {
        Action.sendKeys(Action.find(emailtxtbox), email);
        Action.sendKeys(Action.find(passwordtxtbox), password);
        Action.sendKeys(Action.find(confirmPass), confirmpassword);
        Action.sendKeys(Action.find(passporttxtbox), passport);
        Action.click(Action.find(registerbtn));
    }

    public void confirmEmail(String email) {
        Driver.driver.get("https://www.guerrillamail.com/inbox");
        Driver.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        Action.click(Action.find(confirmMess));
        Action.click(Action.find(confirmLink));
    }
}
