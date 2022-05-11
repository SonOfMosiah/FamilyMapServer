package Generator;

import Models.Event;
import Models.Person;

import java.util.ArrayList;

public class Generation {
    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    /**
     * Generation Constructor
     * @param persons Person Array
     * @param events Event Array
     */
    public Generation(ArrayList<Person> persons, ArrayList<Event> events) {
        this.persons = persons;
        this.events = events;
    }

    public ArrayList<Person> getPersons() { return persons; }
    public void setPersons(ArrayList<Person> persons) { this.persons = persons; }
    public ArrayList<Event> getEvents() { return events; }
    public void setEvents(ArrayList<Event> events) { this.events = events;}
}
