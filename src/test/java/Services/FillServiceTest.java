package Services;

import DataAccess.*;
import Models.Authtoken;
import Models.Person;
import Models.User;
import Result.FillResult;
import Result.PersonsResult;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class FillServiceTest {
    private static ClearService clearService = new ClearService();
    private static PersonService personService = new PersonService();
    private static FillService service;
    private static FillResult result;
    private static UserDAO userDAO;
    private static AuthtokenDAO authTokenDAO;
    private static User user;
    private static Authtoken authToken;
    private static Database db = new Database();

    @BeforeEach
    @DisplayName("Setup")
    public void setUp() {
        try {
            clearService.clearDatabase();
            db.openConnection();
            userDAO = db.getUserDAO();
            authTokenDAO = db.getAuthtokenDAO();
            authToken = new Authtoken("username", "username");
            authTokenDAO.insert(authToken);
            user = new User("username", "password", "email", "first", "last", "m");
            userDAO.insert(user);
            db.closeConnection(true);
            service = new FillService();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Successful Fill Test")
    public void fillTest() throws DataAccessException {
        PersonsResult personsResult = personService.persons(authToken.getAuthtoken());
        Assertions.assertEquals(new ArrayList<Person>(), personsResult.getData());

        result = service.fill("username", 4);
        personsResult = personService.persons(authToken.getAuthtoken());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals(31, personsResult.getData().size());
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    @DisplayName("Failed Fill Test")
    public void failedFillTest() throws DataAccessException {
        PersonsResult personsResult = personService.persons(authToken.getAuthtoken());
        Assertions.assertEquals(new ArrayList<Person>(), personsResult.getData());

        result = service.fill("username", 0);
        Assertions.assertEquals("Error: Number of Generations must be greater than zero", result.getMessage());
        Assertions.assertEquals(false, result.isSuccess());
    }

}
