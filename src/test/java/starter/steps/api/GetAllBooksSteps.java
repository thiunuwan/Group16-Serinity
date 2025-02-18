package starter.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class GetAllBooksSteps extends BaseSteps{

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
        SerenityRest.lastResponse().then()
                .body("books", notNullValue()) // Ensure the "books" field exists
                .body("books.size()", greaterThanOrEqualTo(0)); // Ensure the list contains zero or more books
    }

    @And("the response should indicate an empty book list")
    public void theResponseShouldIndicateAnEmptyBookList() {
        SerenityRest.lastResponse().then()
                .body("books", notNullValue()) // Ensure the "books" field exists
                .body("books.size()", equalTo(0)); // Ensure the list is empty
    }

    @And("the response for all books should contain the error message {string}")
    public void theResponseForAllBooksShouldContainTheErrorMessage(String expectedErrorMessage) {
        // Your implementation here
    }


    @When("I send a GET request to retrieve the book list as non-logged-in user")
    public void iSendAGETRequestToRetrieveTheBookListAsNonLoggedInUser() {

        // Send GET request to retrieve the book list
        SerenityRest.given()
                .get(BASE_URL + "/books");
    }
}
