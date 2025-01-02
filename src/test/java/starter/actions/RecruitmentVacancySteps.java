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


    @Step("verify  Adding New vacancy")
    public void verifyAddingVacancy(String vacancyName) {
        recruitmentVacanciesPage.open();

        List<String> vacancyList = recruitmentVacanciesPage.getVacancyList();
        System.out.println(vacancyList);
        Assert.assertFalse("vacancy not found in the list!", vacancyList.contains(vacancyName));
    }

    @Step("Search Vacancy")
    public void searchVacancy(String vacancyName) {
        recruitmentVacanciesPage.open();
        recruitmentVacanciesPage.enterNameForSearch(vacancyName);
        recruitmentVacanciesPage.searchVacancy();
    }

}
