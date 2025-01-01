package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.AddEmployeePage;
import starter.pageobjects.NotificationPage;

import java.util.List;

public class AddEmployeeActionSteps extends UIInteractionSteps {

    AddEmployeePage addEmployeePage;

    NotificationPage notificationPage;

    @Step("Open the OrangeHRM PIM page")
    public void openPIMPage() {
        addEmployeePage.open();
    }

    @Step("Add newEmployee")
    public void addNewEmployee(String firstName, String lastName, String middleName, String employeeId) {
        addEmployeePage.clickAddEmployee();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastname(lastName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.saveEmployee();
    }

    @Step("Verify notification contains the text: {0}")
    public void shouldSeeNotification(String expectedText) {

        List<String> notificationList = notificationPage.getAllNotificationTexts();
        System.out.println(notificationList);

        boolean notificationExists = notificationPage.containsNotification(expectedText);
        Assert.assertTrue("Notification with text '" + expectedText + "' was not found!", notificationExists);
    }

    @Step("verify  Adding New candidate")
    public void verifyAddingCandidate(String employeeName) {
        addEmployeePage.open();

        List<String> employeeList = addEmployeePage.getEmployeeList();
        System.out.println(employeeList);
        Assert.assertTrue("Employee not found in the list!", employeeList.contains(employeeName));
    }
}
