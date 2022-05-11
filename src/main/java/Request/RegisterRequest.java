package Request;

/**
 * Handles /user/register api calls.
 */
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * RegisterRequest Constructor w/o parameters
     */
    public RegisterRequest() {
        username = null;
        password = null;
        email = null;
        firstName = null;
        lastName = null;
        gender = null;
        personID = null;
    }

    /**
     * RegisterRequest with parameters
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password,
                           String email, String firstName,
                           String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = null;
    }

    /**
     * Get username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get email
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set Email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get firstName
     * @return String firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get lastName
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get Gender
     * @return String gender ( F || M )
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set Gender ( F || M )
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get personID
     * @return String personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Set personID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
