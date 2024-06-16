package org.railway;

import org.openqa.selenium.By;
import org.railway.utils.Action;

public class TimetablePage extends BasePage{

    private String checkPricePage = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'TicketPricePage')]";
    private String bookTicketPage = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, 'BookTicketPage')]";

    public void checkPrice(User user) {
        By check = By.xpath(String.format(checkPricePage, user.getDepart(), user.getArrive()));
        Action.clickIfClickable(check);
    }

    public void bookTicket(User user) {
        By book = By.xpath(String.format(bookTicketPage, user.getDepart(), user.getArrive()));
        Action.clickIfClickable(book);
    }
}
