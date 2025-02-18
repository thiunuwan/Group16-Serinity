package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class DashboardPage extends PageObject {
    public static final By DASHBOARD_HEADER = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module') and text()='Dashboard']");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@href='/web/index.php/auth/logout']");
    private static final By USER_DROPDOWN = By.cssSelector(".oxd-userdropdown-icon.bi-caret-down-fill");

    public void openUserDropdown() {
        $(USER_DROPDOWN).click();
        waitABit(2000);

    }

    public void clickLogout() {
        $(LOGOUT_BUTTON).click();

        waitABit(2000);

    }
}

