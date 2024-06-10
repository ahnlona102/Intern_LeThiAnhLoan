package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.railway.utils.Action;
import org.railway.utils.Driver;

import java.time.Duration;
import java.util.Properties;

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
    protected static Properties properties;


    public void getEmail(String username, String domain) {
        Driver.driver.get(properties.getProperty("mailUrl"));
        Action.click(Action.find(usernametxtbox));
        Action.sendKeys(Action.find(usernameinput), username);
        Action.selectByValue(Action.find(domainselect), domain);
        Action.click(Action.find(setbtn));
        Action.click(Action.find(uncheckbox));
        String getemail = Action.getText(Action.find(emailField));
        System.out.println("Get Email:" + getemail);
    }
    public void RegisterAccount(String email, String password, String confirmpassword, String passport) {
        Driver.driver.switchTo().newWindow(WindowType.TAB);
        Driver.driver.get(properties.getProperty("url"));
        Driver.driver.navigate().refresh();
        Action.sendKeys(Action.find(emailtxtbox), email);
        Action.sendKeys(Action.find(passwordtxtbox), password);
        Action.sendKeys(Action.find(confirmPass), confirmpassword);
        Action.sendKeys(Action.find(passporttxtbox), passport);
        Action.click(Action.find(registerbtn));
    }
    public void confirmEmail() {
        Driver.driver.switchTo().window(Driver.driver.getWindowHandles().iterator().next());
        Driver.driver.navigate().refresh();
        Action.click(Action.find(confirmMess));
        Action.click(Action.find(confirmLink));
    }

}
