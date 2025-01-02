package starter.steps.api;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.Serenity;

public class authentication_steps {
    @Given("I am a normal user")
    public void iAmANormalUser() {

        String normalUsername = "user";
        String normalUserPassword = "password";
        setUserCredentials(normalUsername, normalUserPassword);

    }

    @Given("I am a admin user")
    public void iAmAnAdminUser() {
        String adminUsername = "admin";
        String adminUserPassword = "password";
        setUserCredentials(adminUsername, adminUserPassword);

    }

    // Helper method to set credentials
    private void setUserCredentials(String username, String password) {
        Serenity.setSessionVariable("username").to(username);
        Serenity.setSessionVariable("password").to(password);
    }

}
