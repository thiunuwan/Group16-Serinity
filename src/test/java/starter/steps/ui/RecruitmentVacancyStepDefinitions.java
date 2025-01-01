package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.RecruitmentVacancySteps;

public class RecruitmentVacancyStepDefinitions {

    @Steps
    RecruitmentVacancySteps recruitmentVacancySteps;

    @Given("I navigate to the Recruitment Vacancies page")
    public void iNavigateToTheRecruitmentVacanciesPage() {
        recruitmentVacancySteps.openRecruitmentVacanciesPage();
    }

    @When("I add a new vacancy with name {string}, job title {string}, hiring manager {string}, and number of positions {string}")
    public void iAddANewVacancyWithNameJobTitleHiringManagerAndNumberOfPositions(
            String vacancyName, String jobTitle, String hiringManager, String numberOfPositions) {
        recruitmentVacancySteps.addNewVacancy(vacancyName, jobTitle, hiringManager, numberOfPositions);
    }

    @Then("I should see a success message confirming the vacancy was added")
    public void iShouldSeeASuccessMessage() {
        recruitmentVacancySteps.verifySuccessMessage();
    }

    @When("I search for a vacancy with job title {string}, vacancy {string}, hiring manager {string}, and status {string}")
    public void iSearchForAVacancyWithJobTitleVacancyHiringManagerAndStatus(
            String jobTitle, String vacancy, String hiringManager, String status) {
        recruitmentVacancySteps.searchForVacancy(jobTitle, vacancy, hiringManager, status);
    }

    @Then("I should see the vacancy record for {string} displayed in the results")
    public void iShouldSeeTheVacancyRecordDisplayedInTheResults(String vacancyName) {
        recruitmentVacancySteps.verifyVacancyInSearchResults(vacancyName);
    }


    @When("I delete the vacancy named {string}")
    public void iDeleteTheVacancyNamed(String name) {
        recruitmentVacancySteps.deleteVacancy(name);
    }


    @Then("the vacancy {string} should not appear in the vacancy list")
    public void theVacancyShouldNotAppearInTheVacancyList(String name) {
        recruitmentVacancySteps.verifyDeletingVacancy(name);
    }

    @Then("I should see the vacancy \\(testVacancy) {string} in the vacancy list")
    public void iShouldSeeTheVacancyTestVacancyInTheVacancyList(String vacancyName) {
        recruitmentVacancySteps.verifyAddingVacancy(vacancyName);
    }
}
