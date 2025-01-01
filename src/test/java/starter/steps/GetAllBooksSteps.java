package starter.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class GetAllBooksSteps {

    // Retrieve BASE_URL from configuration
    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

    @When("I send a GET request to retrieve the book list")
    public void iSendAGETRequestToRetrieveTheBookList() {
        // Retrieve credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header (if credentials exist)
        String basicAuthHeader = (username != null && password != null)
                ? starter.utils.AuthUtils.generateBasicAuthHeader(username, password)
                : null;

        // Send GET request to retrieve the book list
        SerenityRest.given()
               .header("Authorization", basicAuthHeader)
                .get(BASE_URL + "/books");
    }

    @Then("the response status code for all books should be {int}")
    public void theResponseStatusCodeForAllBooksShouldBe(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }


    @And("the response should contain a list of books")
    public void theResponseShouldContainAListOfBooks() {
//        String responseBody = SerenityRest.lastResponse().prettyPrint();
//        System.out.println("Response Body: \n" + responseBody);

        SerenityRest.lastResponse().then()
                .body("", notNullValue()) // Ensure the response is not null
                .body("size()", greaterThan(0)); // Ensure the root array contains one or more objects
    }



    @And("the response should indicate an empty book list")
    public void theResponseShouldIndicateAnEmptyBookList() {

        SerenityRest.lastResponse().then()
                .body("", notNullValue()) // Ensure the response is not null
                .body("size()", equalTo(0)); // Ensure the root array contains one or more objects
    }


    @When("I send an unauthorized GET request to retrieve the book list")
    public void iSendAnUnauthorizedGETRequestToRetrieveTheBookList() {
        // Retrieve credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header (if credentials exist)
        String basicAuthHeader = (username != null && password != null)
                ? starter.utils.AuthUtils.generateBasicAuthHeader(username, password)
                : null;

        // Send GET request to retrieve the book list
        SerenityRest.given()
                .get(BASE_URL + "/books");
    }

}
