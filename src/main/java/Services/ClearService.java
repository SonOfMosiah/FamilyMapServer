package Services;

import DataAccess.DataAccessException;
import DataAccess.Database;
import Result.ClearResult;

/**
 * ClearService is a service class to clear the database.
 */
public class ClearService {

    /**
     * Clears the Database
     * @return ClearResult Object
     */
    public ClearResult clearDatabase() throws DataAccessException {
        Database db = new Database();
        db.openConnection();
        try {
            boolean success = db.clearTables();
            if (success){
                return new ClearResult(true, "Clear succeeded.");
            } else {
                return new ClearResult(false, "Error with clearing the Database");
            }
        } catch(DataAccessException e) {
            return new ClearResult(false, e.toString());
        } finally {
            db.closeConnection(true);
        }
    }

}
