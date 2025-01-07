package starter.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList")
public class AddEmployeePage extends PageObject {

    PageUtils pageUtils = new PageUtils();


    private final By addButton = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[3]/a");
    private final By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
    private final By firstnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input");
    private final By middlenameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div[2]/input");
    private final By lastnameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input");
    private final By employeeidInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    private final By toggleInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/input");
    private final By usernameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input");
    private final By passwordInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input");
    private final By confirmPasswordInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input");


    public void clickAddEmployee() {
        pageUtils.clickButton(addButton);
    }

    public void enterFirstName(String firstname) {
        pageUtils.fillInputField(firstnameInput, firstname);
    }

    public void enterLastname(String lastname) {
        pageUtils.fillInputField(lastnameInput, lastname);
    }

    public void enterMiddleName(String middleName) {
        pageUtils.fillInputField(middlenameInput, middleName);
    }

    public void enterUsername(String username) {
        pageUtils.fillInputField(usernameInput, username);
    }

    public void enterPassword(String password) {
        pageUtils.fillInputField(passwordInput, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        pageUtils.fillInputField(confirmPasswordInput, confirmPassword);
    }

    public void enableToggle() {
        WebElement toggleElement = getDriver().findElement(toggleInput);
        // Use JavaScript to click the element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].click();", toggleElement);
    }


    public void saveEmployee() {
        pageUtils.clickButton(saveButton);
        waitABit(5000);
    }


    public List<String> getEmployeeNamelist() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        waitABit(1000);
        List<WebElement> EmployeeNames = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[3]/div"));

        return EmployeeNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getEmployeeIdlist() {
        List<WebElement> EmployeeIDs = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[2]/div"));

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

        // Find the delete button using the dynamically constructed XPath
        WebElement deleteButton = getDriver().findElement(By.xpath(xpath));

        // Click the delete button
        deleteButton.click();

        // Find the "Yes, Delete" button and click it
        WebElement confirmDeleteButton = getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/button[2]"));
        confirmDeleteButton.click();
        waitABit(5000);
    }


    public String getEmployeeId() {
        return $(employeeidInput).waitUntilVisible().getAttribute("value");
    }


    public String getEmployeeNameForGivenID(String employeeIndex) {

        String xpath = String.format("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[%s]/div/div[3]/div", employeeIndex);

        // Find the employee name element using the dynamic XPath
        WebElement employeeNameElement = getDriver().findElement(By.xpath(xpath));

        // Return the text content of the employee name element
        return employeeNameElement.getText();

    }
}
