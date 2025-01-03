package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList")
public class JobTitlesPage extends PageObject {

    PageUtils pageUtils = new PageUtils();

    private final By addJobTitleButton = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
    private final By jobTitleEditInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
    private final By saveButton = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]");
    private final By saveButtonEdit = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]");


    public void clickAddJobTitle() {
        pageUtils.clickButton(addJobTitleButton);
    }

    public void enterJobTitle(String jobtitle) {
        pageUtils.fillInputField(jobTitleEditInput, jobtitle);
    }

    public void saveJobTitle() {
        pageUtils.clickButton(saveButton);
        waitABit(5000);
    }

    public void saveJobTitleEdit() {
        pageUtils.clickButton(saveButtonEdit);
        waitABit(5000);
    }


    public void deleteJobTitle(String jobTitle) {

        List<WebElement> jobList = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div"));

        int rowIndex = -1;

        // Iterate through the job list to find the matching jobTitle
        for (int i = 0; i < jobList.size(); i++) {
            String currentTitle = jobList.get(i).getText().trim(); // Get the text and trim whitespace
            if (currentTitle.equalsIgnoreCase(jobTitle)) { // Compare ignoring case
                rowIndex = i + 1; // Add 1 if you want 1-based indexing
                break;
            }
        }

        // If the jobTitle is found, update it
        if (rowIndex != -1) {
            System.out.println("Job Title found at row: " + rowIndex);

        }

        String xpath = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[4]/div/button[1]";
        // Find the delete button using the dynamically constructed XPath
        WebElement deleteButton = getDriver().findElement(By.xpath(xpath));

        // Click the edit button
        deleteButton.click();
        waitABit(1000);

        WebElement confirmDeleteButton = getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/button[2]"));
        confirmDeleteButton.click();
        waitABit(1000);


    }


    public List<String> getJobTitlesList() {
        List<WebElement> jobTitleElements = getDriver().findElements(By.xpath(" /html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div/div[2]/div"));

        // Extract the job titles from the web elements
        return jobTitleElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void editJobTitle(String jobTitle, String updatedTitle) {
        List<WebElement> jobList = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div"));

        int rowIndex = -1;

        // Iterate through the job list to find the matching jobTitle
        for (int i = 0; i < jobList.size(); i++) {
            String currentTitle = jobList.get(i).getText().trim(); // Get the text and trim whitespace
            if (currentTitle.equalsIgnoreCase(jobTitle)) { // Compare ignoring case
                rowIndex = i + 1; // Add 1 if you want 1-based indexing
                break;
            }
        }

        // If the jobTitle is found, update it
        if (rowIndex != -1) {
            System.out.println("Job Title found at row: " + rowIndex);

        }

        String xpath = "/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[4]/div/button[2]";

        // Find the delete button using the dynamically constructed XPath
        WebElement editButton = getDriver().findElement(By.xpath(xpath));

        // Click the edit button
        editButton.click();
        pageUtils.fillInputField(jobTitleEditInput, updatedTitle);
        saveJobTitleEdit();
        waitABit(5000);

    }
}
