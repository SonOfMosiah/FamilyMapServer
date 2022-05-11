package DataAccess;

import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles all connections with the database concerning Persons
 */
public class PersonDAO {

    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * PersonDAO Constructor
     * @param c Connection object
     */
    public PersonDAO(Connection c) {
        this.conn = c;
    }

    /**
     * PersonDAO Constructor w/o parameters
     */
    public PersonDAO() {
        this.conn = null;
    }

    /**
     * Inserts a Person into the database
     * @param person Person
     * @throws DataAccessException Error accessing database
     */
    public void insert(Person person) throws DataAccessException {
        String sql = "INSERT INTO Persons (PersonID, AssociatedUsername, FirstName, LastName, " +
                "Gender, FatherId, MotherID, SpouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds a Person in the database
     * @param personID String
     * @return Person object
     * @throws DataAccessException Error accessing database
     */
    public Person findByID(String personID) throws DataAccessException {
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Gender"), rs.getString("FatherID"), rs.getString("MotherID"),
                        rs.getString("SpouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Finds all Persons with username in the database
     * @return ArrayList of Person objects
     */
    public Person[] find(String username) throws DataAccessException {
        Person[] personArray = new Person[0];
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(personArray));
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Person person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Gender"), rs.getString("FatherID"), rs.getString("MotherID"),
                        rs.getString("SpouseID"));
                persons.add(person);
            }
            personArray = persons.toArray(personArray);
            return personArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * clear the Person table from the database
     * @return boolean
     */
    public boolean clear() {
        PreparedStatement stmt;
        try {
            String sql = "delete from Persons";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
