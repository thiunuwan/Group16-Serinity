package starter.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.actions.LoginSteps;
import starter.pageobjects.LoginPage;

public class LoginSetupHook extends UIInteractionSteps {


    LoginPage loginPage;
    LoginSteps loginSteps;

    @Before("@loginAsAdmin")
    public void performLoginBeforeUITests() {
        loginPage.open(); // Ensure the page is opened
        loginSteps.performValidLogin();

    }

    @After("@loginAsAdmin")
    public void performLogoutAfterUITests() {
        loginSteps.performLogout();
        waitABit(1000);
    }

}
