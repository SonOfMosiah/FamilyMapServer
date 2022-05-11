package DataAccess;

import Models.Authtoken;
import org.junit.jupiter.api.*;

public class AuthtokenDAOTest {
    private static AuthtokenDAO authTokenDAO;
    private static Database db;

    @BeforeAll
    @DisplayName("Setup")
    public static void setUp() {
        try {
            db = new Database();
            db.openConnection();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    @DisplayName("TearDown")
    public static void tearDown() {
        try {
            db.closeConnection(false);
            authTokenDAO = null;
            db = null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    @DisplayName("CleanUp")
    public void cleanTests() throws DataAccessException {
        try {
            authTokenDAO = new AuthtokenDAO(db.getConnection());
            authTokenDAO.clear();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert AuthToken Test")
    public void InsertAuthTokenTest() {
        Authtoken authToken = new Authtoken("authToken", "username", "personID");
        try {
            authTokenDAO.insert(authToken);
            Authtoken foundAuthtoken = authTokenDAO.find(authToken.getAuthtoken());
            Assertions.assertEquals(foundAuthtoken, authToken);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert Duplicate AuthToken Test")
    public void InsertDuplicateAuthTokenTest() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            Authtoken authToken = new Authtoken("authToken", "username", "personID");
            authTokenDAO.insert(authToken);
            authTokenDAO.insert(authToken);
        });
    }

    @Test
    @DisplayName("Find AuthToken")
    public void FindAuthTokenTest() {
        Authtoken authToken = new Authtoken("authToken", "username", "personID");
        try {
            Assertions.assertNull(authTokenDAO.find(authToken.getAuthtoken()));
            authTokenDAO.insert(authToken);
            Authtoken foundAuthtoken = authTokenDAO.find(authToken.getAuthtoken());
            Assertions.assertEquals(foundAuthtoken, authToken);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Attempt to Find Wrong AuthToken")
    public void FindWrongAuthTokenTest() {
        Authtoken authToken = new Authtoken("authToken", "username", "personID");
        try {
            Assertions.assertNull(authTokenDAO.find("authToken"));
            authTokenDAO.insert(authToken);
            Assertions.assertNull(authTokenDAO.find("xauthToken"));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Clear AuthToken Table")
    public void ClearAuthTokenTableTest() {
        Authtoken authToken = new Authtoken("authToken", "username", "personID");
        try {
            Assertions.assertNull(authTokenDAO.find(authToken.getAuthtoken()));
            authTokenDAO.insert(authToken);
            Authtoken foundAuthtoken = authTokenDAO.find(authToken.getAuthtoken());
            Assertions.assertEquals(foundAuthtoken, authToken);
            Assertions.assertTrue(authTokenDAO.clear());
            Assertions.assertNull(authTokenDAO.find(authToken.getAuthtoken()));

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
