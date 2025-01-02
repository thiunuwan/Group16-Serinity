package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
//import starter.pageobjects.NotificationPage;
import starter.pageobjects.JobTitlesPage;

import java.util.List;

public class JobTitlesActionsSteps extends UIInteractionSteps {

    JobTitlesPage jobTitlesPage;
    //NotificationPage notificationPage;

    @Step("Open the OrangeHRM job title page")
    public void openJobTitlePage() {
        jobTitlesPage.open();
    }

    @Step("Add newJobTitle")
    public void addNewJobTitle(String jobTitle) {
        jobTitlesPage.clickAddJobTitle();
        jobTitlesPage.enterJobTitle(jobTitle);
        jobTitlesPage.saveJobTitle();
        waitABit(2000);
    }



    @Step("verify Adding New job title")
    public void verifyAddingJobTitle(String jobTitle) {
        jobTitlesPage.open();

        List<String> jobTitlesList = jobTitlesPage.getJobTitlesList();
        System.out.println(jobTitlesList);
        Assert.assertTrue("job title not found in the list!", jobTitlesList.contains(jobTitle));
    }


    //@Step("Verify notification contains the text: {0}")
//    public void shouldSeeNotification(String expectedText) {
//
//        List<String> notificationList = notificationPage.getAllNotificationTexts();
//        System.out.println(notificationList);
//
//        boolean notificationExists = notificationPage.containsNotification(expectedText);
//        Assert.assertTrue("Notification with text '" + expectedText + "' was not found!", notificationExists);
//    }

    @Step("verify  the deletion of job title")
    public void verifyDeletingJobTitle(String jobTitle) {

        List<String> jobTitlesList = jobTitlesPage.getJobTitlesList();
        System.out.println(jobTitlesList);
        Assert.assertFalse("job title found in the list!", jobTitlesList.contains(jobTitle));
    }

    @Step("Delete job title")
    public void deleteJobTitle(String jobTitle) {
        jobTitlesPage.open();
        jobTitlesPage.deleteJobTitle(jobTitle);
//        waitABit(2000);
    }

    @Step("Edit job title")
    public void editJobTitle(String jobTitle, String updatedTitle) {
        jobTitlesPage.open();
        jobTitlesPage.editJobTitle(jobTitle,updatedTitle);
        waitABit(2000);
    }
}
