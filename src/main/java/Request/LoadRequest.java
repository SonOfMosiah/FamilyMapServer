package Request;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDAO;
import Models.Event;
import Models.Person;
import Models.User;

/**
 * Class that handles /load api calls
 */
public class LoadRequest {

    private final User[] users;
    private Person[] persons;
    private Event[] events;
    private final Database db = new Database();

    /**
     * LoadRequest Constructor
     * @param users User[]
     * @param persons Person[]
     * @param events Event[]
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Get userList
     * @return ArrayList(User) userList
     */
    public User[] getUsers() {
        return users;
    }

    /**
     * Get personList
     * @return ArrayList(Person) personList
     */
    public Person[] getPersons() {
        return persons;
    }

    public Person[] getPersons(String username) throws DataAccessException {
        db.openConnection();
        PersonDAO personDAO = db.getPersonDAO();
        return personDAO.find(username);
    }

    /**
     * Set personList
     * @param persons Person[]
     */
    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    /**
     * Get eventList
     * @return Event[] events
     */
    public Event[] getEvents() {
        return events;
    }

    /**
     * Set eventList
     * @param events Event[]
     */
    public void setEvents(Event[] events) {
        this.events = events;
    }
}
