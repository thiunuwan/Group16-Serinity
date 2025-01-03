package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers")
public class AdminPage extends PageObject {

    // Locator for the "Add" button
    private final By addUserButton = By.xpath("//button[contains(@class, 'oxd-button--secondary') and contains(., 'Add')]");
    private final By saveButton = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");

    private final By userRoleDropdown = By.xpath("//div[contains(@class, 'oxd-select-text')]");
    private final By dropdownOptions = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By usernameInput = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private final By passwordInput = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private final By statusDropdown = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i");
    private final By dropdownOptionsStatus = By.xpath("//div[@role='listbox']//div[@role='option']");
    private final By confirmPasswordInput = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private final By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private final By usernameSearchInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
    private final By usernameUpdateInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private final By userRoleDropdownForSeach = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i");
    private final By saveButtonUpdate = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElementFacade employeeNameInput;


    public void enterEmpName() {
        // Type "ra" into the employee name input  and pick first suggestion
        employeeNameInput.type("ra");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ARROW_DOWN)  // Simulate pressing the down arrow key
                .sendKeys(Keys.ENTER)       // Simulate pressing the enter key
                .perform();
    }

    public void clickAddUser() {
        $(addUserButton).click();
    }

    public void enterUsername(String username) {
        // Type the username into the input field
        $(usernameInput).type(username);
    }

    public void enterUsernameForSearch(String username) {
        // Type the username into the input field
        $(usernameSearchInput).type(username);
    }

    public void enterPassword(String password) {
        // Clear the field (optional) and type the password
//        $(passwordInput).clear();
        $(passwordInput).type(password);

        // Clear the field (optional) and type the confirmation password
//        $(confirmPasswordInput).clear();
        $(confirmPasswordInput).type(password);
    }

    public void saveUser() {
        $(saveButton).click();
        waitABit(10000);
    }

    public void addUserRole(String role) {
          // Click to open the dropdown
        $(userRoleDropdown).click();

        // Select the role from the dropdown list
        WebElement option = findAll(dropdownOptions).stream()
                .filter(e -> e.getText().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found: " + role));

        option.click();
    }


    public void enterStatus2(String status) {
        // Click to open the dropdown
        $(statusDropdown).click();

        // Select the status from the dropdown list
        WebElement option = findAll(dropdownOptionsStatus).stream()
                .filter(e -> e.getText().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status not found: " + status));
        option.click();
    }


    public List<String> getUserList() {

        waitABit(10000);
        List<WebElement> userElements = getDriver().findElements(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[*]/div/div[2]/div"));

        // Extract the usernames from the web elements
        return userElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public void searchUser() {
        $(searchButton).click();
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getUserSearchResult() {
        WebElement userElement = getDriver().findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div"));
        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Extract the usernames from the web elements
        return userElement.getText();
    }

    public void clickDelete(int rowIndex) {
        // Construct the XPath dynamically using the rowIndex
        String xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[6]/div/button[1]";


        // Find the delete button using the dynamically constructed XPath
        WebElement deleteButton = getDriver().findElement(By.xpath(xpath));


        // Click the delete button
        deleteButton.click();


        // Wait for the confirmation modal or button to appear (optional, can be adjusted depending on the page behavior)
       // WebDriverWait wait = new WebDriverWait(getDriver(), 10); // Adjust timeout as needed
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes, Delete')]")));


        // Find the "Yes, Delete" button and click it
        WebElement confirmDeleteButton = getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/button[2]"));
        confirmDeleteButton.click();

    }

    public void clickUpdate(int index) {
        // Construct the XPath dynamically using the rowIndex
        String xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[" + index + "]/div/div[6]/div/button[2]";

        // Find the delete button using the dynamically constructed XPath
        WebElement updateButton = getDriver().findElement(By.xpath(xpath));


        // Click the update button
        updateButton.click();
    }


    public void updateUserDetails(String newUserName, String newRole) {
        // Type the newUsername into the input field
        $(usernameUpdateInput).type(newUserName);
        // Click to open the dropdown
        $(userRoleDropdownForSeach).click();

        // Select the role from the dropdown list
        WebElement option = findAll(dropdownOptions).stream()
                .filter(e -> e.getText().equalsIgnoreCase(newRole))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found: " + newRole));

        option.click();
        $(saveButtonUpdate).click();
      }

    public void clickDelete2(int rowIndex) {
        // Construct the XPath dynamically using the rowIndex
        String xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[" + rowIndex + "]/div/div[6]/div/button[1]";


        // Find the delete button using the dynamically constructed XPath
        WebElement deleteButton = getDriver().findElement(By.xpath(xpath));


        // Click the delete button
        deleteButton.click();


        // Wait for the confirmation modal or button to appear (optional, can be adjusted depending on the page behavior)
       // WebDriverWait wait = new WebDriverWait(getDriver(), 10); // Adjust timeout as needed
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes, Delete')]")));


        // Find the "Yes, Delete" button and click it
        WebElement confirmDeleteButton = getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/button[2]"));
        confirmDeleteButton.click();

        try {
            Thread.sleep(5000); // 5 seconds wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
