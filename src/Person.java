public abstract class Person {
    private String name;
    private String phoneNumber;
    private int id;

    private static int nextId = 1;

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = nextId;
        nextId++;
    }

    //Constructor for csv imports with ID
    public Person(int id, String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;

        if(id >= nextId) {
            nextId = id + 1;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getId() {
        return this.id;
    }

    public void displayInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Id: " + this.id);
        System.out.println("Phone Number: " + this.phoneNumber);
    }
}