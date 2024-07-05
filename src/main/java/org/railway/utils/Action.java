package org.railway.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {
    public static void click(By element) {
        find(element).click();
    }

    public static WebElement find(By element) {
        return DriverManager.getDriver().findElement(element);
    }


    public static void getWait(int time) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(time));
    }

    public static boolean isDisplayed(By element) {
        try {
            find(element);
            waitForElementVisibility(element);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public static void waitForElementVisibility(By element) {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitForElementVisibilityOf(WebElement element) {
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waiForElementClickable(By element) {
        WebElement webElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(element));
        return webElement;
    }

    public static void waiForElementInvisibility(By element) {
        DriverManager.getWait().until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public static void waitForElementPresent(By element) {
        DriverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static String getText(By element) {
        return find(element).getText();
    }

    public static void enter(By element, String text) {
        WebElement webElement = find(element);
        scroll(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static void selectByValue(By element, String value) {
        WebElement webElement = find(element);
        Select select = new Select(webElement);
        select.selectByValue(value);
    }

    public static void selectByVisibleText(By element, String text) {
        getWait(20);
        WebElement webElement = find(element);
        waitForElementVisibilityOf(webElement);
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public static void clearField(By element) {
        find(element).clear();
    }

    public static void scroll(By element) {
        WebElement webElement = find(element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static WebElement clickIfClickable(By element) {
        getWait(30);
        waitForElementVisibility(element);
        waiForElementClickable(element).click();
        return waiForElementClickable(element);
    }
    public static boolean isDisappear(By element) {
        try {
            waiForElementInvisibility(element);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
    public static void acceptAlert() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }

}
