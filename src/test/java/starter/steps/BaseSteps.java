package starter.steps;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class BaseSteps {

    protected final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    protected final String BASE_URL = environmentVariables.getProperty("api.base.url", "http://localhost:8080/api");

}
