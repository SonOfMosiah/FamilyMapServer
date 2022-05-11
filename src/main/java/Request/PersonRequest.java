package Request;

/**
 * Handles /person api calls.
 */
public class PersonRequest {
    String authToken;

    /**
     * PersonRequest Constructor
     * @param authToken String
     */
    public PersonRequest(String authToken) {
        this.authToken = authToken;
    }
}
