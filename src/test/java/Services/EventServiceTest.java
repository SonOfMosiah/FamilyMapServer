package Services;

import DataAccess.*;
import Models.*;
import Request.EventRequest;
import Result.EventByIDResult;
import Result.EventResult;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class EventServiceTest {
    private static ClearService clearService = new ClearService();
    private static EventService service = new EventService();
    private static EventRequest request;
    private static EventResult eventResult;
    private static EventByIDResult eventByIDResult;
    private static UserDAO userDAO;
    private static AuthtokenDAO authTokenDAO;
    private static EventDAO eventDAO;
    private static User user;
    private static Person person;
    private static Event event;
    private static Authtoken authToken;
    private static Database db = new Database();


    @BeforeEach
    @DisplayName("Setup")
    public void setUp() {
        try {
            clearService.clearDatabase();
            db.openConnection();
            authTokenDAO = db.getAuthtokenDAO();
            authToken = new Authtoken("username", "username");
            authTokenDAO.insert(authToken);
            db.closeConnection(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Successful Event By ID Test")
    public void eventByIDTest() throws DataAccessException {
        db.openConnection();
        eventDAO = db.getEventDAO();
        person = new Person(authToken.getUsername(), "first", "last", "m");
        event = new Event("eventID", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        eventDAO.insert(event);
        db.closeConnection(true);
        eventByIDResult = service.eventByID(event.getEventID(), authToken.getAuthtoken());

        Assertions.assertNotNull(eventByIDResult.getAssociatedUsername());
        Assertions.assertNotNull(eventByIDResult.getCity());
        Assertions.assertNotNull(eventByIDResult.getCountry());
        Assertions.assertNotNull(eventByIDResult.getEventType());
        Assertions.assertNotNull(eventByIDResult.getEventID());
        Assertions.assertEquals(1999, eventByIDResult.getYear());
        Assertions.assertEquals(100, eventByIDResult.getLatitude());
        Assertions.assertEquals(10, eventByIDResult.getLongitude());
        Assertions.assertNull(eventByIDResult.getMessage());
        Assertions.assertEquals(eventByIDResult.getAssociatedUsername(), "username");
    }

    @Test
    @DisplayName("Failed Event Test -- Invalid AuthToken")
    public void failedEventByIDTest() throws DataAccessException {
        db.openConnection();
        eventDAO = db.getEventDAO();
        person = new Person(authToken.getUsername(), "first", "last", "m");
        event = new Event("eventID", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        eventDAO.insert(event);
        db.closeConnection(true);
        eventByIDResult = service.eventByID(event.getEventID(), "x" + authToken.getAuthtoken());

        Assertions.assertNull(eventByIDResult.getAssociatedUsername());
        Assertions.assertNull(eventByIDResult.getCity());
        Assertions.assertNull(eventByIDResult.getCountry());
        Assertions.assertNull(eventByIDResult.getEventType());
        Assertions.assertNull(eventByIDResult.getEventID());
        Assertions.assertEquals("Error: Invalid AuthToken", eventByIDResult.getMessage());
    }

    @Test
    @DisplayName("Successful Event Test")
    public void eventTest() throws DataAccessException {
        db.openConnection();
        eventDAO = db.getEventDAO();
        person = new Person(authToken.getUsername(), "first", "last", "m");

        event = new Event("eventID", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event2 = new Event("eventID2", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event3 = new Event("eventID3", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event4 = new Event("eventID4", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);

        eventDAO.insert(event);
        eventDAO.insert(event2);
        eventDAO.insert(event3);
        eventDAO.insert(event4);

        ArrayList<Event> events = new ArrayList<Event>();
        events.add(event);
        events.add(event2);
        events.add(event3);
        events.add(event4);

        db.closeConnection(true);
        eventResult = service.events(authToken.getAuthtoken());

        Assertions.assertEquals(events.get(0), eventResult.getData().get(0));
        Assertions.assertEquals(events.get(1), eventResult.getData().get(1));
        Assertions.assertEquals(events.get(2), eventResult.getData().get(2));
        Assertions.assertEquals(events.get(3), eventResult.getData().get(3));
    }

    @Test
    @DisplayName("Failed Event Test -- Wrong AuthToken")
    public void failedEventTest() throws DataAccessException {
        db.openConnection();
        eventDAO = db.getEventDAO();
        person = new Person(authToken.getUsername(), "first", "last", "m");

        event = new Event("eventID", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event2 = new Event("eventID2", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event3 = new Event("eventID3", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event4 = new Event("eventID4", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);

        eventDAO.insert(event);
        eventDAO.insert(event2);
        eventDAO.insert(event3);
        eventDAO.insert(event4);

        ArrayList<Event> events = new ArrayList<Event>();
        events.add(event);
        events.add(event2);
        events.add(event3);
        events.add(event4);

        db.closeConnection(true);
        eventResult = service.events("x" + authToken.getAuthtoken());

        Assertions.assertNull(eventResult.getData());
        Assertions.assertEquals("Error: No events found", eventResult.getMessage());
    }
}
