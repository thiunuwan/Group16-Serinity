package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.RecruitmentPage;

import java.util.List;

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

    @Step("verify  Adding New candidate")
    public void verifyAddingCandidate(String candidateName) {
        recruitmentPage.open();

        List<String> candidateList = recruitmentPage.getCandidateList();
        System.out.println(candidateList);
        Assert.assertTrue("candidate not found in the list!", candidateList.contains(candidateName));
    }
}
