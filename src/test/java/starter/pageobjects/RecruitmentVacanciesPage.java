package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;




import java.util.List;
import java.util.stream.Collectors;


@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewJobVacancy")
public class RecruitmentVacanciesPage extends PageObject {

    // Elements for "Add a new vacancy" scenario
    private final By addVacancyButton = By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]");
    private final By vacancyNameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/div[2]/input");
    private final By jobTitleDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div/div");
    private final By dropdownOptions = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By hiringManagerInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div[1]/div/div[2]/div/div/input");
    private final By numberOfPositionsInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div[2]/div/div/div/div[2]/input");
    private final By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/form/div[7]/button[2]");
    private final By successMessage = By.xpath("/html/body/div/div[2]");

    // Elements for "Search for a vacancy" scenario
    private final By searchJobTitleDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div");
    private final By dropdownOptionsSearchJobTitle = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By searchVacancyDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div");
    private final By dropdownOptionsSearchVacancy = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By searchHiringManagerDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div");
    private final By dropdownOptionsSearchManager = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By searchStatusDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div");
    private final By dropdownOptionsSearchStatus = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private final By searchResultsTable = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div/div/div");

    private final By deleteButtonTemplate = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div/div[1]/div[2]/div/div/button[1]");

    // Methods for "Add a new vacancy" scenario

    public void clickAddVacancy() {
        $(addVacancyButton).click();
        pause(2000);
    }

    public void enterVacancyName(String vacancyName) {
        $(vacancyNameInput).type(vacancyName);
        pause(2000);
    }

    public void selectJobTitle(String jobTitle) {
        $(jobTitleDropdown).click();
        List<WebElementFacade> options = findAll(dropdownOptions);
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().equalsIgnoreCase(jobTitle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Job title not found: " + jobTitle));
        option.click();
    }

    public void enterHiringManager(String hiringManager) {
        $(hiringManagerInput).typeAndEnter(hiringManager);
        pause(2000);
    }

    public void enterNumberOfPositions(String numberOfPositions) {
        $(numberOfPositionsInput).type(numberOfPositions);
        pause(2000);
    }

    public void clickSaveButton() {
        $(saveButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccessMessageDisplayed() {
        return $(successMessage).isDisplayed();
    }

    // Methods for "Search for a vacancy" scenario
    public void selectJobTitleForSearch(String jobTitle) {
        $(searchJobTitleDropdown).click();
        List<WebElementFacade> options = findAll(dropdownOptionsSearchJobTitle);
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().equalsIgnoreCase(jobTitle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Job title not found: " + jobTitle));
        option.click();
    }


    public void selectVacancyForSearch(String vacancyName) {
        $(searchVacancyDropdown).click();
        List<WebElementFacade> options = findAll(dropdownOptionsSearchVacancy);
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().equalsIgnoreCase(vacancyName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vacancy not found for search: " + vacancyName));
        option.click();
    }

    public void selectHiringManagerForSearch(String hiringManager) {
        $(searchHiringManagerDropdown).click();
        List<WebElementFacade> options = findAll(dropdownOptionsSearchManager);
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().equalsIgnoreCase(hiringManager))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Hiring manager not found for search: " + hiringManager));
        option.click();
    }

    public void selectStatusForSearch(String status) {
        $(searchStatusDropdown).click();
        List<WebElementFacade> options = findAll(dropdownOptionsSearchStatus);
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status not found for search: " + status));
        option.click();
    }

    public void clickSearchButton() {
        $(searchButton).click();
    }

    public boolean isVacancyDisplayedInSearchResults(String vacancyName) {
        return findAll(searchResultsTable).stream()
                .anyMatch(row -> row.getText().contains(vacancyName));
    }

    public void deleteVacancy(String vacancyName) {
        List<WebElement> rows = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div"));
        for (WebElement row : rows) {
            if (row.getText().contains(vacancyName)) {
                WebElement deleteButton = row.findElement(deleteButtonTemplate);
                pause(5000);
                deleteButton.click();

                // Confirm deletion if required
                confirmDeletion();
                break;
            }
        }

    }

    public void confirmDeletion() {
        WebElement confirmButton = $(By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]"));
        pause(3000);
        confirmButton.click();
    }

    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getVacancyList() {
        List<WebElement> vacancyElements = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div"));
        //                                                                                       /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div
        //                                                                                       /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[5]/div/div[3]/div
        // Extract the usernames from the web elements
        return vacancyElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
