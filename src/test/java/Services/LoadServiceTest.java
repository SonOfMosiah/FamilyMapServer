package Services;

import DataAccess.DataAccessException;
import DataAccess.Database;
import Models.Event;
import Models.Person;
import Models.User;
import Request.LoadRequest;
import Result.LoadResult;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class LoadServiceTest {

    private static ClearService clearService = new ClearService();
    private static LoadService service = new LoadService();
    private static LoadRequest request;
    private static LoadResult result;
    private static Database db;

    @AfterAll
    @DisplayName("TearDown")
    public static void tearDown() {
        service = null;
        db = null;
    }


    @BeforeEach
    @DisplayName("Cleanup")
    public void cleanUp() {
        try {
            db = new Database();
            db.openConnection();
            db.clearTables();
            db.closeConnection(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Successful Load Test")
    public void loadTest() throws DataAccessException {
        User user = new User("username", "password", "email", "first", "last", "m");
        User user2 = new User("username2", "password2", "email2", "first2", "last2", "m");
        User user3 = new User("username3", "password3", "email3", "first3", "last3", "m");
        User user4 = new User("username4", "password4", "email4", "first4", "last4", "m");

        Person person = new Person(user);
        Person person2 = new Person(user2);
        Person person3 = new Person(user3);
        Person person4 = new Person(user4);

        Event event = new Event("eventID", person.getAssociatedUsername(), person.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event2 = new Event("eventID2", person2.getAssociatedUsername(), person2.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event3 = new Event("eventID3", person3.getAssociatedUsername(), person3.getPersonID(), 100, 10, "country", "city", "eventType", 1999);
        Event event4 = new Event("eventID4", person4.getAssociatedUsername(), person4.getPersonID(), 100, 10, "country", "city", "eventType", 1999);

        User[] users = new User[4];
        users[0] = user;
        users[1] = user2;
        users[2] = user3;
        users[3] = user4;

        Person[] persons = new Person[4];
        persons[0] = person;
        persons[1] = person2;
        persons[2] = person3;
        persons[3] = person4;

        Event[] events = new Event[4];
        events[0] = event;
        events[1] = event2;
        events[2] = event3;
        events[3] = event4;

        request = new LoadRequest(users, persons, events);
        result = service.load(request);

        Assertions.assertEquals("Successfully added 4 users, 4 persons, and 4 events to the database.", result.getMessage());
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    @DisplayName("Empty Load Test")
    public void loadFailTest() throws DataAccessException {
        User[] users = new User[0];
        Person[] persons = new Person[0];
        Event[] events = new Event[0];

        request = new LoadRequest(users, persons, events);
        result = service.load(request);

        Assertions.assertEquals(0, result.getNumUsers());
        Assertions.assertEquals(0, result.getNumPersons());
        Assertions.assertEquals(0, result.getNumEvents());
        Assertions.assertEquals(false, result.isSuccess());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals("Error: Need data to load", result.getMessage());
    }
}
