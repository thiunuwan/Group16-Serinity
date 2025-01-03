package starter.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import starter.utils.AuthUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookSteps {

    // Retrieve BASE_URL from configuration
    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

    private Response response;

    @When("I send a POST request to create a book with empty title with author {string}")
    public void iSendAPostRequestToCreateABookWithEmptyTitleWithAuthor(String author) {
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(
                Serenity.sessionVariableCalled("username"),
                Serenity.sessionVariableCalled("password")
        );
        String payload = String.format("{\"title\": \"\", \"author\": \"%s\"}",author);
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL+"/books");
    }

    @When("I send a request to create a book with invalid ID format")
    public void iSendARequestToCreateABookWithInvalidIDFormat() {
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(
                Serenity.sessionVariableCalled("username"),
                Serenity.sessionVariableCalled("password")
        );

        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body("{\"id\": \"invalid-id-format\", \"title\": \"Book Title\", \"author\": \"Author Name\"}")
                .post(BASE_URL+"/books");
    }


    @When("I send a request to create a book with invalid title or author format")
    public void iSendARequestToCreateABookWithInvalidTitleOrAuthorFormat() {
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(
                Serenity.sessionVariableCalled("username"),
                Serenity.sessionVariableCalled("password")
        );

        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"123@Invalid\", \"author\": \"Invalid#456\"}")
                .post(BASE_URL+"/books");
    }


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

    @Given("a book with ID {int}, title {string} and author {string} already exists")
    public void aBookWithIDTitleAndAuthorAlreadyExists(int id, String title, String author) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Create the payload for the book with a specific ID
        String payload = String.format("{\"id\": %d, \"title\": \"%s\", \"author\": \"%s\"}", id, title, author);

        // Send a POST request to create the book
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL + "/books");
    }

    @When("I send a POST request to create a book with ID {int}, title {string} and author {string}")
    public void iSendAPOSTRequestToCreateABookWithIDTitleAndAuthor(int id, String title, String author) {
        // Retrieve normal user credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Create the payload for the duplicate book ID
        String payload = String.format("{\"id\": %d, \"title\": \"%s\", \"author\": \"%s\"}", id, title, author);

        // Send a POST request to create the book
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL + "/books");
    }

}
