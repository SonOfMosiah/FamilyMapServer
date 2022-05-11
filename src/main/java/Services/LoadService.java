package Services;

import DataAccess.*;
import Models.Event;
import Models.Person;
import Models.User;
import Request.LoadRequest;
import Result.LoadResult;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Service class for loading data into application
 */
public class LoadService {

    private final Database db;

    public LoadService() {
        db = new Database();
    }

    /**
     * Clears all data from the database and then loads the posted user, person, and event data into the database.
     * @param request LoadRequest object
     * @return LoadResult object
     */
    public LoadResult load(LoadRequest request) throws DataAccessException {
        LoadResult result;
        try {
            db.openConnection();
            UserDAO userDAO = db.getUserDAO();
            userDAO.clear();
            PersonDAO personDAO = db.getPersonDAO();
            personDAO.clear();
            EventDAO eventDAO = db.getEventDAO();
            eventDAO.clear();
            AuthtokenDAO authTokenDAO = db.getAuthtokenDAO();
            authTokenDAO.clear();

            User[] userArray = request.getUsers();
            Person[] personArray = request.getPersons();
            Event[] eventsArray = request.getEvents();

            ArrayList<User> users = new ArrayList<>(Arrays.asList(userArray));
            ArrayList<Person> persons = new ArrayList<>(Arrays.asList(personArray));
            ArrayList<Event> events = new ArrayList<>(Arrays.asList(eventsArray));

            if (users.size() > 0 || persons.size() > 0 || events.size() > 0) {
                for (User user : users) {
                    userDAO.insert(user);
                }

                for (Person person : persons) {
                    personDAO.insert(person);
                }

                for (Event event : events) {
                    eventDAO.insert(event);
                }
            } else {
                result = new LoadResult(false, "Error: Need data to load");
                db.closeConnection(false);
                return result;
            }

            result = new LoadResult(true, "Successfully added " + users.size() + " users, " + persons.size() +
                    " persons, and " + events.size() + " events to the database.");
            db.closeConnection(true);
        } catch (DataAccessException e) {
            result = new LoadResult(false, "Error: " + e.getMessage());
            try {
                db.closeConnection(false);
            } catch (DataAccessException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
