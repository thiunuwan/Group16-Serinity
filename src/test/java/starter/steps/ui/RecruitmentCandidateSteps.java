package starter.steps.ui;

import io.cucumber.java.en.And;
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

    @Then("I should see the notification {string}")
    public void iShouldSeeTheNotification(String text) {
        recruitmentCandidateActionSteps.shouldSeeNotification(text);
    }

    @When("I delete the candidate named {string}")
    public void iDeleteTheCandidateNamed(String name) {
        recruitmentCandidateActionSteps.deleteCandidate(name);
    }

    @Then("the candidate {string} should not appear in the candidate list")
    public void theCandidateShouldNotAppearInTheCandidateList(String name) {
        recruitmentCandidateActionSteps.verifyDeletingCandidate(name);
    }

    @When("I search the candidate named {string}")
    public void iSearchTheCandidateNamed(String name) {
        recruitmentCandidateActionSteps.searchCandidate(name);
    }


    @And("I add a new candidate with firstname {string} and lastname {string} and email {string} and contact {string} and consentCheckbox {string}")
    public void iAddANewCandidateWithFirstnameAndLastnameAndEmailAndContactAndConsentCheckbox(String firstName, String lastName, String email, String contact, String checkbox) {
            boolean enableConsentCheckbox = Boolean.parseBoolean(checkbox);
            recruitmentCandidateActionSteps.addNewCandidateWithConsent(firstName, lastName, email, contact,enableConsentCheckbox);

    }

}
