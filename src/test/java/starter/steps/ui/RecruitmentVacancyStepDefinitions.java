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


}