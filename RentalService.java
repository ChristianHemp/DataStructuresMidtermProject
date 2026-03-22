public class RentalService {
    private MyHashTable vehicles;
    private MyHashTable customers;
    private double lifetimeRevenue;

    public RentalService() {
        this.vehicles = new MyHashTable(100);
        this.customers = new MyHashTable(100);
        this.lifetimeRevenue = 0.0;
    }

    public void addCustomer(Customer customer) {
        customers.put(String.valueOf(customer.getId()), customer);
        System.out.println("Successfully added " + customer.getName() + " to the database! (ID: " + customer.getId() + ")");
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(String.valueOf(vehicle.getId()), vehicle);
        System.out.println("Successfully added a new " + vehicle.getMake() + " to the database! (ID: " + vehicle.getId() + ")");
    }

    public void rentVehicle(int vehicleId, int customerId) {
        Vehicle vehicle = (Vehicle) vehicles.get(String.valueOf(vehicleId));
        Customer customer = (Customer) customers.get(String.valueOf(customerId));

        if(customer == null) {
            System.out.println("Customer not found");
            return;
        }

        if(vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        if(vehicle.getCurrentCustomerId() == customerId) {
            System.out.println("This customer is already renting this vehicle");
            return;
        }

        if(customerOnWaitlist(vehicle, customerId)) {
            System.out.println("customer is already on the waitlist for this vehicle");
            return;
        }

        if(!vehicle.isAvailable()) {
            vehicle.addWaitlist(customer);
            System.out.println("The vehicle is currently unavailable, but the customer was added to waitlist position: "
                    + vehicle.getWaitlist().getSize());
            return;
        }

        customer.addRental(vehicle);
        vehicle.setAvailability(false);
        vehicle.setCurrentCustomerId(customer.getId());
        System.out.println("Vehicle Successfully Rented");
    }

    // helper to prevent repeat waitlisting
    private static boolean customerOnWaitlist(Vehicle vehicle, int customerId) {
        MyQueue<Customer> waitlist = vehicle.getWaitlist();
        int size = waitlist.getSize();
        boolean found = false;

        for(int i = 0; i < size; i++) {
            Customer customer = waitlist.dequeue();

            if(customer.getId() == customerId) {
                found = true;
            }

            waitlist.enqueue(customer);
        }
        return found;
    }

    public void returnVehicle(int vehicleId, int daysRented) {
        Vehicle vehicle = (Vehicle) vehicles.get(String.valueOf(vehicleId));

        if(vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        if(vehicle.isAvailable()) {
            System.out.println("Vehicle not currently being rented");
            return;
        }

        if(daysRented <= 0) {
            System.out.println("Vehicle must be rented for at least 1 day");
            return;
        }

        Customer customer = (Customer) customers.get(String.valueOf(vehicle.getCurrentCustomerId()));

        if(customer == null) {
            System.out.println("Customer not found");
            return;
        }

        double revenue = vehicle.getDailyRentPrice() * daysRented;

        customer.returnRental(vehicle);
        System.out.println("Amount due: $" + revenue);
        lifetimeRevenue += revenue;

        if(vehicle.getWaitlist().getSize() > 0) {
            Customer nextCustomer = vehicle.getWaitlist().dequeue();
            rentVehicle(vehicle.getId(), nextCustomer.getId());

            System.out.println("The vehicle has been automatically rented out to the next customer: "
                    + nextCustomer.getName() + " (id: " + nextCustomer.getId() + ")");
            System.out.println("This vehicle currently has " + vehicle.getWaitlist().getSize() +
                    " people left on its waitlist");
        }
    }

    public MyHashTable getCustomers() {
        return this.customers;
    }

    public MyHashTable getVehicles() {
        return this.vehicles;
    }
}
