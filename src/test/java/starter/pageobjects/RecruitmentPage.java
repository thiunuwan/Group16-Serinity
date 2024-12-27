package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates")
public class RecruitmentPage extends PageObject {
    private final By addCandidateButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    private final By firstnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
    private final By lastnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input");
    private final By emailInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
    private final By contactInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input");
    private final By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]");


    public void clickAddCandidate() {

        $(addCandidateButton).click();
        pause(2000);
    }

    public void enterFirstName(String firstname) {

        $(firstnameInput).type(firstname);
        pause(2000);
    }
    public void enterLastname(String lastname) {

        $(lastnameInput).type(lastname);
        pause(2000);
    }
    public void enterEmail(String email) {

        $(emailInput).type(email);
        pause(2000);
    }
    public void enterContactNumber(String contactNumber) {

        $(contactInput).type(contactNumber);
        pause(2000);
    }
    public void saveCandidate() {
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

    public List<String> getCandidateList() {
        pause(9000);
        // Wait for the elements to be present
//       WebDriverWait wait = new WebDriverWait(getDriver(), 30); // Adjust timeout as needed
//       wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'data')]")));

        // Find all elements with the class 'data'
        List<WebElement> nameElements = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[*]/div/div/div[1]/div[1]/div/div/div[2]"));
//                                                                                           /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[*]/div/div/div[1]/div[1]/div/div/div[2]
        // Extract the text from each element
        return nameElements.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty()) // Filter out any empty elements
                .collect(Collectors.toList());
    }



}
