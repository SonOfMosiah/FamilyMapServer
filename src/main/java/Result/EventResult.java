package Result;

import Models.Event;

import java.util.ArrayList;

public class EventResult {

    private ArrayList<Event> data;
    private boolean success;
    private String message;

    /**
     * EventResult Constructor w/o Parameters
     */
    public EventResult() {
        this.data = null;
        this.message = null;
    }

    /**
     * EventResult Constructor with ArrayList(Event) Parameter
     * @param success Boolean
     * @param eventsList ArrayList(Event)
     */
    public EventResult(boolean success, ArrayList<Event> eventsList) {
        this.success = success;
        this.data = eventsList;
        this.message = null;
    }

    /**
     * EventResult Constructor with String Parameter
     * Error
     * @param success Boolean
     * @param err String
     */
    public EventResult(boolean success, String err) {
        this.success = success;
        this.data = null;
        this.message = err;
    }

    /**
     * Get Events
     * @return events ArrayList(Event)
     */
    public ArrayList<Event> getData() {
        return data;
    }

    /**
     * Set Events
     * @param data ArrayList(Event)
     */
    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

    /**
     * Get Result
     * @return message (String)
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Result
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
