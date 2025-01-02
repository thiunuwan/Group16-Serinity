package starter.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList")
public class AddEmployeePage extends PageObject {

    private final By addButton = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[3]/a");
    private final By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
    private final By firstnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input");
    private final By middlenameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div[2]/input");
    private final By lastnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input");
    private final By employeeidInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    private final By deleteButtonTemplate = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[%s]/div/div[9]/div/button[2]");
    private final By toggleInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/input");
    private final By usernameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input");
//                                                          /html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input
    private final By passwordInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input");
    private final By confirmPasswordInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input");
    private final By checkBox = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/label/span");

    public void clickAddEmployee() {

        $(addButton).click();
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
    public void enterMiddleName(String middleName) {

        $(middlenameInput).type(middleName);
        pause(2000);
    }

    public void enterEmployeeId(String employeeId) {

        $(employeeidInput).type(employeeId);
        pause(2000);
    }

    public void enterUsername(String username) {
        $(usernameInput).type(username);
    }

    public void enterPassword(String password) {
        $(passwordInput).type(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        $(confirmPasswordInput).type(confirmPassword);
    }

    public void enableToggle() {
        WebElement toggleElement = getDriver().findElement(toggleInput);

        // Use JavaScript to click the element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].click();", toggleElement);

    }

//    public void toggleOn() {
//        WebElement toggle = waitForElementToBeVisible(toggleInput);
//        if (!toggleInput.isSelected()) {
//            toggle.click(); // Enable the toggle if it's not already on
//        }
//    }
//
//    public void toggleOff() {
//        WebElement toggle = waitForElementToBeVisible(toggleInput);
//        if (toggle.isSelected()) {
//            toggle.click(); // Disable the toggle if it's not already off
//        }
//    }
//
//    public boolean isToggleOn() {
//        return waitForElementToBeVisible(toggleInput).isSelected(); // Check if the toggle is enabled
//    }

    public void saveEmployee() {
        $(saveButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        private void pause ( long milliseconds){
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public List<String> getEmployeeNamelist() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div")));
        waitABit(1000);
        List<WebElement> EmployeeNames = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div"));


        return EmployeeNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getEmployeeIdlist() {
        List<WebElement> EmployeeIDs = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[2]/div"));
//        /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[2]/div
//        /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[2]/div/div[2]/div

        return EmployeeIDs.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void deleteEmployee() {
        String employee_id = Serenity.sessionVariableCalled("employee-id");
        List<String> employeeIDlist = getEmployeeIdlist();
        System.out.println("***");
        System.out.println(employeeIDlist);
        int employeeIndex = employeeIDlist.indexOf(employee_id);
        employeeIndex++;
        clickDelete(employeeIndex);


    }

    public void clickDelete(int rowIndex) {
        // Construct the XPath dynamically using the rowIndex
        String xpath = "/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[9]/div/button[2]";
//                        "/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[9]/div/button[2]"

        // Find the delete button using the dynamically constructed XPath
        WebElement deleteButton = getDriver().findElement(By.xpath(xpath));

        // Click the delete button
        deleteButton.click();

        // Find the "Yes, Delete" button and click it
        WebElement confirmDeleteButton = getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/button[2]"));
        confirmDeleteButton.click();

        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void confirmDeletion() {
        WebElement confirmButton = $(By.xpath("//html/body/div/div[3]/div/div/div/div[3]/button[2]"));
        pause(3000);
        confirmButton.click();
    }

    public String getEmployeeId() {
        return $(employeeidInput).getAttribute("value");
    }

    public String getEmployeeNameForGivenID(String employeeIndex) {

//        WebElement employeeNameElement = getDriver().findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div", employeeIndex));
//        return employeeNameElement.getText();
        String xpath = String.format("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[%s]/div/div[3]/div", employeeIndex);
//                                    /html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div

        // Find the employee name element using the dynamic XPath
        WebElement employeeNameElement = getDriver().findElement(By.xpath(xpath));

        // Return the text content of the employee name element
        return employeeNameElement.getText();

    }
}
