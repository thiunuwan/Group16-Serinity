package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class DashboardPage extends PageObject {
    public static final By DASHBOARD_HEADER = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module') and text()='Dashboard']");
}

