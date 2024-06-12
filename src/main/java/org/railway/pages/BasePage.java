package org.railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.railway.utils.Action;
import org.railway.utils.ConfigDriver;
import org.railway.utils.Driver;

public class BasePage {
    private static String railway;
    private static String mail;
    private final String tab = "//a[.='%s']";

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

    public static void navigateToRailway() {
        String railwayUrl = ConfigDriver.getProperty("railway.url");
        Driver.driver.get(railwayUrl);
        railway = Driver.driver.getWindowHandle();
    }

    public void clickTab(String tabname) {
        By tabName = By.xpath(String.format(tab, tabname));
        Action.click(Action.find(tabName));
    }

    public boolean checkTab(String nametab){
        By tabName = By.xpath(String.format(tab, nametab));
        Action.find(tabName);
        Action.isElementPresent(tabName);
        if (Action.isElementPresent(tabName)) {
            System.out.println(nametab + " is displayed");
        } else {
            System.out.println(nametab + " is not displayed");
        }
        return false;
    }
}
