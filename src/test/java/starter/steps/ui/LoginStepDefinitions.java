package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.LoginSteps;

public class LoginStepDefinitions {
    @Steps
    LoginSteps login;

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        login.openLoginPage();
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        login.performValidLogin();
    }

    @Then("I should see the dashboard")
    public void iShouldSeeTheDashboard() {
        login.verifySuccessfulLogin();
    }

    @When("I enter invalid credentials \\(incorrect username or password)")
    public void iEnterInvalidCredentialsIncorrectUsernameOrPassword() {
        login.performInvalidLogin();
    }

    @Then("I should see an error message indicating incorrect login credentials")
    public void iShouldSeeAnErrorMessageIndicatingIncorrectLoginCredentials() {
        login.verifyLoginFailed();
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        login.performLogout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        login.verifyLogout();
    }
}
