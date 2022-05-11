package Result;

import Models.Authtoken;

/**
 * Class for returning Results of Register API call.
 */
public class RegisterResult {

    private String authtoken;
    private String username;
    private String personID;
    private String associatedUsername;
    private String message;
    private boolean success;



    /**
     * Gets Authorization Token
     * @return String authToken
     */
    public String getAuthtoken() {
        return authtoken;
    }

    /**
     * Sets Authorization Token
     * @param authtoken String authToken
     */
    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Gets User's username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets User's username
     * @param username String username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Gets User's associatedUsername
     * @return String associatedUsername
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Sets user's associatedUsername
     * @param associatedUsername String associatedUsername
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * Gets Result
     * @return String result
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets Result
     * @param message String result
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * RegisterResult Constructor if login is successful
     * @param token
     * @param username
     * @param associatedUsername
     */
    public RegisterResult(String token, String username, String associatedUsername) {
        this.success = true;
        this.authtoken = token;
        this.username = username;
        this.associatedUsername = associatedUsername;
        this.message = null;
    }

    /**
     * RegisterResult Constructor if login is successful
     * @param authtoken AuthToken
     */
    public RegisterResult(Authtoken authtoken) {
        this.success = true;
        this.authtoken = authtoken.getAuthtoken();
        this.username = authtoken.getUsername();
        this.associatedUsername = authtoken.getAssociatedUsername();
        this.message = null;
    }

    /**
     * RegisterResult Constructor if login is unsuccessful
     * @param err String containing Error Message
     */
    public RegisterResult(boolean success, String err) {
        this.authtoken = null;
        this.username = null;
        this.associatedUsername = null;
        this.message = err;
        this.success = success;
    }

    /**
     * RegusterResult Constructor with parameters from Register Service class
     * @param success boolean
     * @param authtoken String
     * @param username String
     * @param personID String
     */
    public RegisterResult(boolean success, String authtoken, String username, String personID) {
        this.success = success;
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }
}
