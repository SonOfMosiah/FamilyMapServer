package Services;

import DataAccess.*;
import Models.Authtoken;
import Models.Person;
import Result.PersonResult;
import Result.PersonsResult;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Finds Person Objects
 */
public class PersonService {

    private AuthtokenDAO authTokenDAO;
    private PersonDAO personDAO;
    private final Database db = new Database();

    /**
     * Finds a Person by their PersonID for the given user
     * @param personID String
     * @param authToken String
     * @return PersonByIDResult Object
     */
    public PersonResult person(String personID, String authToken) throws DataAccessException {
        db.openConnection();
        authTokenDAO = db.getAuthtokenDAO();
        personDAO = db.getPersonDAO();
        try {
            Authtoken token = authTokenDAO.find(authToken);

            if (token == null){
                db.closeConnection(false);
                return new PersonResult(false, "Error: Invalid AuthToken");
            } else {
                Person tempPerson = personDAO.findByID(personID);
                if (tempPerson == null){
                    db.closeConnection(false);
                    return new PersonResult(false, "Error: Person not found");
                } else if (!token.getUsername().equals(tempPerson.getAssociatedUsername())){
                    db.closeConnection(false);
                    return new PersonResult(false, "Error: Person not registered under current user");
                } else {
                    PersonResult result = new PersonResult(tempPerson);
                    db.closeConnection(true);
                    return result;
                }
            }
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new PersonResult(false, "Error in locating person");
        }
    }

    /**
     * Finds all Persons with associatedUsername
     * @param authToken String
     * @return PersonResult Object
     */
    public PersonsResult persons(String authToken) throws DataAccessException {
        db.openConnection();
        authTokenDAO = db.getAuthtokenDAO();
        Authtoken token = authTokenDAO.find(authToken);
        personDAO = db.getPersonDAO();

        if (token == null) {
            db.closeConnection(false);
            return new PersonsResult(false,"Error: Invalid AuthToken");
        } else {
            Person[] persons = personDAO.find(token.getUsername());

            if (persons == null) {
                db.closeConnection(false);
                return new PersonsResult(false,"Error: No People with the given Username");
            } else {
                db.closeConnection(true);
                return new PersonsResult(true, new ArrayList<>(Arrays.asList(persons)));
            }
        }
    }
}
