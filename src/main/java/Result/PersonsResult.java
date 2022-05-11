package Result;

import Models.Person;

import java.util.ArrayList;

public class PersonsResult {
    /**
     * Array of Person Objects
     */
    private ArrayList<Person> data;

    /**
     * success is a boolean variable to indicate the status of the Service class
     */
    private boolean success;

    /**
     * Relevant error message
     */
    private String message;

    /**
     * PersonResult Constructor with String Parameter
     * Failure
     * @param message
     */
    public PersonsResult(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    /**
     * PersonResult Constructor with ArrayList(Person) Parameter
     * Success
     * @param data
     */
    public PersonsResult(boolean success, ArrayList data) {
        this.success = success;
        this.data = data;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
