package Services;

import DataAccess.*;
import Generator.Generation;
import Generator.Generator;
import Models.Authtoken;
import Models.Event;
import Models.Person;
import Models.User;
import Request.RegisterRequest;
import Result.RegisterResult;
import java.util.ArrayList;

/**
 * Registers a new user
 */
public class RegisterService {
    private final User user = new User();
    private final Person person = new Person();
    private final Generator generator = new Generator();
    private final Database db = new Database();

    /**
     * Registers a new user
     * @param request RegisterRequest
     * @return RegisterResult
     */
    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        request.setPersonID(person.getPersonID());
        if ((request.getUsername() == null) || (request.getPassword() == null) || (request.getEmail() == null) || (request.getFirstName() ==
                null) || (request.getLastName() == null) || request.getGender() == null) {

            return new RegisterResult(false, "Error: missing property");
        }

        RegisterResult result;
        db.openConnection();
        UserDAO userDAO = db.getUserDAO();
        PersonDAO personDAO = db.getPersonDAO();
        EventDAO eventDAO = db.getEventDAO();
        AuthtokenDAO authTokenDAO = db.getAuthtokenDAO();

        createUser(request);
        createPerson(request);

        try {
            userDAO.insert(user);
            Authtoken authToken = new Authtoken(user.getUsername(), person.getAssociatedUsername());
            authTokenDAO.insert(authToken);
            int defaultGenerations = 4;
            Generation generation = generator.generateGenerations(defaultGenerations, person);

            ArrayList<Person> persons = generation.getPersons();
            for (Person value : persons) {
                personDAO.insert(value);
            }

            ArrayList<Event> events = generation.getEvents();
            for (Event event : events) {
                eventDAO.insert(event);
            }
            result = new RegisterResult(true, authToken.getAuthtoken(), request.getUsername(), request.getPersonID());
            db.closeConnection(true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new RegisterResult(false, "Error: Data Access Exception");
        }
        return result;
    }

    /**
     * Create a user from the request data
     * @param request RegisterRequest
     */
    private void createUser(RegisterRequest request) {
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPersonID(person.getPersonID());
    }

    /**
     * Create a person from the request data
     * @param request RegisterRequest
     */
    private void createPerson(RegisterRequest request) {
        person.setAssociatedUsername(request.getUsername());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setGender(request.getGender());
    }
}
