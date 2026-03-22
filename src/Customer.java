import java.util.ArrayList;

public class Customer extends Person {
    private String driversLicenseNumber;
    private MySinglyLinkedList<Vehicle> currentRentals;
    private ArrayList<Vehicle> previousRentals;

    public Customer(String name, String phoneNumber, String driversLicenseNumber) {
        super(name, phoneNumber);
        this.driversLicenseNumber = driversLicenseNumber;
        this.currentRentals = new MySinglyLinkedList<>();
        this.previousRentals = new ArrayList<>();
    }

    // Custom constructor for imports
    public Customer(int id, String name, String phoneNumber, String driversLicenseNumber) {
        super(id, name, phoneNumber);
        this.driversLicenseNumber = driversLicenseNumber;
        this.currentRentals = new MySinglyLinkedList<>();
        this.previousRentals = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Drivers License Number: " + this.driversLicenseNumber);
        System.out.print("Current Rentals: ");
        this.currentRentals.printIds();
        System.out.print("Previous Rentals: ");
        printPreviousRentalIds();
    }

    public void addRental(Vehicle vehicle) {
        this.currentRentals.append(vehicle);
    }

    public void returnRental(Vehicle vehicle) {
        this.previousRentals.add(vehicle);
        this.currentRentals.removeValue(vehicle);
        vehicle.setAvailability(true);
        vehicle.setCurrentCustomerId(-1);
    }

    // Helper method for rebuilding rental history from CSV
    public void addPreviousRental(Vehicle vehicle) {
        this.previousRentals.add(vehicle);
    }

    public String getDriversLicenseNumber() {
        return this.driversLicenseNumber;
    }

    public MySinglyLinkedList<Vehicle> getCurrentRentals() {
        return this.currentRentals;
    }

    public ArrayList<Vehicle> getPreviousRentals() {
        return this.previousRentals;
    }

    @Override
    public String toString() {
        return String.format("(%d) %12s %15s %15s", this.getId(), this.getName(), this.getPhoneNumber(),
        this.driversLicenseNumber);
    }

    public void printPreviousRentalIds() {
        System.out.print("[");
        for(int i = 0; i < previousRentals.size(); i++) {
            System.out.print(previousRentals.get(i).getId());
            if(i < previousRentals.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
