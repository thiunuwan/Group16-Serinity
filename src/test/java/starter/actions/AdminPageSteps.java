package starter.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.pageobjects.AdminPage;

import java.util.List;


public class AdminPageSteps extends UIInteractionSteps {

    AdminPage adminPage;

    @Step("Open the OrangeHRM admin page")
    public void openAdminPage() {
        adminPage.open();
    }


    @Step("Add newTestUser")
    public void addNewTestUser(String username, String password,String role) {
        adminPage.clickAddUser();
        adminPage.addUserRole(role);
        adminPage.enterEmpName("Akash");
        adminPage.enterStatus2("Enabled");
        adminPage.enterUsername(username);
        adminPage.enterPassword(password);
        adminPage.saveUser();
    }

    @Step("verify  Adding New TestUser")
    public void verifyAddingTestUser(String username) {
        List<String> userList = adminPage.getUserList();
        System.out.println(userList);
        Assert.assertTrue("User not found in the list!", userList.contains(username));
    }


    public void deleteTestUser(String username) {
        List<String> userList = adminPage.getUserList();
        System.out.println(userList);

        // Find the index of the given username
        int index = userList.indexOf(username);

        if (index != -1) {
            System.out.println("Username found at index: " + index);
            adminPage.clickDelete(index+1);
        } else {
            System.out.println("Username not found in the list");
        }

    }

    public void verifyDeletingTestUser(String username) {
        List<String> userList = adminPage.getUserList();
        System.out.println(userList);
        Assert.assertFalse("User should not be in the list!", userList.contains(username));
    }
}