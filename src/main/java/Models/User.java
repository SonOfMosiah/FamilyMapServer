package Models;

/**
 * Model for a User of the program
 */
public class User {

    /**
     * Unique username for the user
     */
    private String username; // Primary Key
    /**
     * Password for the user (String)
     */
    private String password;
    /**
     * Email Address for the user
     */
    private String email;
    /**
     * User first name
     */
    private String firstName;
    /**
     * User last name
     */
    private String lastName;
    /**
     * User's gender -- F || M
     */
    private String gender; // F || M
    /**
     * Unique personId for the user
     */
    private String personID; // Foreign Key

    /**
     * Get the Username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username
     * @param username The Username (String) for the User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     * @param password The password (String) for the User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the email address
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address
     * @param email The email address (String) for the User
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user First Name
     * @return String firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user First Name
     * @param firstName The First Name (String) for the User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user last name
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user last name
     * @param lastName The Last Name (String) for the User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user's gender
     * @return String gender (F || M)
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the user's gender
     * @param gender The Gender (M || F) for the User
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the user's person ID
     * @return String personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Set the user's personID
     * @param personID The personID (String) for the User
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * User Constructor w/o parameters
     * Sets all variables to null.
     */
    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.personID = null;
    }

    /**
     * User Constructor
     * @param username String
     * @param password String
     * @param email String
     * @param firstName String
     * @param lastName String
     * @param gender String
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = new Person(username, password, firstName, lastName).getPersonID();
    }

    /**
     * User Constructor
     * @param username String
     * @param password String
     * @param email String
     * @param firstName String
     * @param lastName String
     * @param gender String
     * @param personID String
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Overrides the equals() method.
     * @param o User Object
     * @return True if object properties are equal. False if object properties are not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof User) {
            User oUser = (User) o;
            return oUser.getUsername().equals(getUsername()) &&
                    oUser.getPassword().equals(getPassword()) &&
                    oUser.getEmail().equals(getEmail()) &&
                    oUser.getFirstName().equals(getFirstName()) &&
                    oUser.getLastName().equals(getLastName()) &&
                    oUser.getGender().equals(getGender()) &&
                    oUser.getPersonID().equals(getPersonID());
        } else {
            return false;
        }
    }
}
