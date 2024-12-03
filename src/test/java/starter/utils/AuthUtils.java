package starter.utils;

import java.util.Base64;

public class AuthUtils {

    /**
     * Generates a Basic Authorization header value.
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return A Basic Authorization header value.
     */
    public static String generateBasicAuthHeader(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }
        String authValue = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(authValue.getBytes());
    }
}
