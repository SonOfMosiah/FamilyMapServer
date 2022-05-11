package Request;

public class EventRequest {
    String authToken;

    /**
     * EventRequest constructor
     * @param authToken String
     */
    public EventRequest(String authToken){
        this.authToken = authToken;
    }

    /**
     * Get authToken
     * @return String authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set authToken
     * @param authToken String
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
