package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

import static org.railway.utils.Driver.driver;

public class BasePage {
    private String tab = "//a[.='%s']";

    public void clickTab(String tabname) {
        By tabName = By.xpath(String.format(tab, tabname));
        Action.click(Action.find(tabName));
    }
}
