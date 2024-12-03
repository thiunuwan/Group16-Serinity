package starter.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import starter.utils.AuthUtils;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;


public class GetBookByIdSteps {

    private static final String BASE_URL = "http://localhost:8080/api";


    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String path) {
        // Retrieve admin credentials from session variables
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");

        // Generate Basic Auth header
        String basicAuthHeader = AuthUtils.generateBasicAuthHeader(username, password);


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
