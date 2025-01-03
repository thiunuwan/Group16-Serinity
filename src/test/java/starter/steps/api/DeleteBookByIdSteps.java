package starter.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import starter.hooks.BookLifecycleHooks;
import starter.utils.AuthUtils;

import static org.junit.Assert.assertTrue;

public class DeleteBookByIdSteps extends BaseSteps{
    // Retrieve BASE_URL from configuration

    private  final int testBookId = BookLifecycleHooks.getTestBookId();

    @When("I send a DELETE request to delete the test book")
    public void iSendADELETERequestToDeleteTheTestBook() {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Send DELETE request to delete the test book by ID
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .delete(BASE_URL + "/books/" + testBookId);
    }

    @And("the response should contain the success message {string}")
    public void theResponseShouldContainTheSuccessMessage(String expectedMessage) {
        // Retrieve the response body as a String
        Response response = SerenityRest.lastResponse();
        String responseBody = response.getBody().asString();

        // Verify that the response body contains the expected error message
        assertTrue("Response body should contain the success message: " + expectedMessage,
                responseBody.contains(expectedMessage));
    }

    @When("I send a DELETE request to delete a book with a non-existent ID {int}")
    public void iSendADELETERequestToDeleteABookWithANonExistentIDBook_id(int nonExistingBookId) {
        // Retrieve credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        // Send DELETE request to delete the book by ID
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .delete(BASE_URL + "/books/" + nonExistingBookId);
    }

}
