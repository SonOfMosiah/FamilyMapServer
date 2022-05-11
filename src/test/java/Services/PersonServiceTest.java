package Services;

import DataAccess.AuthtokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDAO;
import Models.Authtoken;
import Models.Person;
import Request.PersonRequest;
import Result.PersonResult;
import Result.PersonsResult;
import org.junit.jupiter.api.*;

public class PersonServiceTest {
    private static ClearService clearService = new ClearService();
    private static PersonService service = new PersonService();
    private static PersonRequest request;
    private static PersonsResult personResult;
    private static PersonResult personByIDResult;
    private static PersonDAO personDAO;
    private static Person person;
    private static AuthtokenDAO authTokenDAO;
    private static Authtoken authToken;
    private static Database db;



    @BeforeEach
    @DisplayName("Setup")
    public void setUp() throws DataAccessException {
        clearService.clearDatabase();
        service = new PersonService();
        db = new Database();
        db.openConnection();
        personDAO = db.getPersonDAO();
        person = new Person("username", "first", "last", "m");
        personDAO.insert(person);
        authTokenDAO = db.getAuthtokenDAO();
        authToken = new Authtoken(person.getAssociatedUsername(), person.getAssociatedUsername());
        authTokenDAO.insert(authToken);
        db.closeConnection(true);
    }


    @Test
    @DisplayName("Find a Person By ID Test")
    public void findPersonByIDTest() throws DataAccessException {
        personByIDResult = service.person(person.getPersonID(), authToken.getAuthtoken());
        Assertions.assertEquals(person.getPersonID(), personByIDResult.getPersonID());
        Assertions.assertEquals(person.getAssociatedUsername(), personByIDResult.getAssociatedUsername());
        Assertions.assertEquals(person.getFirstName(), personByIDResult.getFirstName());
        Assertions.assertEquals(person.getLastName(), personByIDResult.getLastName());
        Assertions.assertEquals(person.getGender(), personByIDResult.getGender());
        Assertions.assertEquals(null, personByIDResult.getMessage());
    }

    @Test
    @DisplayName("Find a Person By ID Fail Test")
    public void findPersonByIDFailTest() throws DataAccessException {
        personByIDResult = service.person(person.getAssociatedUsername(), "x" + authToken.getAuthtoken());
        Assertions.assertEquals(null, personByIDResult.getPersonID());
        Assertions.assertEquals(null, personByIDResult.getAssociatedUsername());
        Assertions.assertEquals(null, personByIDResult.getFirstName());
        Assertions.assertEquals(null, personByIDResult.getLastName());
        Assertions.assertEquals(null, personByIDResult.getGender());
        Assertions.assertEquals("Error: Invalid AuthToken", personByIDResult.getMessage());
        Assertions.assertEquals(false, personByIDResult.getSuccess());
    }

    @Test
    @DisplayName("Find a Person Test")
    public void findPersonTest() throws DataAccessException {
        personResult = service.persons(authToken.getAuthtoken());
        Assertions.assertEquals(person.getPersonID(), personResult.getData().get(0).getPersonID());
        Assertions.assertEquals(person.getAssociatedUsername(), personResult.getData().get(0).getAssociatedUsername());
        Assertions.assertEquals(person.getFirstName(), personResult.getData().get(0).getFirstName());
        Assertions.assertEquals(person.getLastName(), personResult.getData().get(0).getLastName());
        Assertions.assertEquals(person.getGender(), personResult.getData().get(0).getGender());
        Assertions.assertEquals(null, personResult.getMessage());
    }

    @Test
    @DisplayName("Find a Person Fail Test")
    public void findPersonFailTest() throws DataAccessException {
        personResult = service.persons("x" + authToken.getAuthtoken());
        Assertions.assertEquals(null, personResult.getData());
        Assertions.assertEquals("Error: Invalid AuthToken", personResult.getMessage());
        Assertions.assertEquals(false, personResult.getSuccess());
    }
}
