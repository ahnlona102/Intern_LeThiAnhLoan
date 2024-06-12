package org.railway.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Action {
    public static void click(WebElement element) {
        element.click();
    }

    public static WebElement find(By element) {
        return Driver.driver.findElement(element);
    }

    public static boolean isElementPresent(By element) {
        try {
            Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static void clearField(WebElement element){
        element.clear();
    }

    public static void scroll(WebElement element) {
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static WebElement clickBeAble(WebElement element) {
        Driver.initialize();
        Driver.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return element;
    }
}
