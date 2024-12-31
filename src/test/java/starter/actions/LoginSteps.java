package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import starter.pageobjects.DashboardPage;
import starter.pageobjects.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

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

    @Step("Enter Invalid login credentials")
    public void performInvalidLogin() {
        find(LoginPage.USERNAME_FIELD).sendKeys("Admin");
        find(LoginPage.PASSWORD_FIELD).sendKeys("admin333");
        find(LoginPage.LOGIN_BUTTON).click();
    }

    @Step("Verify login failure and error message display")
    public void verifyLoginFailed() {
        withTimeoutOf(Duration.ofSeconds(20))
                .waitForPresenceOf(LoginPage.ERROR_MESSAGE);

        // Verify the error message text
        String actualErrorMessage = find(LoginPage.ERROR_MESSAGE).getText();
        assertEquals("Invalid credentials", actualErrorMessage);  // Assert the error message
    }

    public void performLogout() {
        dashboardPage.openUserDropdown();
        dashboardPage.clickLogout();
    }

    public void verifyLogout() {
        String currentUrl = getDriver().getCurrentUrl();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", currentUrl);
    }
}
