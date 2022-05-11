package Result;

public class LoadResult {

    private boolean success;
    private String message;
    private int numUsers;
    private int numEvents;
    private int numPersons;

    /**
     * LoadResult Constructor for Failed Load Service
     * @param success Boolean
     * @param message String
     */
    public LoadResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Successful LoadResult Constructor
     * @param success
     * @param numUsers
     * @param numPersons
     * @param numEvents
     */
    public LoadResult(boolean success, int numUsers, int numPersons, int numEvents) {
        this.success = success;
        this.numUsers = numUsers;
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }

    /**
     * Successful LoadResult Constructor w/o success parameter
     * @param numUsers
     * @param numPersons
     * @param numEvents
     */
    public LoadResult(int numUsers, int numPersons, int numEvents) {
        this.success = true;
        this.numUsers = numUsers;
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }

    /**
     * LoadResult Constructor w/o parameters
     */
    public LoadResult() {

    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }
}
