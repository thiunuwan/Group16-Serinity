package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.NotificationPage;
import starter.pageobjects.RecruitmentPage;

import java.util.List;

public class RecruitmentCandidateActionSteps extends UIInteractionSteps {

    RecruitmentPage recruitmentPage;
    NotificationPage notificationPage;

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


    @Step("Verify notification contains the text: {0}")
    public void shouldSeeNotification(String expectedText) {

        List<String> notificationList = notificationPage.getAllNotificationTexts();
        System.out.println(notificationList);

        boolean notificationExists = notificationPage.containsNotification(expectedText);
        Assert.assertTrue("Notification with text '" + expectedText + "' was not found!", notificationExists);
    }

    @Step("verify  the deletion of candidate")
    public void verifyDeletingCandidate(String candidateName) {

        List<String> candidateList = recruitmentPage.getCandidateList();
        System.out.println(candidateList);
        Assert.assertFalse("candidate  found in the list!", candidateList.contains(candidateName));
    }

    @Step("Delete candidate")
    public void deleteCandidate(String candidateName) {
        recruitmentPage.open();
        recruitmentPage.deleteCandidate(candidateName);
    }

    @Step("Search candidate")
    public void searchCandidate(String candidateName) {
        recruitmentPage.open();
        recruitmentPage.enterNameForSearch(candidateName);
        recruitmentPage.searchCandidate();
    }
}
