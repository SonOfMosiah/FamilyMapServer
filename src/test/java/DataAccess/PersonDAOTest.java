package DataAccess;

import Models.Person;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonDAOTest {
    private static PersonDAO personDAO;
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
            personDAO = null;
            database = null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    @DisplayName("CleanUp")
    public void cleanTests() throws DataAccessException {
        try {
            personDAO = new PersonDAO(database.getConnection());
            personDAO.clear();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert Person Test")
    public void InsertPersonTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            personDAO.insert(person);
            Person foundPerson = personDAO.findByID(person.getPersonID());
            Assertions.assertEquals(foundPerson, person);

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Insert Duplicate Person")
    public void InsertDuplicatePersonTest() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            Person person = new Person("username", "first", "last", "M");
            personDAO.insert(person);
            personDAO.insert(person);
        });
    }

    @Test
    @DisplayName("Find Person")
    public void FindPersonTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            personDAO.insert(person);
            Person[] personArray = personDAO.find("username");
            ArrayList<Person> foundPerson = new ArrayList<>(Arrays.asList(personArray));
            Assertions.assertEquals(foundPerson.get(0), person);
        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Find Person")
    public void FindPersonByIDTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            Assertions.assertNull(personDAO.findByID(person.getPersonID()));
            personDAO.insert(person);
            Person foundPerson = personDAO.findByID(person.getPersonID());
            Assertions.assertEquals(foundPerson, person);

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong Person")
    public void FindWrongPersonTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            personDAO.insert(person);
            Assertions.assertEquals(new ArrayList<Person>(), new ArrayList<>(Arrays.asList(personDAO.find("xusername"))));
        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong Person")
    public void FindWrongPersonByIDTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            Assertions.assertNull(personDAO.findByID(person.getPersonID()));
            personDAO.insert(person);
            Assertions.assertNull(personDAO.findByID("x" + person.getPersonID()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Clear Person Table")
    public void ClearPersonTableTest() {
        Person person = new Person("username", "first", "last", "M");
        try {
            Assertions.assertNull(personDAO.findByID(person.getPersonID()));
            personDAO.insert(person);
            Person foundPerson = personDAO.findByID(person.getPersonID());
            Assertions.assertEquals(foundPerson, person);
            Assertions.assertTrue(personDAO.clear());
            Assertions.assertNull(personDAO.findByID(person.getPersonID()));

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}