package starter.steps;

import io.cucumber.java.en.And;
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


public class CreateBookSteps {
    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

    private Response response;

    @When("I send a POST request to create a book with empty title or author")
    public void iSendAPostRequestToCreateABookWithEmptyTitleOrAuthor() {
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(
                Serenity.sessionVariableCalled("username"),
                Serenity.sessionVariableCalled("password")
        );

        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"\", \"author\": \"\"}")
                .post(BASE_URL);
    }

    @Then("the response status code should be 400 error")
    public void theResponseStatusCodeShouldBe400Error() {
        restAssuredThat(response -> response.statusCode(400));
    }

    @And("the response should contain validation errors")
    public void theResponseShouldContainValidationErrors() {
        SerenityRest.lastResponse().then()
                .body("errors.title", equalTo("Title is required"))
                .body("errors.author", equalTo("Author is required"));
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
                .post(BASE_URL);
    }

    @And("the response message should indicate invalid ID format")
    public void theResponseMessageShouldIndicateInvalidIDFormat() {
        SerenityRest.lastResponse().then()
                .body("message", equalTo("Invalid ID format"));
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
                .post(BASE_URL);
    }

    @And("the response message should indicate invalid title or author format")
    public void theResponseMessageShouldIndicateInvalidTitleOrAuthorFormat() {
        SerenityRest.lastResponse().then()
                .body("message", equalTo("Invalid title or author format"));
    }

    @When("I send a POST request to create a book with an existing title {string}")
    public void iSendAPOSTRequestToCreateABookWithAnExistingTitle(String title) {
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);

        String payload = String.format("{\"title\": \"%s\", \"author\": \"Author Name\"}", title);

        response = SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL + "/books");
    }

    @Then("the response status code should be 409 error")
    public void theResponseStatusCodeShouldBeConflict() {
        restAssuredThat(response -> response.statusCode(409));
    }

    @And("the response message should indicate that the book already exists")
    public void theResponseMessageShouldIndicateThatTheBookAlreadyExists() {
        response.then().body("message", equalTo("Book already exists"));
    }
}
