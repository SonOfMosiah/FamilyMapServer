package Result;

import Models.Person;

public class PersonResult {
    /**
     * The primary key of the Person class.
     */
    private String personID; // Primary Key
    /**
     * username is a foreign key from the User class.
     */
    private String associatedUsername; // Foreign Key ( One to One)
    /**
     * firstName
     */
    private String firstName;
    /**
     * lastName
     */
    private String lastName;
    /**
     * Gender can be either 'F' or 'M'
     */
    private String gender; // F || M
    /**
     * fatherId is the personId of the father. (Can be null) Foreign Key.
     */
    private String fatherID; // Foreign Key ( One to One)
    /**
     * motherId is the personId of the mother. (Can be null) Foreign Key.
     */
    private String motherID; // Foreign Key ( One to One)
    /**
     * spouseId is the personId of the spouse. (Can be null) Foreign Key.
     */
    private String spouseID; // Foreign Key ( One to One)

    /**
     * success is a boolean variable to indicate the status of the Service class
     */
    private boolean success;

    /**
     * Relevant error message
     */
    private String message;

    /**
     * PersonByID Constructor with String Parameter
     * Constructor for Error
     * @param success Boolean
     * @param message String
     */
    public PersonResult(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    /**
     * PersonByID Constructor with Person object Parameter
     * Constructor for Success
     * @param person Person Object
     */
    public PersonResult(Person person) {
        this.success = true;
        this.personID = person.getPersonID();
        this.associatedUsername = person.getAssociatedUsername();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
    }

    /**
     * PersonByID Constructor w/o parameters
     */
    public PersonResult() { }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
