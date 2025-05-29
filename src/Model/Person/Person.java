// Model/Person/Person.java
package Model.Person;

/**
 *
 * @author tiankaining
 */
public abstract class Person {
    protected String id;
    protected String gender;
    protected int age;
    protected String name;
    protected String dateOfBirth;
    protected ContactInfo contactInfo;

    protected static int personIdCounter = 1; // Added static counter for Person ID generation

    // Default constructor
    public Person() {
        this.id = generatePersonId();
        this.contactInfo = new ContactInfo();
    }
    
    // Constructor with parameters (if you want to manually provide ID)
    public Person(String id, String name, String gender, int age, String dateOfBirth) {
        this(); // Call default constructor to set auto-generated ID and ContactInfo
        this.id = (id != null && !id.isEmpty()) ? id : generatePersonId(); // Override the auto-generated ID if a specific one is provided
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }
    
    protected static String generatePersonId() {
        return "PER" + personIdCounter++;
    }

    // Constructor with all parameters including ContactInfo (if you want to manually provide ID and ContactInfo)
    public Person(String id, String name, String gender, int age, String dateOfBirth, ContactInfo contactInfo) {
        this(id, name, gender, age, dateOfBirth); // Call the constructor with ID
        this.contactInfo = contactInfo; // Override the default ContactInfo
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(int age) { // Corrected parameter type to int
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Added a setter for gender and getter/setter for age to match previous corrections
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public static int getCounter() {
    return personIdCounter++;
}
    
    
}