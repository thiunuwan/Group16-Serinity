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

    @When("I search the vacancy named {string}")
    public void iSearchTheVacancyNamed(String name) {
        recruitmentVacancySteps.searchVacancy(name);
    }
}
