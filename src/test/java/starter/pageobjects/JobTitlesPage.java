package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList")
public class JobTitlesPage extends PageObject {
    private final By addJobTitleButton = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[1]/div/button");
    private final By jobTitleInput = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
    private final By saveButton = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]");

    private final By editButtonTemplate = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div[1]/div/div/div[1]/div[2]/div/div/button[2]/i");

    private final By deleteButtonTemplate = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[1]/i");

//    /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[1]
//    /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[1]/i
//    /html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div/div/div/div[1]/div[2]/div/div/button[1]/i

    public void clickAddJobTitle() {

        $(addJobTitleButton).click();
        pause(2000);
    }

    public void enterJobTitle(String jobtitle) {

        $(jobTitleInput).type(jobtitle);
        pause(2000);
    }

    public void saveJobTitle() {
        $(saveButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteJobTitle(String jobTitle) {
        List<WebElement> rows = getDriver().findElements(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div"));

//       /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div
//        /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[2]/div
        for (WebElement row : rows) {
            if (row.getText().contains(jobTitle)) {
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
        WebElement confirmButton = $(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and ./i[contains(@class, 'bi-trash')] and contains(., 'Yes, Delete')]"));
        pause(3000);
        confirmButton.click();
    }


    public List<String> getJobTitlesList() {
        List<WebElement> jobTitleElements = getDriver().findElements(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div/div[2]/div"));
//        /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div/div[2]/div
//        /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[2]/div/div[2]/div

        // Extract the job titles from the web elements
        return jobTitleElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void editJobTitle(String jobTitle) {
        List<String> jobTitlesList = getJobTitlesList();
        System.out.println("***");
        System.out.println(jobTitlesList);

        String matchingTitle = jobTitlesList.stream()
                .filter(title -> title.equals(jobTitle))
                .findFirst()
                .orElse(null);

        clickEdit(matchingTitle);
    }

    private void clickEdit(String matchingTitle) {
//        String xpath =
//                   /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[*]/div/div[4]/div/button[2]
//                  /html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[2]/div/div[4]/div/button[2]
    }
}
