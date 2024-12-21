package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import starter.pageobjects.DashboardPage;
import starter.pageobjects.LoginPage;

import java.time.Duration;

public class LoginSteps extends UIInteractionSteps {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Step("Open the OrangeHRM login page")
    public void openLoginPage() {
        loginPage.open();
        withTimeoutOf(Duration.ofSeconds(10))
                .waitForPresenceOf(LoginPage.LOGIN_BUTTON);
    }

    @Step("Enter valid OrangeHRM credentials")
    public void performValidLogin() {
        find(LoginPage.USERNAME_FIELD).sendKeys("Admin");
        find(LoginPage.PASSWORD_FIELD).sendKeys("admin123");
        find(LoginPage.LOGIN_BUTTON).click();
    }

    @Step("Verify successful login to OrangeHRM dashboard")
    public void verifySuccessfulLogin() {
        withTimeoutOf(Duration.ofSeconds(10))
                .waitForPresenceOf(DashboardPage.DASHBOARD_HEADER);
    }
}
