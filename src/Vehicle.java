public abstract class Vehicle {
    private int id;
    private int year;
    private double dailyRentPrice;
    private String make;
    private String model;
    private boolean isAvailable;
    private MyQueue<Customer> waitlist;
    private int currentCustomerId;

    private static int nextVehicleId = 1;

    public Vehicle(int year, double dailyRentPrice, String make, String model) {
        this.year = year;
        this.dailyRentPrice = dailyRentPrice;
        this.make = make;
        this.model = model;
        this.waitlist = new MyQueue<>(5);   // Waitlist size set to 5
        this.currentCustomerId = -1;

        this.isAvailable = true;
        this.id = nextVehicleId;
        nextVehicleId++;
    }

    // Custom constructor for importing from CSV
    public Vehicle(int id, int year, double dailyRentPrice, String make, String model) {
        this.id = id;
        this.year = year;
        this.dailyRentPrice = dailyRentPrice;
        this.make = make;
        this.model = model;
        this.waitlist = new MyQueue<>(5);   // Waitlist size set to 5
        this.currentCustomerId = -1;
        this.isAvailable = true;

        if(id >= nextVehicleId) {
            nextVehicleId = id + 1;
        }
    }

    public void displayInfo() {
        System.out.println("ID: " + this.id);
        System.out.println("Make: " + this.make);
        System.out.println("Model: " + this.model);
        System.out.println("Year: " + this.year);
        System.out.println("Cost to Rent Per Day: " + this.dailyRentPrice);
        System.out.println("Available: " + this.isAvailable);
        System.out.print("Waitlist(ID : Name): ");
        this.waitlist.printIdAndName();
    }

    public void addWaitlist(Customer customer) {
        waitlist.enqueue(customer);
    }

    public int getId() {
        return this.id;
    }

    public int getYear() {
        return this.year;
    }

    public double getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(double dailyRentPrice) {
        this.dailyRentPrice = dailyRentPrice;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public MyQueue<Customer> getWaitlist() {
        return this.waitlist;
    }

    public void setAvailability(boolean b) {
        if(b) {
            this.isAvailable = true;
        } else {
            this.isAvailable = false;
        }
    }

    public int getCurrentCustomerId() {
        return this.currentCustomerId;
    }

    public void setCurrentCustomerId(int newId) {
        this.currentCustomerId = newId;
    }
}
