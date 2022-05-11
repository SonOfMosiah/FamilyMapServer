package Services;

import DataAccess.DataAccessException;
import Result.ClearResult;
import org.junit.jupiter.api.*;

public class ClearServiceTest {
    private static ClearService service = new ClearService();
    private static ClearResult result = new ClearResult();

    @Test
    @DisplayName("Clear Database Test")
    public void clearTest() throws DataAccessException {
        result = service.clearDatabase();
        Assertions.assertEquals("Clear succeeded.", result.getMessage());
        Assertions.assertTrue(result.isSuccess());
    }
}
