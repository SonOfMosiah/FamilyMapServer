package DataAccess;

import Models.User;
import org.junit.jupiter.api.*;

public class UserDAOTest {
    private static UserDAO userDAO;
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
        userDAO = null;
        database = null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    @DisplayName("CleanUp")
    public void cleanTests() throws DataAccessException {
        try {
        userDAO = new UserDAO(database.getConnection());
        userDAO.clear();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert User Test")
    public void InsertUserTest() {
        User user = new User("username", "password", "email", "first", "last", "M");
        try {
            userDAO.insert(user);
            User foundUser = userDAO.find("username");
            Assertions.assertEquals(foundUser, user);

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Insert Duplicate User")
    public void InsertDuplicateUserTest() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            User user = new User("username", "password", "email", "first", "last", "M");
            userDAO.insert(user);
            userDAO.insert(user);
        });
    }

    @Test
    @DisplayName("Find User")
    public void FindUserTest() {
        User user = new User("username", "password", "email", "first", "last", "M");
        try {
            Assertions.assertNull(userDAO.find("username"));
            userDAO.insert(user);
            User foundUser = userDAO.find("username");
            Assertions.assertEquals(foundUser, user);

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong User")
    public void FindWrongUserTest() {
        User user = new User("username", "password", "email", "first", "last", "M");
        try {
            Assertions.assertNull(userDAO.find("username"));
            userDAO.insert(user);
            Assertions.assertNull(userDAO.find("xusername"));
        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Clear User Table")
    public void ClearUserTableTest() {
        User user = new User("username", "password", "email", "first", "last", "M");
        try {
            Assertions.assertNull(userDAO.find("username"));
            userDAO.insert(user);
            User foundUser = userDAO.find("username");
            Assertions.assertEquals(foundUser, user);
            Assertions.assertTrue(userDAO.clear());
            Assertions.assertNull(userDAO.find("username"));

        } catch (DataAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
