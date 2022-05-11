package Generator;

import Models.Event;
import Models.Person;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    private final Random random = new Random();
    private final String username;
    private final ArrayList<Event> events;

    public String mName() {
        Random rand = new Random();

        try {
            FileReader fileReader = new FileReader("json/mnames.json");
            return getNameString(rand, fileReader);
        }
        catch ( FileNotFoundException e){
            e.printStackTrace();
        }
        return "Error generating male name";
    }

    public String fName() {
        Random rand = new Random();

        try {
            FileReader fileReader = new FileReader("json/fnames.json");
            return getNameString(rand, fileReader);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return "Error generating female name";
    }

    public String sName() {
        Random rand = new Random();

        try {
            FileReader fileReader = new FileReader("json/snames.json");
            return getNameString(rand, fileReader);
        }
        catch (FileNotFoundException fileNotFound){
            fileNotFound.printStackTrace();
        }

        return "Error generating surname";
    }

    @NotNull
    private String getNameString(Random rand, FileReader fileReader) {
        JsonObject object = (JsonObject) JsonParser.parseReader(fileReader);
        JsonArray nameArray = (JsonArray) object.get("data");

        int index = rand.nextInt(nameArray.size());
        String name = nameArray.get(index).toString();
        name = name.substring(1, name.length() - 1);
        return name;
    }

    public Event location() {
        Random rand = new Random();
        Event event = new Event();

        try {
            FileReader fileReader = new FileReader("json/locations.json");
            JsonObject object = (JsonObject) JsonParser.parseReader(fileReader);
            JsonArray locArray = (JsonArray) object.get("data");

            int index = rand.nextInt(locArray.size());
            JsonObject location = (JsonObject)locArray.get(index);

            String city = location.get("city").toString()
                    .substring(1, location.get("city").toString().length() - 1);
            String country = location.get("country")
                    .toString().substring(1, location.get("country").toString().length() - 1);

            event.setCountry(country);
            event.setCity(city);
            event.setLatitude(location.get("latitude").getAsFloat());
            event.setLongitude(location.get("longitude").getAsFloat());

            return event;
        }
        catch (FileNotFoundException fileNotFound){
            fileNotFound.printStackTrace();
        }
        return null;
    }

    public DataGenerator() {
        this.username = null;
        this.events = new ArrayList<>();
    }

    public DataGenerator(String username) {
        this.username = username;
        this.events = new ArrayList<>();
    }

    public String generateEventType() {
        Random rand = new Random();
        String eventTypeToReturn;

        JsonObject rootObject = null;
        try (FileReader fileReader = new FileReader("json/events.json")) {
            rootObject = (JsonObject) JsonParser.parseReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert rootObject != null;
        JsonArray eventTypeArray = (JsonArray) rootObject.get("data");

        int index = rand.nextInt(eventTypeArray.size());
        eventTypeToReturn = eventTypeArray.get(index).toString();
        eventTypeToReturn = eventTypeToReturn.substring(1, eventTypeToReturn.length() -1);

        return eventTypeToReturn;
    }

    public void birth(Person person, int year) {
        Event birth = location();
        int birthYear;
        int birthYearVariation = 10;
        birthYear = year - random.nextInt(birthYearVariation);

        birth.setPersonID(person.getPersonID());
        birth.setEventType("Birth");
        birth.setUsername(username);
        birth.setYear(birthYear);

        events.add(birth);

    }

    public void wedding(Person husband, Person wife, int year) {
        int weddingYear;
        weddingYear = year + random.nextInt(6) + 25;

        Event wedding = location();
        wedding.setPersonID(husband.getPersonID());
        wedding.setEventType("Marriage");
        wedding.setUsername(username);
        wedding.setYear(weddingYear);

        Event marriageWife = new Event(wedding);
        marriageWife.setPersonID(wife.getPersonID());

        events.add(wedding);
        events.add(marriageWife);
    }


    public void death(Person person, int year) {
        Event death = location();
        int maxAge = 100;
        int ageVariation = 70;

        int deathYear;
        deathYear = year + maxAge - random.nextInt(ageVariation);

        if (deathYear > 2022){
            deathYear = 2022;
        }

        death.setPersonID(person.getPersonID());
        death.setEventType("Death");
        death.setUsername(username);
        death.setYear(deathYear);

        events.add(death);
    }

    public void otherEvent(Person person, int year) {
        int baseYear = 10;
        int eventYearVariation = 25;
        int eventYear = year + baseYear + random.nextInt(eventYearVariation);

        Event other = location();
        other.setYear(eventYear);
        other.setEventType(generateEventType());
        other.setUsername(username);
        other.setPersonID(person.getPersonID());

        events.add(other);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getUsername() {
        return username;
    }
}
