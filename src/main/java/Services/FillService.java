package Services;

import DataAccess.*;
import Generator.*;
import Models.*;
import Result.FillResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Fills the database with data
 */
public class FillService {
    private final Generator generator = new Generator();
    private final Database db = new Database();

    /**
     * Fills the database with data
     * @param username String
     * @param numGenerations int
     * @return FillResult Object
     */
    public FillResult fill(String username, int numGenerations) {
        if (numGenerations <= 0) {
            return new FillResult(false, "Error: Number of Generations must be greater than zero");
        }

        try {
            db.openConnection();
            UserDAO userDAO = db.getUserDAO();
            PersonDAO personDAO = db.getPersonDAO();
            EventDAO eventDAO = db.getEventDAO();
            User user = userDAO.find(username);
            if (user == null) {
                return new FillResult(false, "Error: No user exists with username: " + username);
            }

            String sql = "DELETE FROM Persons WHERE AssociatedUsername = ?;";
            try {
                PreparedStatement stmt = db.getConnection().prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
             }

            String sql2 = "DELETE FROM Events WHERE AssociatedUsername = ?;";
            try {
                PreparedStatement stmt = db.getConnection().prepareStatement(sql2);
                stmt.setString(1, user.getUsername());
                stmt.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

            Generation generation = generator.generateGenerations(numGenerations, new Person(user));
            ArrayList<Person> persons = generation.getPersons();
            for (Person person : persons) {
                personDAO.insert(person);
            }
            ArrayList<Event> events = generation.getEvents();
            for (Event event : events) {
                eventDAO.insert(event);
            }

            db.closeConnection(true);
            return new FillResult(true, persons, events);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        try {
            db.closeConnection(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new FillResult(false, "Error filling the data");
    }

}
