package starter.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import starter.hooks.BookLifecycleHooks;
import starter.utils.AuthUtils;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.junit.Assert.assertTrue;

public class UpdateBookByIdSteps {
    // Retrieve BASE_URL from configuration
    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");
    private static final int testBookId = BookLifecycleHooks.getTestBookId();
    @When("I send a PUT request to update the test book")
    public void iSendAPUTRequestToUpdateTheTestBook() {
        // Update admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // JSON body for the PUT request
        String requestBody = "{"
                + "\"title\": \"Updated Book Title\","
                + "\"author\": \"Updated Author Name\","
                + "}";

        // Send PUT request to update the book with ID 1
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .body(requestBody)
                .put(BASE_URL +"/books/"+ testBookId);
    }
    @When("I send a PUT request to update a book with a non-existent ID {int}")
    public void iSendAPUTRequestToUpdateABookWithANonExistentIDBook_id(int nonExistentBookID) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // JSON body for the PUT request
        String requestBody = "{"
                + "\"title\": \"Non-existent Book Title\","
                + "\"author\": \"Non-existent Author Name\","
                + "}";
        // Send PUT request to update the book with ID 1
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .body(requestBody)
                .put(BASE_URL +"/books/"+nonExistentBookID);
    }
    @When("I send a PUT request to update the test book with missing mandatory fields")
    public void iSendAPUTRequestToUpdateTheTestBookWithMissingMandatoryFields() {
        // Update admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // JSON body for the PUT request with missing mandatory fields
        String requestBody = "{}"; // Empty JSON body, simulating missing fields

        // Send PUT request to update the book
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .body(requestBody)
                .put(BASE_URL + "/books/" + testBookId);
    }
}
