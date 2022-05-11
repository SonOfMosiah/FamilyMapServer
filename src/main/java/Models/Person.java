package Models;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for a Person
 * Used for data in the family map
 */
public class Person {
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


    public String getPersonID() {
        return personID;
    }

    /**
     * Person Constructor w/o parameters
     * Initializes all variables as null
     */
    public Person() {
        this.personID = UUID.randomUUID().toString();
        this.associatedUsername = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
    }

    /**
     * Person Constructor
     * @param associatedUsername The Person's unique username
     * @param firstName The Person's first name
     * @param lastName The Person's last name
     * @param gender The Person's Gender (M || F)
     */
    public Person(String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = UUID.randomUUID().toString();
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Person Constructor
     * @param associatedUsername The Person's unique username
     * @param firstName The Person's first name
     * @param lastName The Person's last name
     * @param gender The Person's Gender (M || F)
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Person Constructor
     * @param personID The Person's unique personID
     * @param associatedUsername The Person's unique username
     * @param firstName The Person's first name
     * @param lastName The Person's last name
     * @param gender The Person's Gender (M || F)
     * @param fatherID The Person's fatherID
     * @param motherID The Person's motherID
     * @param spouseID The Person's spouseID
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID ) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    /**
     * Person Constructor with a User object as a parameter
     * @param user The User Object associated with the Person
     */
    public Person(User user) {
        this.personID = UUID.randomUUID().toString();
        this.associatedUsername = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
    }

    /**
     * Set the PersonID
     * @param personID The personID for the Person
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Get the Username
     * @return String
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Set the Username
     * @param associatedUsername The Username (String) for the Person
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * Get the First Name
     * @return String firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the First Name
     * @param firstName The First Name (String) for the Person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the Last Name
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the Last Name
     * @param lastName The Last Name (String) for the Person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the Gender
     * @return String Gender ( F || M)
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the Gender
     * @param gender The Gender (M || F) for the Person
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the FatherID
     * @return String fatherID
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * Set the Father ID
     * @param fatherID The fatherID (String) for the Person
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * Get the MotherID
     * @return String motherID
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * Set the MotherID
     * @param motherID The motherID (String) for the Person
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * Get the SpouseID
     * @return String spouseID
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * Set the SpouseID
     * @param spouseID The spouseID (String) for the Person
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    /**
     * Overrides the equals() method.
     * @param o Person Object
     * @return True if object properties are equal. False if object properties are not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Person) {
            Person oPerson = (Person) o;
            if (oPerson.getFatherID() != null &&
            oPerson.getMotherID() != null &&
            oPerson.getSpouseID() != null) {
                return oPerson.getPersonID().equals(getPersonID()) &&
                        oPerson.getAssociatedUsername().equals(getAssociatedUsername()) &&
                        oPerson.getFirstName().equals(getFirstName()) &&
                        oPerson.getLastName().equals(getLastName()) &&
                        oPerson.getGender().equals(getGender()) &&
                        oPerson.getFatherID().equals(getFatherID()) &&
                        oPerson.getMotherID().equals(getMotherID()) &&
                        oPerson.getSpouseID().equals(getSpouseID());
            } else {
                return oPerson.getPersonID().equals(getPersonID()) &&
                        oPerson.getAssociatedUsername().equals(getAssociatedUsername()) &&
                        oPerson.getFirstName().equals(getFirstName()) &&
                        oPerson.getLastName().equals(getLastName()) &&
                        oPerson.getGender().equals(getGender());
            }
        } else {
            return false;
        }
    }

    /**
     * Overrides the hashCode() method
     * @return a has of all Person object variables.
     */
    @Override
    public int hashCode() {
        return Objects.hash(personID, firstName, lastName, gender, fatherID, motherID, spouseID);
    }
}
