package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.RecruitmentCandidateActionSteps;

public class RecruitmentCandidateSteps {

    @Steps
    RecruitmentCandidateActionSteps recruitmentCandidateActionSteps;

    @Given("I am in the {string} page")
    public void iAmInThePage(String arg0) {
        recruitmentCandidateActionSteps.openRecruitmentCPage();
    }

    @When("I add a new candidate with firstname {string} and lastname {string} and email {string} and contact {string}")
    public void iAddANewCandidateWithFirstnameAndLastnameAndEmailAndContact(String firstName, String lastName, String email, String contact) {
        recruitmentCandidateActionSteps.addNewCandidate(firstName, lastName, email, contact);
    }

    @Then("I should see the candidate \\(testCandidate) {string} in the candidate list")
    public void iShouldSeeTheCandidateTestCandidateInTheCandidateList(String candidateName) {
        recruitmentCandidateActionSteps.verifyAddingCandidate(candidateName);
    }
}
