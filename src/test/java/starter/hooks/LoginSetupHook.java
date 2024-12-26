package starter.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pageobjects.LoginPage;

public class LoginSetupHook extends UIInteractionSteps {
    LoginPage loginPage;

    @Before("@loginAsAdmin")
    public void performLoginBeforeUITests() {
        loginPage.open(); // Ensure the page is opened

        // Wait for 2 seconds
        waitABit(2000);

        // Perform login
        find(LoginPage.USERNAME_FIELD).sendKeys("Admin");
        find(LoginPage.PASSWORD_FIELD).sendKeys("admin123");
        find(LoginPage.LOGIN_BUTTON).click();

    }

}
