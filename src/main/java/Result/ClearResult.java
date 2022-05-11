package Result;

public class ClearResult {

    private String message;
    private boolean success;

    /**
     * ClearResult constructor
     * @param success boolean
     * @param message String
     */
    public ClearResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * ClearResult Constructor w/o parameters
     */
    public ClearResult() {
        this.message = null;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message;}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
