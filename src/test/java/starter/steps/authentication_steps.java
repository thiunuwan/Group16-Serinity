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
        Serenity.setSessionVariable("normalUsername").to(normalUsername);
        Serenity.setSessionVariable("normalPassword").to(normalPassword);
    }

    @Given("I am an admin user")
    public void iAmAnAdminUser() {
        String adminUsername = "admin";
        String adminPassword = "password";

        // Store the admin credentials in Serenity session variables
        Serenity.setSessionVariable("adminUsername").to(adminUsername);
        Serenity.setSessionVariable("adminPassword").to(adminPassword);

    }
}
