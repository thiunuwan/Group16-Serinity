package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates")
public class RecruitmentPage extends PageObject {
    private final By addCandidateButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    private final By firstnameInput;
    private final By lastnameInput;
    private final By emailInput;
    private final By contactInput;
    private final By saveButton;


    public void clickAddCandidate() {
        $(addCandidateButton).click();
    }

    public void enterFirstName(String username) {

        $(firstnameInput).type(username);
    }
    public void enterLastname(String username) {

        $(lastnameInput).type(username);
    }
    public void enterEmail(String username) {

        $(emailInput).type(username);
    }
    public void enterContactnumber(String username) {

        $(contactInput).type(username);
    }
    public void saveCandidate() {
        $(saveButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
