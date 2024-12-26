package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

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



}
