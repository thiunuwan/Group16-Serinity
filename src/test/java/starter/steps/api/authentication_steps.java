package starter.steps.api;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class authentication_steps {

    protected final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    protected final String normalUsername = environmentVariables.getProperty("normalUsername");
    protected final String normalPassword = environmentVariables.getProperty("normalPassword");
    protected final String adminUsername = environmentVariables.getProperty("adminUsername");
    protected final String adminPassword = environmentVariables.getProperty("adminPassword");



    @Given("I am a normal user")
    public void iAmANormalUser() {
        // Set the credentials for normal user
//        String normalUsername = "user";
//        String normalPassword = "password";

        // Store the normal user credentials in Serenity session variables
        Serenity.setSessionVariable("username").to(normalUsername);
        Serenity.setSessionVariable("password").to(normalPassword);
    }

    @Given("I am a admin user")
    public void iAmAnAdminUser() {
//        String adminUsername = "admin";
//        String adminPassword = "password";

        // Store the admin credentials in Serenity session variables
        Serenity.setSessionVariable("username").to(adminUsername);
        Serenity.setSessionVariable("password").to(adminPassword);

    }


    @Given("I am an unauthorized user")
    public void iAmAnUnauthorizedUser() {

    }



}
