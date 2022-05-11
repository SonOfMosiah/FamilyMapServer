package DataAccess;

import java.sql.*;

/**
 * General Database class that the DAO objects will extend
 */
public class Database {
    private Connection conn;

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final UserDAO userDAO;
    private final PersonDAO personDAO;
    private final EventDAO eventDAO;
    private AuthtokenDAO authtokenDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }
    public AuthtokenDAO getAuthtokenDAO() {
        return authtokenDAO;
    }
    public void setAuthtokenDAO(AuthtokenDAO authtokenDAO) { this.authtokenDAO = authtokenDAO; }

    /**
     * Database Constructor
     */
    public Database() {
        userDAO = new UserDAO();
        personDAO = new PersonDAO();
        eventDAO = new EventDAO();
        authtokenDAO = new AuthtokenDAO();
    }

    public Connection openConnection() throws DataAccessException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:database/FamilyMapServer.sqlite";
            conn = DriverManager.getConnection(CONNECTION_URL);

            userDAO.setConn(conn);
            eventDAO.setConn(conn);
            personDAO.setConn(conn);
            authtokenDAO.setConn(conn);

            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    public Connection getConnection() throws DataAccessException {
        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    public boolean clearTables() throws DataAccessException {
        try {
            userDAO.clear();
            personDAO.clear();
            eventDAO.clear();
            authtokenDAO.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
