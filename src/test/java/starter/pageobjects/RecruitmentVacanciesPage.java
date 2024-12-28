package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewJobVacancy")
public class RecruitmentVacanciesPage extends PageObject {
//    public static final By VACANCIES_TABLE = By.id("vacanciesTable");
//    public static final By ADD_BUTTON = By.id("btnAdd");
//    public static final By JOB_TITLE_DROPDOWN = By.id("jobTitle");
//    public static final By HIRING_MANAGER_FIELD = By.id("hiringManager");
//    public static final By NUMBER_OF_POSITIONS_FIELD = By.id("numOfPositions");
//    public static final By SAVE_BUTTON = By.id("btnSave");
//    public static final By SUCCESS_MESSAGE = By.cssSelector(".success");
//    public static final By SEARCH_JOB_TITLE_FIELD = By.id("searchJobTitle");
//    public static final By SEARCH_BUTTON = By.id("btnSearch");
//    public static final By SEARCH_RESULTS_TABLE = By.id("searchResultsTable");

    public static final By ADD_BUTTON = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    public static final By JOB_TITLE_DROPDOWN = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]");
    public static final By HIRING_MANAGER_FIELD = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div[1]");
    public static final By NUMBER_OF_POSITIONS_FIELD = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div[2]");
    public static final By SAVE_BUTTON = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[7]/button[2]");
    public static final By SUCCESS_MESSAGE = By.cssSelector(".success");



    public void clickAddVacancy() {

        $(ADD_BUTTON).click();
    }




}


