package Services;

import DataAccess.DataAccessException;
import Request.RegisterRequest;
import Result.RegisterResult;
import org.junit.jupiter.api.*;

public class RegisterServiceTest {
    private static ClearService clearService = new ClearService();
    private static RegisterService service = new RegisterService();
    private static RegisterRequest request = new RegisterRequest();
    private static RegisterResult result;

    @BeforeEach
    @DisplayName("Cleanup")
    public void cleanUp() throws DataAccessException {
        clearService.clearDatabase();
        service = new RegisterService();
    }

    @Test
    @DisplayName("Register User Test")
    public void registerUserTest() throws DataAccessException {
        request = new RegisterRequest("username", "password", "email", "first", "last","m");
        result = service.register(request);
        Assertions.assertNotNull(result.getAuthtoken());
        Assertions.assertNotNull(result.getUsername());
        Assertions.assertNotNull(result.getPersonID());
        Assertions.assertNull(result.getMessage());
    }

    @Test
    @DisplayName("Register Invalid User Test")
    public void registerInvalidUserTest() throws DataAccessException {
        service = new RegisterService();
        request = new RegisterRequest("username", "password", "email", "first", "last","m");
        request.setGender(null);
        result = service.register(request);

        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals("Error: missing property", result.getMessage());
        Assertions.assertNull(result.getAuthtoken());
        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getAssociatedUsername());
    }

    @Test
    @DisplayName("Register Duplicate User Test")
    public void registerUserAlreadyExistsTest() throws DataAccessException {
        service = new RegisterService();
        request = new RegisterRequest("username", "password", "email", "first", "last","m");
        result = service.register(request);
        result = service.register(request);

        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals("Error: Data Access Exception", result.getMessage());
        Assertions.assertNull(result.getAuthtoken());
        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getAssociatedUsername());
    }
}
