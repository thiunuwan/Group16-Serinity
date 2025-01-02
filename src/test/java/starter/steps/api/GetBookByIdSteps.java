package starter.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import starter.utils.AuthUtils;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import starter.hooks.BookLifecycleHooks;

public class GetBookByIdSteps extends BaseSteps {

    // Retrieve BASE_URL from configuration
//    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
//    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

    private final int testBookId = BookLifecycleHooks.getTestBookId();
    private  final String testBookTitle = BookLifecycleHooks.getTestBookTitle();
    private  final String testBookAuthor = BookLifecycleHooks.getTestBookAuthor();

    @When("I send a GET request to retrieve the test book")
    public void iSendAGETRequestTo() {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        System.out.println("LOG: BASE_URL: " + BASE_URL);

        System.out.println("send get request to retrieve book" + testBookId);

        // Send GET request to retrieve the book with ID 1
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .get(BASE_URL +"/books/"+testBookId);
    }

//        @Then("the response status code should be {int}")
//    public void theResponseStatusCodeShouldBe(int statusCode) {
//        restAssuredThat(response -> response.statusCode(statusCode));
//    }


    @And("the response should contain the book details for testBook")
    public void theResponseShouldContainTheBookDetailsForID() {
        // Get the response from SerenityRest
        Response response = SerenityRest.lastResponse();

        // Assert that the response contains the expected book ID
        response.then().body("id", equalTo(testBookId));

        // Optionally, verify additional book details like title, author, etc.
        response.then().body("title", notNullValue());
        response.then().body("author", notNullValue());
        response.then().body("title", equalTo(testBookTitle));  // Check the book title
        response.then().body("author", equalTo(testBookAuthor));  // Check the book author
    }

    @When("I send a GET request to retrieve a book with a non-existent ID {int}")
    public void iSendAGETRequestToRetrieveABookWithANonExistentIDBook_id(int nonExistentBookID) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);


        // Send GET request to retrieve the book with ID 1
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .get(BASE_URL +"/books/"+nonExistentBookID);
    }

    @And("the response should contain the error message {string}")
    public void theResponseShouldContainTheErrorMessage(String expectedErrorMessage) {
        // Retrieve the response body as a String
        Response response = SerenityRest.lastResponse();
        String responseBody = response.getBody().asString();

        // Verify that the response body contains the expected error message
        assertTrue("Response body should contain the error message: " + expectedErrorMessage,
                responseBody.contains(expectedErrorMessage));
    }
}
