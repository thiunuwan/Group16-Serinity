package starter.steps.api;

import io.cucumber.java.en.Then;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class UtilSteps {

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

}
