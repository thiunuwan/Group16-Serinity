package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.JobTitlesActionsSteps;
public class JobTitlesSteps {

    @Steps
    JobTitlesActionsSteps jobTitlesActionsSteps;

    @Given("I am in the {string} page")
    public void iAmInThePage(String arg0) {
        jobTitlesActionsSteps.openJobTitlePage();
    }

    @When("I add a new job title with job title {string}")
    public void iAddANewJobTitle(String jobTitle) {
        jobTitlesActionsSteps.addNewJobTitle(jobTitle);
    }

    @Then("I should see the job title \\(testJobTitle) {string} in the job titles list")
    public void iShouldSeeTheJobTitleTestJobTitleInTheJobTitlesList(String jobTitle) {
        jobTitlesActionsSteps.verifyAddingJobTitle(jobTitle);
    }


    @When("I delete the job title named {string}")
    public void iDeleteTheJobTitleNamed(String jobTitle) {
        jobTitlesActionsSteps.deleteJobTitle(jobTitle);
    }

    @Then("the job title {string} should not appear in the job titles list")
    public void theJobTitleShouldNotAppearInTheJobTitleList(String jobTitle) {
        jobTitlesActionsSteps.verifyDeletingJobTitle(jobTitle);
    }


    @When("I edit the existing job title named {string} to {string}")
    public void iEditTheExistingJobTitleNamed(String jobTitle, String updatedTitle) {
        jobTitlesActionsSteps.editJobTitle(jobTitle,updatedTitle);
    }
}
