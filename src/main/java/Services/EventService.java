package Services;

import DataAccess.*;
import Models.Authtoken;
import Models.Event;
import Result.EventByIDResult;
import Result.EventResult;
import java.util.ArrayList;

public class EventService {

    private EventDAO eventDAO;
    private AuthtokenDAO authtokenDAO;

    /**
     * Finds all events for a user
     * @param authtoken Authtoken object
     * @return EventResults object
     */
    public EventResult events(String authtoken) throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();
            authtokenDAO = db.getAuthtokenDAO();
            Authtoken token = authtokenDAO.find(authtoken);
            String associatedUsername;
            if (token != null) {
                associatedUsername = token.getAssociatedUsername();
            }
            else {
                db.closeConnection(false);
                return new EventResult(false, "Error: No events found");
            }
            eventDAO = db.getEventDAO();
            ArrayList<Event> events = eventDAO.find(associatedUsername);
            if (events == null) {
                db.closeConnection(false);
                return new EventResult(false, "Error: No events found");
            }
            db.closeConnection(true);
            return new EventResult(true, events);
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            return new EventResult(false, "Error message");
        }
    }

    /**
     * Finds an Event by its eventID for a given user
     * @param eventID String
     * @param authToken String
     * @return EventByIDResult Object
     */
    public EventByIDResult eventByID(String eventID, String authToken) throws DataAccessException {
        Database db = new Database();
        db.openConnection();
        authtokenDAO = db.getAuthtokenDAO();
        eventDAO = db.getEventDAO();
        try {
            Authtoken authtoken = authtokenDAO.find(authToken);
            if (authtoken == null) {
                db.closeConnection(false);
                return new EventByIDResult(false, "Error: Invalid AuthToken");
            }
            Event event = eventDAO.findByID(eventID);
            if (!authtoken.getUsername().equals(event.getUsername())) {
                db.closeConnection(false);
                return new EventByIDResult(false, "Error: Event not registered under current user");
            }
            db.closeConnection(true);
            return new EventByIDResult(true, event);
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            return new EventByIDResult(false, "Error message");
        }
    }
}
