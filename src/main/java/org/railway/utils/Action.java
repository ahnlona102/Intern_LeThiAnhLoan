package org.railway.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {
    public static void click(By element) {
        WebElement webElement = Driver.driver.findElement(element);
        scroll(element);
        webElement.click();
    }

    public static WebElement find(By element) {
        return Driver.driver.findElement(element);
    }


    public static void getWait(int time) {
        Driver.wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(time));
    }

    public static boolean isDisplayed(By element) {
        try {
            WebElement elements = find(element);
            Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return elements.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public static String getText(By element) {
        return Driver.driver.findElement(element).getText();
    }

    public static void sendKeys(By element, String text) {
        WebElement webElement = Driver.driver.findElement(element);
        scroll(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static void selectByValue(By element, String value) {
        WebElement webElement = Driver.driver.findElement(element);
        Select select = new Select(webElement);
        select.selectByValue(value);
    }

    public static void selectByVisibleText(By element, String text) {
        WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(20)); // Increased timeout
        WebElement webElement = Driver.driver.findElement(element);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public static void clearField(By element) {
        Driver.driver.findElement(element).clear();
    }

    public static void scroll(By element) {
        WebElement webElement = Driver.driver.findElement(element);
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static WebElement clickIfClickable(By element) {
        WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(30));
        Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        webElement.click();
        return webElement;
    }

    public static boolean isDisappear(By element){
        try {
            Driver.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

}
