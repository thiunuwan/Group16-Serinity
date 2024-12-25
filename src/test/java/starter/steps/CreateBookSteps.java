package starter.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import starter.utils.AuthUtils;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class CreateBookSteps {

    // Retrieve BASE_URL from configuration
    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

    private Response response;

    @When("I send a POST request to create a new book with title {string} and author {string}")
    public void iSendAPOSTRequestToCreateANewBook(String title, String author) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Build request payload
        String payload = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        // Send POST request to create a book
        response = SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL + "/books");
    }

    @Then("the response status code should {int}")
    public void theResponseStatusCodeForCreateOperationShouldBe(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @And("the response should contain the book details with title {string} and author {string}")
    public void theResponseShouldContainTheBookDetails(String expectedTitle, String expectedAuthor) {
        // Assert that the response contains the expected book details
        response.then().body("title", equalTo(expectedTitle));
        response.then().body("author", equalTo(expectedAuthor));
        response.then().body("id", notNullValue()); // Ensure the book ID is generated
    }

    @When("I send a POST request to create a book with empty title and author")
    public void iSendAPOSTRequestToCreateABookWithEmptyTitleAndAuthorAsNormalUser() {
        // Retrieve normal user credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Build the payload with empty title and author
        String payload = "{\"title\": \"\", \"author\": \"\"}";

        // Send POST request
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL + "/books");
    }

    @Then("the response status code should be {int} for empty input as a normal user")
    public void theResponseStatusCodeShouldBeForEmptyInputAsNormalUser(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @And("the response should contain validation errors for empty title and author as a normal user")
    public void theResponseShouldContainValidationErrorsForEmptyTitleAndAuthorAsNormalUser() {
        // Retrieve the response body
        Response response = SerenityRest.lastResponse();

        // Validate the error messages in the response
        response.then().body("errors.title", equalTo("Title is required"));
        response.then().body("errors.author", equalTo("Author is required"));
    }

//    @Given("a book with title "Duplicate Book" and author \"John Doe\" already exists")
//    public void aBookWithTitleAndAuthorAlreadyExists(String title, String author) {
//        // Retrieve admin credentials from session variables
//        String username = Serenity.sessionVariableCalled("username");
//        String password = Serenity.sessionVariableCalled("password");
//
//        // Generate Basic Auth header
//        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);
//
//        // Create the payload for the book
//        String payload = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);
//
//        // Send a POST request to ensure the book exists
//        SerenityRest.given()
//                .header("Authorization", basicAuthHeader)
//                .header("Content-Type", "application/json")
//                .body(payload)
//                .post(BASE_URL + "/books");
//    }
//
//    @When("I send a POST request to create a book with title {string} and author {string}")
//    public void iSendAPOSTRequestToCreateABookWithTitleAndAuthor(String title, String author) {
//        // Retrieve normal user credentials from session variables
//        String username = Serenity.sessionVariableCalled("username");
//        String password = Serenity.sessionVariableCalled("password");
//
//        // Generate Basic Auth header
//        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);
//
//        // Create the payload for the book
//        String payload = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);
//
//        // Send a POST request to create the book
//        SerenityRest.given()
//                .header("Authorization", basicAuthHeader)
//                .header("Content-Type", "application/json")
//                .body(payload)
//                .post(BASE_URL + "/books");
//    }
//
//    @Then("the response status code should be {int} for duplicate book")
//    public void theResponseStatusCodeShouldBeForDuplicateBook(int statusCode) {
//        restAssuredThat(response -> response.statusCode(statusCode));
//    }
//
//    @And("the response should contain the error message {string} for duplicate book")
//    public void theResponseShouldContainTheErrorMessageForDuplicateBook(String expectedErrorMessage) {
//        // Retrieve the response body
//        Response response = SerenityRest.lastResponse();
//        String responseBody = response.getBody().asString();
//
//        // Assert the error message is present
//        assertTrue("Response body should contain the error message: " + expectedErrorMessage,
//                responseBody.contains(expectedErrorMessage));
//    }

}
