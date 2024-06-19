package org.railway;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.railway.utils.Action;
import org.railway.utils.ConfigDriver;
import org.railway.utils.Driver;

public class BasePage {

    private static String railway;
    private static String mail;
    private final String tab = "//a[.='%s']";
    private final String hyperlink = "//a[contains(text(),'%s')]";
    private final String title = "//h1[text()='%s']";

    public static void navigateToRailway() {
        String railwayUrl = ConfigDriver.getProperty("railway.url");
        Driver.driver.get(railwayUrl);
        railway = Driver.driver.getWindowHandle();
    }

    public static void switchToRailway() {
        Driver.driver.switchTo().window(railway);
    }

    public static void navigateToMailPage() {
        String mailUrl = ConfigDriver.getProperty("mail.url");
        Driver.driver.get(mailUrl);
        mail = Driver.driver.getWindowHandle();
    }

    public static void switchToEmail() {
        Driver.driver.switchTo().window(mail);
    }

    public static void refreshPage() {
        Driver.driver.navigate().refresh();
    }

    public static void switchToNewTab() {
        Driver.driver.switchTo().newWindow(WindowType.TAB);
    }

    public void clickTab(String tabname) {
        By tabName = By.xpath(String.format(tab, tabname));
        Action.click(tabName);
    }

    public boolean checkTitle(String message) {
        By text = By.xpath(String.format(title, message));
        try {
            WebElement element = Action.find(text);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickHyperlink(String linkName) {
        By hyperlinkName = By.xpath(String.format(hyperlink, linkName));
        Action.click(hyperlinkName);
    }

    public boolean checkTab(String nametab) {
        return Action.find(By.linkText(nametab)).isDisplayed();
    }

    public boolean checkDisappear(String tabName) {
        Action.getWait(20);
        boolean disappear = Action.isDisappear(By.linkText(tabName));
        return disappear;
    }
}
