package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.AddEmployeeActionSteps;
import starter.actions.RecruitmentCandidateActionSteps;

public class AddEmployeeSteps {

    @Steps
    AddEmployeeActionSteps addEmployeeActionSteps;

    @Given("I am in {string} page")
    public void iAmInThePage(String arg0) {
        addEmployeeActionSteps.openPIMPage();
    }

    @When("I add a new employee with firstname {string} and middlename {string} and lastname {string} and employeeId {string}")
    public void iAddANewCandidateWithFirstnameAndLastnameAndEmailAndContact(String firstName, String lastName, String middleName, String userId) {
        addEmployeeActionSteps.addNewEmployee(firstName, lastName, middleName, userId);
    }

    @Then("I should see {string} in the employee list")
    public void iShouldSeeTheCandidateTestCandidateInTheCandidateList(String candidateName) {
        addEmployeeActionSteps.verifyAddingCandidate(candidateName);
    }


}
