package starter.steps;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.Serenity;

public class authentication_steps {
    @Given("I am a normal user")
    public void iAmANormalUser() {
        // Set the credentials for normal user
        String normalUsername = "user";
        String normalPassword = "password";

        // Store the normal user credentials in Serenity session variables
        Serenity.setSessionVariable("username").to(normalUsername);
        Serenity.setSessionVariable("password").to(normalPassword);
    }

    @Given("I am a admin user")
    public void iAmAnAdminUser() {
        String adminUsername = "admin";
        String adminPassword = "password";

        // Store the admin credentials in Serenity session variables
        Serenity.setSessionVariable("username").to(adminUsername);
        Serenity.setSessionVariable("password").to(adminPassword);

    }


    @Given("I am an unauthorized user")
    public void iAmAnUnauthorizedUser() {
        String unauthorizedUsername = "otherUser";
        String unauthorizedPassword = "otherPassword";

        Serenity.setSessionVariable("username").to(unauthorizedUsername);
        Serenity.setSessionVariable("password").to(unauthorizedPassword);
    }




}
