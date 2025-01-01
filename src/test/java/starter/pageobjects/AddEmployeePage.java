package starter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    private final By checkBox = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/p");

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

    public List<String> getEmployeeList() {
        List<WebElement> EmployeeElements = getDriver().findElements(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div"));

        return EmployeeElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    }
