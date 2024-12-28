package starter.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.actions.RecruitmentVacancySteps;

public class RecruitmentVacancyStepDefinitions {

    @Steps
    RecruitmentVacancySteps recruitmentVacancies;

    @Given("I navigate to the Recruitment Vacancies page")
    public void iNavigateToTheRecruitmentVacanciesPage() {
        recruitmentVacancies.openVacanciesPage();
    }

    @When("I add a new vacancy with valid details")
    public void iAddANewVacancyWithValidDetails() {
        recruitmentVacancies.addNewVacancy();
    }

    @Then("I should see the new vacancy in the list")
    public void iShouldSeeTheNewVacancyInTheList() {
        recruitmentVacancies.verifyNewVacancyAdded();
    }

}
