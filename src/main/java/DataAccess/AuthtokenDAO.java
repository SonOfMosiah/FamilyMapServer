package DataAccess;

import Models.Authtoken;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles all connections with the database concerning authTokens
 */
public class AuthtokenDAO {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * AuthTokenDAO Constructor
     * @param conn Connection object to connect to database
     */
    public AuthtokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * AuthTokenDAO Constructor without parameters
     */
    public AuthtokenDAO() { this.conn = null; }

    /**
     * Inserts an authToken into the database
     * @param authtoken AuthToken object
     * @throws DataAccessException Error accessing the database
     */
    public void insert(Authtoken authtoken) throws DataAccessException {
        String sql = "INSERT INTO Authtokens (Authtoken, Username, AssociatedUsername) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());
            stmt.setString(3, authtoken.getAssociatedUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds an authToken in the database
     * @param authtoken String
     * @return AuthToken object
     */
    public Authtoken find(String authtoken) throws DataAccessException {
        Authtoken token;
        ResultSet rs = null;
        String sql = "SELECT * FROM Authtokens WHERE Authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                token = new Authtoken(rs.getString("Authtoken"),
                        rs.getString("Username"),
                        rs.getString("AssociatedUsername"));
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding authToken");
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
     * clear the AuthToken table from the database
     * @return boolean for success
     */
    public boolean clear() {
        PreparedStatement stmt;
        try {
            String sql = "delete from Authtokens";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
