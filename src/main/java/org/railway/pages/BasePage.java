package org.railway.pages;

import org.openqa.selenium.*;
import org.railway.enums.NameHyperlink;
import org.railway.utils.Action;
import org.railway.utils.ConfigLoader;
import org.railway.enums.RailwayTab;
import org.railway.utils.DriverManager;

public class BasePage {

    private static String railway;
    private static String mail;
    private final String tabLocator = "//a[.='%s']";
    private final String hyperlinkLocator = "//a[contains(text(),'%s')]";
    private By titleLocator = By.xpath("//h1[@align='center']");

    public static void navigateToRailway() {
        String railwayUrl = ConfigLoader.getProperty("railway.url");
        DriverManager.getDriver().get(railwayUrl);
        railway = DriverManager.getDriver().getWindowHandle();
    }

    public static void switchToRailway() {
        DriverManager.getDriver().switchTo().window(railway);
    }

    public static void navigateToMailPage() {
        String mailUrl = ConfigLoader.getProperty("mail.url");
        DriverManager.getDriver().get(mailUrl);
        mail = DriverManager.getDriver().getWindowHandle();
    }

    public static void switchToEmail() {
        DriverManager.getDriver().switchTo().window(mail);
    }

    public static void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
    }

    public static void switchToNewTab() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public void clickTab(RailwayTab tabname) {
        By tabName = By.xpath(String.format(tabLocator, tabname.getValue()));
        Action.click(tabName);
    }

    public boolean isTitleDisplay(String message) {
        String actual = Action.getText(titleLocator);
        return actual.equals(message);
    }

    public void clickHyperlinkByName(NameHyperlink linkName) {
        By hyperlinkName = By.xpath(String.format(hyperlinkLocator, linkName.getValue()));
        Action.click(hyperlinkName);
    }

    public boolean isTabDisplayed(RailwayTab nametab) {
        return Action.find(By.linkText(nametab.getValue())).isDisplayed();
    }

    public boolean doesTabExist(RailwayTab tabName) {
        Action.getWait(20);
        boolean disappear = Action.isDisappear(By.linkText(tabName.getValue()));
        return disappear;
    }
}
