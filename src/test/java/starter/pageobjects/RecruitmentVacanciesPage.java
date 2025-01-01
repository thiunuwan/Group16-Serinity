package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewJobVacancy")
public class RecruitmentVacanciesPage extends PageObject {

    private final By addVacancyButton = By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]");
    private final By vacancyNameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/div[2]/input");
    private final By jobTitleDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div/div");
    private final By dropdownOptions = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By hiringManagerInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div[1]/div/div[2]/div/div/input");
    private final By numberOfPositionsInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div[2]/div/div/div/div[2]/input");
    private final By saveButton = By.xpath("//button[contains(., 'Save')]");
    private final By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-container')]//p");

    public void clickAddVacancy() {
        $(addVacancyButton).click();
    }

    public void enterVacancyName(String vacancyName) {
        $(vacancyNameInput).type(vacancyName);
    }

    public void selectJobTitle(String jobTitle) {
        $(jobTitleDropdown).click();
        // Print all found dropdown options
        List<WebElementFacade> options = findAll(dropdownOptions);
        System.out.println("Found job titles in the dropdown:");
        options.forEach(option -> System.out.println(option.getText()));

        WebElementFacade option = findAll(dropdownOptions).stream()
                .filter(e -> e.getText().equalsIgnoreCase(jobTitle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Job title not found: " + jobTitle));
        option.click();
    }


    public void enterHiringManager(String hiringManager) {
        $(hiringManagerInput).type(hiringManager);
        waitABit(2000); // Wait to ensure suggestions load
        $(hiringManagerInput).sendKeys(Keys.ARROW_DOWN);
        $(hiringManagerInput).sendKeys(Keys.ENTER);
    }

    public void enterNumberOfPositions(String numberOfPositions) {
        $(numberOfPositionsInput).type(numberOfPositions);
    }

    public void clickSaveButton() {
        $(saveButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return $(successMessage).isDisplayed();
    }

}
