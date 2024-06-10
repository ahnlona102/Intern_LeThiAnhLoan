package org.railway.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void initialize(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

}

