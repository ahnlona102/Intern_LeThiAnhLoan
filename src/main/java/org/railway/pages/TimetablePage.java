package org.railway.pages;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class TimetablePage {
    private final String checkPricePage = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'TicketPricePage')]";
    private final String bookTicketPage = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'BookTicketPage')]";

    public void checkPrice(String departStation, String arriveStation) {
        By check = By.xpath(String.format(checkPricePage, departStation, arriveStation));
        Action.click(Action.find(check));
    }

    public void bookTicket(String departStation, String arriveStation) {
        By book = By.xpath(String.format(bookTicketPage, departStation, arriveStation));
        Action.click(Action.find(book));
    }
}
