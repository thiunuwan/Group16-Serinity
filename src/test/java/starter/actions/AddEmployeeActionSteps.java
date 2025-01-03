package starter.actions;

import net.serenitybdd.core.Serenity;
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
    public void addNewEmployee(String firstName, String middleName, String lastName) {
        addEmployeePage.clickAddEmployee();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastname(lastName);
        String user_id = addEmployeePage.getEmployeeId();
        Serenity.setSessionVariable("employee-id").to(user_id);
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
    public void verifyAddingEmployee(String employeeName) {
        addEmployeePage.open();
        waitABit(10000);
        List<String> employeeIDlist = addEmployeePage.getEmployeeIdlist();
        String employee_id = Serenity.sessionVariableCalled("employee-id");
        System.out.println("***");
        System.out.println(employeeIDlist);
        List<String> employeeNamelist = addEmployeePage.getEmployeeNamelist();
        System.out.println(employeeNamelist);
        int employeeIndex = employeeIDlist.indexOf(employee_id);
        employeeIndex++;
        String employeeIndexAsString = String.valueOf(employeeIndex);
        String employeeNameFromTable = addEmployeePage.getEmployeeNameForGivenID(employeeIndexAsString);
        System.out.println("***");
        System.out.println(employeeNameFromTable);
        System.out.println("***");
        // Log the index
        System.out.println("Index of employee_id (" + employee_id + "): " + employeeIndex);


        Assert.assertEquals("Employee name does not match!", employeeName, employeeNameFromTable);

    }


    @Step("Delete test employee")
    public void deleteTestEmployee() {
        addEmployeePage.open();
        addEmployeePage.deleteEmployee();
    }


    @Step("Add a new employee with username")
    public void addEmployeeWithUsername(String firstName, String middleName, String lastName, String username, String password, String confirmPassword) {
        addEmployeePage.clickAddEmployee();
        addEmployeePage.enterFirstName(firstName);
        String user_id = addEmployeePage.getEmployeeId();
        Serenity.setSessionVariable("employee-id").to(user_id);
        addEmployeePage.enableToggle();
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastname(lastName);
        addEmployeePage.enterUsername(username);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(confirmPassword);
        addEmployeePage.saveEmployee();


        waitABit(5000);

    }


    public void verifyEmployeeNotInList() {

        List<String> employeeIDlist = addEmployeePage.getEmployeeIdlist();
        System.out.println("###");
        System.out.println(employeeIDlist);
        System.out.println("###");
        String employee_id = Serenity.sessionVariableCalled("employee-id");
        Assert.assertFalse("User should not be in the list!", employeeIDlist.contains(employee_id));

    }
}
