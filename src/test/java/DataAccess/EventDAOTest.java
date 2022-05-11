package DataAccess;

import Models.Event;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class EventDAOTest {

    private static EventDAO eventDAO;
    private static Database database;

    @BeforeAll
    @DisplayName("Setup")
    public static void setUp() {
        try {
            database = new Database();
            database.openConnection();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    @DisplayName("TearDown")
    public static void tearDown() {
        try {
            database.closeConnection(false);
            eventDAO = null;
            database = null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    @DisplayName("CleanUp")
    public void cleanTests() throws DataAccessException {
        try {
            eventDAO = new EventDAO(database.getConnection());
            eventDAO.clear();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert Event Test")
    public void InsertEventTest() {

        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            eventDAO.insert(event);
            Event foundEvent = eventDAO.findByID(event.getEventID());
            Assertions.assertEquals(foundEvent, event);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert Duplicate Token Test")
    public void InsertDuplicateTokenTest() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                    "country", "city", "eventType", 1999);
            eventDAO.insert(event);
            eventDAO.insert(event);
        });
    }

    @Test
    @DisplayName("Find Event")
    public void FindEventTest() {
        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            Assertions.assertNull(eventDAO.find("username"));
            eventDAO.insert(event);
            ArrayList<Event> foundEvent = eventDAO.find("username");
            Assertions.assertEquals(foundEvent.get(0), event);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Find Event By ID")
    public void FindEventByIDTest() {
        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            Assertions.assertNull(eventDAO.findByID(event.getEventID()));
            eventDAO.insert(event);
            Event foundEvent = eventDAO.findByID(event.getEventID());
            Assertions.assertEquals(foundEvent, event);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong Event")
    public void FindWrongEventTest() {
        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            Assertions.assertNull(eventDAO.find("username"));
            eventDAO.insert(event);
            Assertions.assertNull(eventDAO.find("xusername"));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong Event By ID")
    public void FindWrongEventByIDTest() {
        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            Assertions.assertNull(eventDAO.findByID("eventID"));
            eventDAO.insert(event);
            Assertions.assertNull(eventDAO.findByID("xeventID"));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Clear Event Table")
    public void ClearEventTableTest() {
        Event event = new Event("eventID", "username", "personID", 100.2f, 37.2f,
                "country", "city", "eventType", 1999);
        try {
            Assertions.assertNull(eventDAO.findByID(event.getEventID()));
            eventDAO.insert(event);
            Event foundEvent = eventDAO.findByID(event.getEventID());
            Assertions.assertEquals(foundEvent, event);
            Assertions.assertTrue(eventDAO.clear());
            Assertions.assertNull(eventDAO.findByID(event.getEventID()));

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

}
