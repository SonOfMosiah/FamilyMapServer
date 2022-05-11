package Services;

import DataAccess.AuthtokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.Authtoken;
import Models.User;
import Request.LoginRequest;
import Result.LoginResult;

/**
 * User Login
 */
public class LoginService {
    private final Database db = new Database();

    private UserDAO userDAO;

    /**
     * Attempts to Log User in
     * @param request LoginRequest
     * @return LoginResult
     */
    public LoginResult login(LoginRequest request) throws DataAccessException {
        try {
            db.openConnection();
            userDAO = db.getUserDAO();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        try {
            if (!(request.getUsername() != null && request.getPassword() != null)) {
                db.closeConnection(false);
                return new LoginResult(false, "Error: Invalid input");
            }
            User user = userDAO.find(request.getUsername());

            if (user == null) {
                db.closeConnection(false);
                return new LoginResult(false, "Error: error finding user");
            }
            else if (!user.getPassword().equals(request.getPassword())) {
                db.closeConnection(false);
                return new LoginResult(false, "Error: Wrong Password");
            }
            else {
                Authtoken authToken = new Authtoken(request.getUsername(), request.getUsername());
                AuthtokenDAO authTokenDAO = db.getAuthtokenDAO();
                authTokenDAO.insert(authToken);
                db.closeConnection(true);
                return new LoginResult(true, authToken.getAuthtoken(), user.getUsername(), user.getPersonID());
            }
        }
        catch (DataAccessException e) {
            db.closeConnection(false);
            return new LoginResult(false,"Internal Server Error");
        }
    }
}
