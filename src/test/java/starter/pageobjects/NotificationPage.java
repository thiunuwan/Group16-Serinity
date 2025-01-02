package starter.pageobjects;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationPage extends PageObject {

    // Locator for the notification container
    public static final Target NOTIFICATION_CONTAINER = Target.the("Notification container")
            .locatedBy(".oxd-toast-container");

    // Locator for individual notifications inside the container
    public static final Target NOTIFICATIONS = Target.the("Notifications inside the container")
            .locatedBy(".oxd-toast-container .oxd-toast");


    /**
     * Retrieves all the notification texts.
     *
     * @return List of notification texts
     */
    public List<String> getAllNotificationTexts() {
        return findAll(NOTIFICATIONS.getCssOrXPathSelector())
                .stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a specific notification with the expected text exists.
     *
     * @param expectedText The text to search for in notifications
     * @return True if the notification exists, false otherwise
     */
    public boolean containsNotification(String expectedText) {
        return getAllNotificationTexts().stream().anyMatch(text -> text.contains(expectedText));
    }

}
