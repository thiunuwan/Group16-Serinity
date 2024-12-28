package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import starter.pageobjects.RecruitmentVacanciesPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RecruitmentVacancySteps extends UIInteractionSteps {
    RecruitmentVacanciesPage vacanciesPage;

    @Step("Open the Recruitment Vacancies page")
    public void openVacanciesPage() {
        vacanciesPage.open();
    }
    @Step("Add a new vacancy with valid details")
    public void addNewVacancy() {
//        find(RecruitmentVacanciesPage.ADD_BUTTON).click();
//        find(RecruitmentVacanciesPage.JOB_TITLE_DROPDOWN).selectByVisibleText("Software Engineer");
//        find(RecruitmentVacanciesPage.HIRING_MANAGER_FIELD).sendKeys("John Doe");
//        find(RecruitmentVacanciesPage.NUMBER_OF_POSITIONS_FIELD).sendKeys("3");
//        find(RecruitmentVacanciesPage.SAVE_BUTTON).click();
        vacanciesPage.clickAddVacancy();
    }

    @Step("Verify new vacancy is added")
    public void verifyNewVacancyAdded() {
        withTimeoutOf(Duration.ofSeconds(10))
                .waitForPresenceOf(RecruitmentVacanciesPage.SUCCESS_MESSAGE);
    }


}
