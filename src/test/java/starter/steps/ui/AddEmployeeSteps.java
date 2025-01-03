package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.AddEmployeeActionSteps;

public class AddEmployeeSteps {

    @Steps
    AddEmployeeActionSteps addEmployeeActionSteps;

    @Given("I am in {string} page")
    public void iAmInThePage(String arg0) {
        addEmployeeActionSteps.openPIMPage();
    }

    @When("I add a new test employee with firstname {string} and middlename {string} and lastname {string}")
    public void iAddANewCandidateWithFirstnameAndMiddlenameAndLastname(String firstName, String middleName, String lastName) {
        addEmployeeActionSteps.addNewEmployee(firstName, middleName, lastName);
    }

    @Then("I should see {string} in the employee list")
    public void iShouldSeeTheCandidateTestCandidateInTheCandidateList(String employeeName) {
        addEmployeeActionSteps.verifyAddingEmployee(employeeName);
    }

    @When("I delete test employee")
    public void iDeleteTestEmployee() {
        addEmployeeActionSteps.deleteTestEmployee();
    }

    @Then("test employee should not appear in the employee list")
    public void theEmployeeShouldNotAppearInTheEmployeeList() {
        addEmployeeActionSteps.verifyEmployeeNotInList();
    }


    @When("I add a new employee with firstname {string} and middlename {string} and lastname {string} and username {string} and password {string} confirmpassword {string}")
    public void iAddANewEmployeeWithFirstnameAndMiddlenameAndLastnameAndUsernameAndPasswordConfirmpassword(String firstName, String middleName, String lastName, String userName, String password, String confirmPassword) {
        addEmployeeActionSteps.addEmployeeWithUsername(firstName, middleName, lastName, userName, password, confirmPassword);
    }
}
