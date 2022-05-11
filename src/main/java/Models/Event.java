package Models;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for Family Map Events
 */
public class Event {
    private final String eventID;
    private String associatedUsername;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    /**
     * Event Constructor
     * @param eventID String
     * @param username String
     * @param personID String
     * @param latitude Float
     * @param longitude Float
     * @param country String
     * @param city String
     * @param eventType String
     * @param year Int
     */
    public Event(String eventID, String username, String personID, float latitude, float longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Event Constructor w/o parameters
     */
    public Event() {
        this.eventID = UUID.randomUUID().toString();
    }

    /**
     * Copy Constructor w/ new EventID
     * @param event Event Object
     */
    public Event(Event event) {
        this.eventID = UUID.randomUUID().toString();
        this.associatedUsername = event.getUsername();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
    }

    /**
     * Get the Event ID
     * @return String eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Get the user's name
     * @return String associatedUsername
     */
    public String getUsername() {
        return associatedUsername;
    }

    /**
     * Set the user's name
     * @param username String
     */
    public void setUsername(String username) {
        this.associatedUsername = username;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }


    /**
     * Get the Person's ID
     * @return String personID
     */
    public String getPersonID() {
        return personID;
    }


    /**
     * Set the Person's ID
     * @param personID String
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }


    /**
     * Get the Latitude
     * @return float Latitude
     */
    public float getLatitude() {
        return latitude;
    }


    /**
     * Set the Latitude
     * @param latitude float
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


    /**
     * Get the Longitude
     */
    public float getLongitude() {
        return longitude;
    }


    /**
     * Set the Longitude
     * @param longitude float
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    /**
     * Get the Country
     * @return String country
     */
    public String getCountry() {
        return country;
    }


    /**
     * Set the Country
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Get the City
     * @return String city
     */
    public String getCity() {
        return city;
    }


    /**
     * Set the City
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Get Event Type
     * @return String Event Type
     */
    public String getEventType() {
        return eventType;
    }


    /**
     * Set the Event Type
     * @param eventType String
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    /**
     * Get the Year
     * @return integer Year
     */
    public int getYear() {
        return year;
    }


    /**
     * Set the Year
     * @param year int
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Overrides the equals() method.
     * @param o Event Object
     * @return True if object properties are equal. False if object properties are not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Event) {
            Event oEvent = (Event) o;
            return oEvent.getEventID().equals(getEventID()) &&
                    oEvent.getUsername().equals(getUsername()) &&
                    oEvent.getPersonID().equals(getPersonID()) &&
                    oEvent.getLatitude() == (getLatitude()) &&
                    oEvent.getLongitude() == (getLongitude()) &&
                    oEvent.getCountry().equals(getCountry()) &&
                    oEvent.getCity().equals(getCity()) &&
                    oEvent.getEventType().equals(getEventType()) &&
                    oEvent.getYear() == (getYear());
        } else {
            return false;
        }
    }

    /**
     * Overrides the hashCode() method
     * @return a has of all Event object variables.
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventID, personID, latitude, longitude, country, city, eventType, year);
    }
}
