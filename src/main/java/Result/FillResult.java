package Result;

import Models.Event;
import Models.Person;

import java.util.ArrayList;

public class FillResult {

    private String message;
    private boolean success;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    /**
     * Failed FillResult Constructor
     * @param success boolean
     * @param message String
     */
    public FillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Successful FillResult Contructor
     * @param success boolean
     * @param persons ArrayList(Person)
     * @param events ArrayList(Event)
     */
    public FillResult(boolean success, ArrayList<Person> persons, ArrayList<Event> events) {
        this.success = success;
        this.message = "Successfully added " + persons.size() + " persons and " + events.size() + " events to the database.";
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
