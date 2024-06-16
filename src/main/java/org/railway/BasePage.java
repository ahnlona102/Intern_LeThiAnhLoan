package org.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.railway.utils.Action;
import org.railway.utils.ConfigDriver;
import org.railway.utils.Driver;

public class BasePage {

    private static String railway;
    private static String mail;
    private String tab = "//a[.='%s']";
    private String hyperlink = "//a[contains(text(),'%s')]";
    private String title = "//h1[text()='%s']";

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
        By tabName = By.xpath(String.format(tab, nametab));
        boolean isPresent = Action.isElementPresent(tabName);
        if (isPresent) {
            System.out.println(nametab + " is displayed");
        } else {
            System.out.println(nametab + " is not displayed");
        }
        return isPresent;
    }
}
