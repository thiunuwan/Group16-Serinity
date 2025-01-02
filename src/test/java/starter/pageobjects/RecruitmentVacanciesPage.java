package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


import org.openqa.selenium.support.ui.WebDriverWait;


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
    private final By searchVacancyNameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
    private final By dropdownOptionsSearch = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.oxd-table-filter > div.oxd-table-filter-area > form > div.oxd-form-row > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div.oxd-select-text-input");
    private final By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");


    private final By deleteButtonTemplate = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div/div[1]/div[2]/div/div/button[1]");



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
        // Extract the usernames from the web elements
        return vacancyElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

//    public void enterNameForSearch(String name) {
//
//        $(searchVacancyNameInput).type(name);
//        pause(2000);
//    }

    public void enterNameForSearch(String name) {
        // Click on the search input field
        $(searchVacancyNameInput).click();

        // Add explicit wait for the dropdown options to load
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(driver -> !findAll(dropdownOptionsSearch).isEmpty());

        // Fetch the available options
        List<WebElementFacade> options = findAll(dropdownOptionsSearch);

        // Debug: Print available options for verification
        System.out.println("Debug: Available options:");
        options.forEach(option -> System.out.println("Option: " + option.getText()));

        // Handle cases where no valid options are present
        if (options.isEmpty()) {
            throw new RuntimeException("No vacancies available in the dropdown.");
        }

        // Search for the matching option
        WebElementFacade option = options.stream()
                .filter(e -> e.getText().trim().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vacancy not found: " + name));

        // Click on the matching option
        option.click();
    }




    public void searchVacancy() {
        $(searchButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
