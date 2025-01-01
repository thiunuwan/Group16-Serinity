package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.RecruitmentVacanciesPage;

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

    @Step("Search for a vacancy")
    public void searchForVacancy(String jobTitle, String vacancyName, String hiringManager, String status) {
        recruitmentVacanciesPage.selectJobTitleForSearch(jobTitle);
        recruitmentVacanciesPage.selectVacancyForSearch(vacancyName);
        recruitmentVacanciesPage.selectHiringManagerForSearch(hiringManager);
        recruitmentVacanciesPage.selectStatusForSearch(status);
        recruitmentVacanciesPage.clickSearchButton();
    }

    @Step("Verify vacancy is displayed in search results")
    public void verifyVacancyInSearchResults(String vacancyName) {
        Assert.assertTrue("Vacancy not found in search results!",
                recruitmentVacanciesPage.isVacancyDisplayedInSearchResults(vacancyName));
    }

    @Step("Delete vacancy")
    public void deleteVacancy(String vacancyName) {
        recruitmentVacanciesPage.open();
        recruitmentVacanciesPage.deleteVacancy(vacancyName);

    }

    @Step("verify  the deletion of vacancy")
    public void verifyDeletingVacancy(String vacancyName) {
        List<String> vacancyList = recruitmentVacanciesPage.getVacancyList();
        System.out.println(vacancyList);
        Assert.assertFalse("vacancy  found in the list!", vacancyList.contains(vacancyName));
    }


    }
}
