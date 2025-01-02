package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates")
public class RecruitmentPage extends PageObject {
    private final By addCandidateButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    private final By firstnameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
    private final By lastnameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input");
    private final By emailInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
    private final By contactInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input");
    private final By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]");
    private final By deleteButtonTemplate = By.xpath("//button[./i[contains(@class, 'bi-trash')]]\n");
    private final By searchCandidateNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input");
    private final By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[2]");
    private final By checkBox = By.xpath("//span[contains(@class, 'oxd-checkbox-input')]/i");

    public void clickAddCandidate() {
        if ($(addCandidateButton).waitUntilClickable().isEnabled()) {
            $(addCandidateButton).click();
        } else {
            System.out.println("addCandidate button is not enabled.");
        }
    }

    public void enterFirstName(String firstname) {
        if ($(firstnameInput).waitUntilVisible().isEnabled()) {
            $(firstnameInput).type(firstname);
        } else {
            System.out.println("First Name input is not enabled.");
        }
    }

    public void enterLastname(String lastname) {
        if ($(lastnameInput).waitUntilVisible().isEnabled()) {
            $(lastnameInput).type(lastname);
        } else {
            System.out.println("Last Name input is not enabled.");
        }
    }

    public void enterEmail(String email) {
        if ($(emailInput).waitUntilVisible().isEnabled()) {
            $(emailInput).type(email);
        } else {
            System.out.println("Email input is not enabled.");
        }
    }

    public void enterNameForSearch(String name) {
        if ($(searchCandidateNameInput).waitUntilVisible().isEnabled()) {
            $(searchCandidateNameInput).type(name);
        } else {
            System.out.println("Search input is not enabled.");
        }
    }

    public void enterContactNumber(String contactNumber) {
        if ($(contactInput).waitUntilVisible().isEnabled()) {
            $(contactInput).type(contactNumber);
        } else {
            System.out.println("Contact input is not enabled.");
        }
    }

    public void setAgreeTermsCheckbox(boolean state) {
        if (state) {
            if (!$(checkBox).isSelected()) {
                $(checkBox).click(); // Check the checkbox
            }
        } else {
            if ($(checkBox).isSelected()) {
                $(checkBox).click(); // Uncheck the checkbox
            }
        }
    }

    public void saveCandidate() {
        if ($(saveButton).waitUntilClickable().isEnabled()) {
            $(saveButton).click();
        } else {
            System.out.println("Save button is not enabled.");
        }
    }

    public void searchCandidate() {
        if ($(searchButton).waitUntilClickable().isEnabled()) {
            $(searchButton).click();
        } else {
            System.out.println("search button is not enabled.");
        }
    }

    public void deleteCandidate(String candidateName) {
        List<WebElement> rows = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div"));
        for (WebElement row : rows) {
            if (row.getText().contains(candidateName)) {
                WebElement deleteButton = row.findElement(deleteButtonTemplate);
                waitForElement(deleteButton, 60, 500);
                deleteButton.click();

                // Confirm deletion if required
                confirmDeletion();
                break;
            }
        }

    }

    public void confirmDeletion() {
        WebElement confirmButton = $(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and ./i[contains(@class, 'bi-trash')] and contains(., 'Yes, Delete')]"));
        $(confirmButton).waitUntilClickable().click();
    }

    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getCandidateList() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);

        List<WebElement> candidateElements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div"))
        );

        // Extract the usernames from the web elements
        return candidateElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // fluent wait implementation
    public void waitForElement(WebElement element, int timeoutInSeconds, int pollingTimeInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingTimeInMillis))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
