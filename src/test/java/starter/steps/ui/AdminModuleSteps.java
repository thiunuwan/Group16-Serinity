package starter.steps.ui;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import starter.actions.AdminPageSteps;


public class AdminModuleSteps {

    Faker faker = new Faker();
    @Steps
    AdminPageSteps adminPage;

    @Given("I am on the {string} page")
    public void iAmOnThePage(String arg0) {
        adminPage.openAdminPage();
    }

    @When("I add a new user with username and password {string} and role {string}")
    public void iAddANewUserWithUsernameAndPassword( String password,String role) {
        // Generate a unique username using Faker
        String username =  faker.name().firstName() + faker.number().digits(4);
        // Set the username into Serenity session variables
        Serenity.setSessionVariable("test-user-username").to(username);

        System.out.println("Log :- Username:" + username);
        adminPage.addNewTestUser(username,password,role);
    }
    @When("I search the user named {string}")
    public void iSearchTheUserNamed(String name) {
        adminPage.searchUser(name);
    }

    @Then("I should see the added user in the user list")
    public void iShouldSeeTheUserTestUserInTheUserList() {
        String username = Serenity.sessionVariableCalled("test-user-username");
        System.out.println("Log :- username from session var: " +username);
        adminPage.verifyAddingTestUser(username);
    }

    @And("the user test exists with username {string} and password {string} and role {string}")
    public void theUserTestExistsWithUsernameAndPasswordAndRole(String username, String password, String role) {
        adminPage.addNewTestUser(username,password,role);
    }

    @When("I delete the user with username {string}")
    public void iDeleteTheUserWithUsername(String username) {
        adminPage.deleteTestUser(username);
    }

    @Then("I should not see the user \\(testUser) {string} in the user list")
    public void iShouldNotSeeTheUserTestUserInTheUserList(String username) {
        adminPage.verifyDeletingTestUser(username);

    }


    @When("I try to add a new user with same username {string} and password {string} and role {string}")
    public void iTryToAddANewUserWithUsernameAndPasswordAndRole(String username, String password, String role) {
        adminPage.addNewTestUser(username,password,role);
    }

    @When("I add a new user with username {string} and password {string} and role {string}")
    public void iAddANewUserWithUsernameAndPassword(String username, String password,String role) {
        adminPage.addNewTestUser(username,password,role);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String arg0) {
    }

    @Then("I should see the user \\(testUser) {string} in the search result list")
    public void iShouldSeeTheUserTestUserInTheSearchResultList(String username) {
        adminPage.verifySearchingTestUser(username);
    }
    @Then("I should see the user \\(testUser) {string} in the user list")
    public void iShouldSeeTheUserTestUserInTheUserList(String username) {
        adminPage.verifyAddingTestUser(username);
    }

    @When("I update a user with username {string} to {string} and role {string} to {string}")
    public void iUpdateAUserWithUsernameToAndPasswordToAndRoleTo(String oldUserName, String newUserName, String oldRole, String newRole) {
        adminPage.updateTestUser(oldUserName,newUserName,oldRole,newRole);
    }
}
