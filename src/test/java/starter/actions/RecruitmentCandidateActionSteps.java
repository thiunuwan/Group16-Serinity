package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import starter.pageobjects.RecruitmentPage;

public class RecruitmentCandidateActionSteps extends UIInteractionSteps {

    RecruitmentPage recruitmentPage;

    @Step("Open the OrangeHRM Recruitment page")
    public void openRecruitmentCPage() {
        recruitmentPage.open();
    }

    @Step("Add newCandidate")
    public void addNewCandidate(String firstName, String lastName, String email, String contact) {
        recruitmentPage.clickAddCandidate();
        recruitmentPage.enterFirstName(firstName);
        recruitmentPage.enterLastname(lastName);
        recruitmentPage.enterEmail(email);
        recruitmentPage.enterContactNumber(contact);
        recruitmentPage.saveCandidate();
    }
}
