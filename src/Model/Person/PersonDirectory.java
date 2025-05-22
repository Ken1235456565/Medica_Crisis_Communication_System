/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Person;

import java.util.ArrayList;

/**
 * A directory class to manage Person objects
 * @author tiankaining, modified
 */
public class PersonDirectory {
    private ArrayList<Person> personList;
    
    // Constructor
    public PersonDirectory() {
        personList = new ArrayList<>();
    }
    
    // Get the full list of persons
    public ArrayList<Person> getPersonList() {
        return personList;
    }
    
    // Add a person to the directory
    public void addPerson(Person person) {
        personList.add(person);
    }
    
    // Remove a person from the directory
    public void removePerson(Person person) {
        personList.remove(person);
    }
    
    // Find a person by ID
    public Person findPersonById(String id) {
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }
    
    // Find a person by name
    public Person findPersonByName(String name) {
        for (Person person : personList) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
    
    // Filter persons by gender
    public ArrayList<Person> filterByGender(String gender) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (person.getGender().equals(gender)) {
                result.add(person);
            }
        }
        return result;
    }
    
    // Filter persons by age range
    public ArrayList<Person> filterByAgeRange(int minAge, int maxAge) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (person.getAge() >= minAge && person.getAge() <= maxAge) {
                result.add(person);
            }
        }
        return result;
    }
}
