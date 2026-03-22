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
