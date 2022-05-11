package Result;

public class LoginResult {

    private String authtoken;
    private String username;
    private String personID;
    private String message;
    private boolean success;

    /**
     * LoginResult Constructor when login service is successful
     * @param authToken String
     * @param username String
     * @param personID String
     */
    public LoginResult(String authToken, String username, String personID) {
        this.authtoken = authToken;
        this.username = username;
        this.personID = personID;
        this.success = true;
    }

    /**
     * LoginResult Constructor when login service is successful w/ success parameter
     * @param success Boolean
     * @param authToken String
     * @param username String
     * @param personID String
     */
    public LoginResult(boolean success, String authToken, String username, String personID) {
        this.success = success;
        this.authtoken = authToken;
        this.username = username;
        this.personID = personID;
    }

    /**
     * LoginResult Constructor when login service fails
     * @param success Boolean
     * @param message String error message
     */
    public LoginResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() { return success; }

    public void setSuccess(Boolean success) { this.success = success; }
}
