package Generator;

import DataAccess.DataAccessException;
import Models.Person;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Generator {

    private ArrayList<Person> personsList;
    private DataGenerator generator = new DataGenerator();
    private final Random random = new Random();

    /** generateGenerations starts the generation process
     * @param numGenerations integer of how many generations are wanted
     * @param person person that is the root of the family tree
     * @return GenerationData object that contains the family tree and corresponding events
     */
    public Generation generateGenerations(int numGenerations, Person person) throws DataAccessException {
        String username = person.getAssociatedUsername();
        generator = new DataGenerator(username);
        ancestry(person, numGenerations);
        return new Generation(personsList, generator.getEvents());
    }

    private void ancestry(Person root, int numGenerations) {
        personsList = new ArrayList<>();
        personsList.add(root);
        int year = 1999;

        generator.birth(root, year);

        makeMomAndDad(root, numGenerations - 1, year);
    }

    public Person makeFather(Person root, String spouse) {
        Person father = new Person(UUID.randomUUID().toString(), root.getAssociatedUsername(),
                generator.mName(), root.getLastName(), "m");
        father.setSpouseID(spouse);
        return father;
    }

    public Person makeMother(Person root, Person mother, Person father) {
        mother.setAssociatedUsername(root.getAssociatedUsername());
        mother.setFirstName(generator.fName());
        mother.setLastName(generator.sName());
        mother.setSpouseID(father.getPersonID());
        mother.setGender("f");
        return mother;
    }

    private void lifeGenerator(Person mother, Person father, int year) {
        generator.birth(father, year);
        generator.birth(mother, year);
        generator.wedding(father, mother, year);
        generator.death(father, year);
        generator.death(mother, year);

        int randomEvent = random.nextInt(4);
        if (randomEvent == 0) {
            generator.death(father, year);
            generator.otherEvent(mother, year);
        }
        else if (randomEvent == 1){
            generator.death(mother, year);
            generator.otherEvent(father, year);
        }

        if (randomEvent == 2){
            generator.otherEvent(father, year);
        }
        else if (randomEvent == 3){
            generator.otherEvent(mother, year);
        }
    }

    private void makeMomAndDad(Person root, int generation, int year) {
        int gap = 25;
        year = year - gap - random.nextInt(10);

        Person mother = new Person();
        Person father = makeFather(root, mother.getPersonID());
        mother = makeMother(root, mother, father);

        root.setFatherID(father.getPersonID());
        root.setMotherID(mother.getPersonID());

        lifeGenerator(mother, father, year);

        personsList.add(mother);
        personsList.add(father);

        if (generation != 0) {
            makeMomAndDad(mother, generation - 1, year);
            makeMomAndDad(father, generation - 1, year);
        }
    }
}
