package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.AdminPageSteps;


public class AdminModuleSteps {

    @Steps
    AdminPageSteps adminPage;

    @Given("I am on the {string} page")
    public void iAmOnThePage(String arg0) {
        adminPage.openAdminPage();
    }

    @When("I add a new user with username {string} and password {string} and role {string}")
    public void iAddANewUserWithUsernameAndPassword(String username, String password,String role) {
        adminPage.addNewTestUser(username,password,role);
    }



    @Then("I should see the user \\(testUser) {string} in the user list")
    public void iShouldSeeTheUserTestUserInTheUserList(String username) {
        adminPage.verifyAddingTestUser(username);
    }

}

