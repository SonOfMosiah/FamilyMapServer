package Request;

/**
 * Class that handles /fill api calls
 */
public class FillRequest {

    String username;
    int genCount;

    /**
     * FillRequest Constructor
     * @param username
     * @param genCount
     */
    public FillRequest(String username, int genCount) {
        this.username = username;
        this.genCount = genCount;
    }
}
