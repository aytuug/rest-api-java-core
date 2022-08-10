package dao;

import model.Person;

import java.util.HashMap;
import java.util.Map;


public class DataStore {

    private Map<String, Person> personMap = new HashMap<>();

    private static DataStore instance = new DataStore();
    public static DataStore getInstance(){
        return instance;
    }

    private DataStore(){

        personMap.put("Aytug", new Person("Aytug", "Son.", 1999));
        personMap.put("Bulent", new Person("Bulent", "Father.", 1972));
        personMap.put("Aylin", new Person("Aylin", "Mother.", 1978));
    }

    public Person getPerson(String name) {
        return personMap.get(name);
    }

    public void putPerson(Person person) {
        personMap.put(person.getName(), person);
    }
}