package starter.pageobjects;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;

public class PageUtils extends PageObject {

    public void fillInputField(By inputField, String value) {
        if ($(inputField).waitUntilVisible().isEnabled()) {
            $(inputField).type(value);
        } else {
            System.out.println(inputField + " is not enabled.");
        }
    }

    public void clickButton(By button) {
        if ($(button).waitUntilClickable().isEnabled()) {
            $(button).click();
        } else {
            System.out.println(button + " is not enabled.");
        }
    }
}
