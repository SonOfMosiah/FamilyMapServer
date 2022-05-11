package Services;

import DataAccess.*;
import Models.User;
import Request.LoginRequest;
import Result.LoginResult;
import org.junit.jupiter.api.*;

public class LoginServiceTest {

    private static ClearService clearService = new ClearService();
    private static LoginService service = new LoginService();
    private static LoginRequest request;
    private static LoginResult result;
    private static UserDAO userDAO;
    private static User user;
    private static Database db;


    @BeforeAll
    @DisplayName("Setup")
    public static void setUp() {
        try {
            db = new Database();
            db.openConnection();
            userDAO = db.getUserDAO();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    @DisplayName("TearDown")
    public static void tearDown() {
            userDAO = null;
            db = null;
    }


    @BeforeEach
    @DisplayName("Cleanup")
    public void cleanUp() {
        try {
            db.openConnection();
            userDAO = db.getUserDAO();
            userDAO = db.getUserDAO();
            userDAO.clear();
            user = new User("username", "password", "email", "first", "last", "m");
            userDAO.insert(user);
            db.closeConnection(true);
            service = new LoginService();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Successful Login Test")
    public void loginTest() throws DataAccessException {
        request = new LoginRequest(user.getUsername(), user.getPassword());
        result = service.login(request);

        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertNotNull(result.getAuthtoken());
        Assertions.assertNotNull(result.getPersonID());
        Assertions.assertNull(result.getMessage());
        Assertions.assertEquals(true, result.getSuccess());
    }

    @Test
    @DisplayName("Wrong Password Test")
    public void wrongPasswordTest() throws DataAccessException {
        request = new LoginRequest(user.getUsername(), "x" + user.getPassword());
        result = service.login(request);

        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getPersonID());
        Assertions.assertNull(result.getAuthtoken());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals("Error: Wrong Password", result.getMessage());
        Assertions.assertEquals(false, result.getSuccess());
    }

    @Test
    @DisplayName("Failed Login Test")
    public void loginFailTest() throws DataAccessException {
        db.openConnection();
        userDAO = db.getUserDAO();
        userDAO.clear();
        db.closeConnection(true);
        request = new LoginRequest(user.getUsername(), user.getPassword());
        result = service.login(request);

        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getAuthtoken());
        Assertions.assertNull(result.getPersonID());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals("Error: error finding user", result.getMessage());
        Assertions.assertEquals(false, result.getSuccess());
    }
}
