package starter.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;


public class GetBookByIdSteps {

    private static final String BASE_URL = "http://localhost:8080/api";

    // A helper method to generate the Basic Auth header
    private String generateBasicAuthHeader(String username, String password) {
        String authValue = username + ":" + password;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(authValue.getBytes());
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String path) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("Username");
        String password = Serenity.sessionVariableCalled("Password");

        // Generate Basic Auth header
        String basicAuthHeader = generateBasicAuthHeader(username, password);

        // Send GET request to retrieve the book with ID 1
        SerenityRest.given()
                .header("Authorization", basicAuthHeader)
                .get(BASE_URL + path);
    }


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

//    @And("the response should contain the book details for ID {int}")
//    public void theResponseShouldContainTheBookDetailsForID(int arg0) {
//
//    }

}
