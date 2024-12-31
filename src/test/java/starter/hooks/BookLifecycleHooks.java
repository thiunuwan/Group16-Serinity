package starter.hooks;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.Getter;
import net.serenitybdd.rest.SerenityRest;

public class BookLifecycleHooks {

    private static final String BASE_URL = System.getProperty("api.base.url", "http://localhost:8080/api");
    @Getter
    private static int testBookId;
    @Getter
    private static String testBookTitle;
    @Getter
    private static String testBookAuthor;
    private final Faker faker = new Faker();


    @Before("@createTestBook")
    public void createTestBook() {
        // Generate random book details using Faker
        testBookTitle = faker.book().title();
        testBookAuthor = faker.book().author();

        // Basic authentication credentials
        String username = "admin";
        String password = "password";

        // Create a test book via API with dynamic title, author, and basic authentication
        testBookId = SerenityRest.given()
                .auth()
                .basic(username, password) // Set Basic Authentication
                .contentType("application/json")
                .body("{ \"title\": \"" + testBookTitle + "\", \"author\": \"" + testBookAuthor + "\" }")
                .post(BASE_URL + "/books")
                .then()
                .statusCode(201) // Validate creation
                .extract()
                .path("id"); // Extract the ID for later use
    }

    @After("@createTestBook")
    public void deleteTestBook() {
        // Basic authentication credentials
        String username = "user"; // since access rights are swapped
        String password = "password";

        // Delete the test book via API with basic authentication
        SerenityRest.given()
                .auth()
                .basic(username, password)  // Set Basic Authentication
                .delete("http://localhost:8080/api/books/" + testBookId);
    }


}
