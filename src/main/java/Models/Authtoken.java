package Models;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for Authorization Tokens
 */
public class Authtoken {
    private String authtoken; // primary key
    private String username; // foreign key
    private String associatedUsername; // foreign key

    /**
     * AuthToken Constructor
     * @param username String
     * @param associatedUsername String
     */
    public Authtoken(String username, String associatedUsername) {
        this.authtoken = UUID.randomUUID().toString();
        this.username = username;
        this.associatedUsername = associatedUsername;
    }

    /**
     * AuthToken Constructor
     * @param authToken String
     * @param username String
     * @param associatedUsername String
     */
    public Authtoken(String authToken, String username, String associatedUsername) {
        this.authtoken = authToken;
        this.username = username;
        this.associatedUsername = associatedUsername;
    }

    /**
     * Get the Authorization Token
     * @return String authToken
     */
    public String getAuthtoken() {
        return authtoken;
    }

    /**
     * Set the Authorization Token
     * @param authtoken String
     */
    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Get the Username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the Username
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the associatedUsername
     * @return String associatedUsername
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Set the associatedUsername
     * @param associatedUsername String
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * Overrides the equals() method.
     * @param o AuthToken Object
     * @return True if object properties are equal. False if object properties are not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Authtoken) {
            Authtoken oAuthtoken = (Authtoken) o;
            return oAuthtoken.getAuthtoken().equals(getAuthtoken()) &&
                    oAuthtoken.getUsername().equals(getUsername()) &&
                    oAuthtoken.getAssociatedUsername().equals(getAssociatedUsername());
        } else {
            return false;
        }
    }

    /**
     * Overrides the hashCode() method
     * @return a has of all AuthToken object variables.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, associatedUsername, authtoken);
    }
}
