package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.RecruitmentVacanciesPage;

import java.util.List;

public class RecruitmentVacancySteps extends UIInteractionSteps {

    RecruitmentVacanciesPage recruitmentVacanciesPage;

    @Step("Open the Recruitment Vacancies page")
    public void openRecruitmentVacanciesPage() {
        recruitmentVacanciesPage.open();
    }

    @Step("Add a new vacancy")
    public void addNewVacancy(String vacancyName, String jobTitle, String hiringManager, String numberOfPositions) {
        recruitmentVacanciesPage.clickAddVacancy();
        recruitmentVacanciesPage.enterVacancyName(vacancyName);
        recruitmentVacanciesPage.selectJobTitle(jobTitle);
        recruitmentVacanciesPage.enterHiringManager(hiringManager);
        recruitmentVacanciesPage.enterNumberOfPositions(numberOfPositions);
        recruitmentVacanciesPage.clickSaveButton();
    }

    @Step("Verify success message is displayed")
    public void verifySuccessMessage() {
        Assert.assertTrue("Success message not displayed!", recruitmentVacanciesPage.isSuccessMessageDisplayed());
    }


}
