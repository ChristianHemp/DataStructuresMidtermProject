import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize scanner for console input & rental service system
        Scanner scanner = new Scanner(System.in);
        RentalService rentalService = new RentalService();

        // Load existing customer & vehicle data from CSV
        CSVManager.loadCustomerData("customerData.csv", rentalService);
        CSVManager.loadVehicleData("vehicleData.csv", rentalService);
        CSVManager.loadRentalHistory("rentalHistory.csv", rentalService);

        // Main program loop
        while(true) {
            System.out.println("============ Car Rental Manager ============");
            System.out.println("(1) View Customer Database");
            System.out.println("(2) View Vehicle Database");
            System.out.println("(3) Register New Customer");
            System.out.println("(4) Register New Vehicle");
            System.out.println("(5) Rent Vehicle to Customer");
            System.out.println("(6) Return Rented Vehicle");
            System.out.println("(7) Search Specific Customer Info");
            System.out.println("(8) Search Specific Vehicle Info");
            System.out.println("(9) End Program");
            System.out.println("============================================");
            System.out.print("Please select an option (1-9): ");

            // Prevents invalid scanner input crashing
            if(!scanner.hasNextInt()) {
                System.out.println("Only numbers are allowed");
                scanner.nextLine();
                continue;
            }
            int num = scanner.nextInt();
            scanner.nextLine();
            if(num > 9 || num <= 0){
                System.out.println("\nNumber must be 1-9");
                continue;
            }

            // Main switch case to handle main 9 program functions
            switch(num) {
                case 1:
                    System.out.println();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("(ID)      Name      Phone Number      Drivers License #");
                    System.out.println("--------------------------------------------------------");

                    // Get customer IDs into int array then merge sort for formatting
                    int size = rentalService.getCustomers().getSize();
                    int[] customerIds = new int[size];

                    for(int i = 0; i < size; i++) {
                        customerIds[i] = i + 1;
                    }

                    mergeSort(customerIds);

                    for(int id : customerIds) {
                        System.out.println(rentalService.getCustomers().get(String.valueOf(id)));
                    }
                    System.out.print("\nEnter Any Key when finished: ");
                    scanner.nextLine();
                    continue;
                case 2:
                    System.out.println("(1) Cars");
                    System.out.println("(2) Trucks");
                    System.out.println("(3) Motorcycles");
                    System.out.print("Pick a vehicle type (1-3): ");

                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }

                    int picked = scanner.nextInt();
                    scanner.nextLine();

                    if(picked > 3 || picked <= 0){
                        System.out.println("\nNumber must be 1-3");
                        continue;
                    }

                    if(picked == 1) {
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        System.out.println("(ID)    Year      Make        Model     CostPerDay($)  " +
                                "Available    MaxPassengers       Waitlist(IDs)");
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");

                        for(int i = 1; i <= rentalService.getVehicles().getSize(); i++) {
                            Vehicle currVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(i));

                            // Only prints (formatted) Car objects
                            if(currVehicle instanceof Car) {
                                System.out.print(currVehicle + "            ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        scanner.nextLine();
                        continue;
                    } else if (picked == 2) {
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        System.out.println("(ID)    Year      Make          Model     CostPerDay($)  " +
                                "Available    MaxWeight(lbs)       Waitlist(IDs)");
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");

                        for(int i = 1; i <= rentalService.getVehicles().getSize(); i++) {
                            Vehicle currVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(i));

                            // Only prints (formatted) Truck objects
                            if(currVehicle instanceof Truck) {
                                System.out.print(currVehicle + "           ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        scanner.nextLine();
                        continue;
                    } else {
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        System.out.println("(ID)    Year        Make        Model     CostPerDay($)  " +
                                "Available    MaxSpeed(mph)       Waitlist(IDs)");
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");

                        for(int i = 1; i <= rentalService.getVehicles().getSize(); i++) {
                            Vehicle currVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(i));

                            // Only prints (formatted) Motorcycle objects
                            if(currVehicle instanceof Motorcycle) {
                                System.out.print(currVehicle + "              ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        scanner.nextLine();
                        continue;
                    }
                case 3:
                    System.out.print("Please Enter Customer Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Please Enter Customer Phone Number (xxx-xxx-xxxx): ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Please Enter Customer Drivers License Number: ");
                    String licenseNumber = scanner.nextLine();

                    rentalService.addCustomer(new Customer(name, phoneNumber, licenseNumber));
                    continue;
                case 4:
                    System.out.println("(1) Cars");
                    System.out.println("(2) Trucks");
                    System.out.println("(3) Motorcycles");
                    System.out.print("Pick a vehicle type to register (1-3): ");

                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if(type > 3 || type <= 0) {
                        System.out.println("\nNumber must be 1-3");
                        continue;
                    }

                    // If statements to handle creation of Car/Truck/Motorcycle objects
                    // Variations due to custom fields (ie: Trucks have maxWeight)
                    if(type == 1) {
                        System.out.print("Please Enter Car Year: ");
                        if(!scanner.hasNextInt()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Please Enter Cost to Rent Per Day ($): ");
                        if(!scanner.hasNextDouble()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        double dailyRentPrice = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Please Enter Car Make: ");
                        String make = scanner.nextLine();

                        System.out.print("Please Enter Car Model: ");
                        String model = scanner.nextLine();

                        System.out.print("Please Enter The Max Passengers the Car Can Hold: ");
                        if(!scanner.hasNextInt()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        int maxPassengers = scanner.nextInt();
                        scanner.nextLine();

                        rentalService.addVehicle(new Car(year, dailyRentPrice, make, model, maxPassengers));
                        continue;
                    } else if (type == 2) {
                        System.out.print("Please Enter Truck Year: ");
                        if(!scanner.hasNextInt()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Please Enter Cost to Rent Per Day ($): ");
                        if(!scanner.hasNextDouble()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        double dailyRentPrice = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Please Enter Truck Make: ");
                        String make = scanner.nextLine();

                        System.out.print("Please Enter Truck Model: ");
                        String model = scanner.nextLine();

                        System.out.print("Please Enter the Payload Capacity of the Truck (lbs): ");
                        if(!scanner.hasNextDouble()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        double maxWeight = scanner.nextDouble();
                        scanner.nextLine();

                        rentalService.addVehicle(new Truck(year, dailyRentPrice, make, model, maxWeight));
                        continue;
                    } else {
                        System.out.print("Please Enter Motorcycle Year: ");
                        if(!scanner.hasNextInt()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Please Enter Cost to Rent Per Day ($): ");
                        if(!scanner.hasNextDouble()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        double dailyRentPrice = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Please Enter Motorcycle Make: ");
                        String make = scanner.nextLine();

                        System.out.print("Please Enter Motorcycle Model: ");
                        String model = scanner.nextLine();

                        System.out.print("Please Enter the Max Speed of the Motorcycle (mph): ");
                        if(!scanner.hasNextInt()) {
                            System.out.println("Only numbers are allowed");
                            scanner.nextLine();
                            continue;
                        }
                        int maxSpeed = scanner.nextInt();
                        scanner.nextLine();

                        rentalService.addVehicle(new Motorcycle(year, dailyRentPrice, make, model, maxSpeed));
                        continue;
                    }
                case 5:
                    // If valid ID for existing vehicle & customer are found, calls rent function
                    System.out.print("Please Enter the ID of the desired vehicle: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int vehicleId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Please Enter the ID of the customer: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int customerId = scanner.nextInt();
                    scanner.nextLine();

                    rentalService.rentVehicle(vehicleId, customerId);
                    continue;
                case 6:
                    // Finds vehicle by ID and calls return function
                    // Customer ID stored in Vehicle's currentCustomerId field
                    // Return function automatically moves dequeues from Vehicle's queue field
                    System.out.print("Please enter the ID of the vehicle being returned: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int returnVehicleId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Please enter the number of days the vehicle was rented: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int numOfDays = scanner.nextInt();
                    scanner.nextLine();

                    rentalService.returnVehicle(returnVehicleId, numOfDays);
                    continue;
                case 7:
                    // Searches for Customer by ID and calls displayInfo method
                    System.out.print("Please Enter Customer ID: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int pickedCustomerId = scanner.nextInt();
                    scanner.nextLine();

                    Customer currentCustomer = (Customer) rentalService.getCustomers().get(String.valueOf(pickedCustomerId));
                    if(currentCustomer == null) {
                        System.out.println("Customer does not exist");
                        continue;
                    }
                    currentCustomer.displayInfo();

                    System.out.print("\nEnter Any Key when finished: ");
                    scanner.nextLine();
                    continue;
                case 8:
                    // Searches for vehicle by ID and calls displayInfo method
                    System.out.print("Please Enter Vehicle ID: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int pickedVehicleId = scanner.nextInt();
                    scanner.nextLine();

                    Vehicle currentVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(pickedVehicleId));
                    if(currentVehicle == null) {
                        System.out.println("Vehicle does not exist");
                        continue;
                    }
                    currentVehicle.displayInfo();

                    System.out.println("\nEnter Any Key when finished: ");
                    scanner.nextLine();
                    continue;
                case 9:
                    System.out.println("Program Terminated");
                    break;
            }
            break;
        }

        // Stores new and existing data back to CSV for future use
        CSVManager.saveCustomerData("customerData.csv", rentalService);
        CSVManager.saveVehicleData("vehicleData.csv", rentalService);
        CSVManager.saveRentalHistory("rentalHistory.csv", rentalService);
        scanner.close();
    }

    // Basic merge sort method (class implementation)
    static void mergeSort(int[] arr) {

        // Base case array of length 1 is already sorted with itself
        if(arr.length <= 1) {
            return;
        }

        // Finds middle value (integer division rounds down)
        int middle = arr.length / 2;

        // Initialize two integer arrays left and right with correct sizes
        int[] left = new int[middle];
        // arr.length - middle ensures handling in case the array length is odd
        int[] right = new int[arr.length - middle];


        for(int i = 0; i < middle; i++) {
            left[i] = arr[i];
        }

        for(int i = middle; i < arr.length; i++) {
            right[i - middle] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    // Helper method for mergeSort
    static void merge(int[] arr, int[] left, int[] right) {
        // Initialize counter/indexing ints
        int i = 0;
        int j = 0;
        int z = 0;

        // Loop that sorts the integers back into the loop based on which is larger
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[z] = left[i];
                z += 1;
                i += 1;
            }
            else {
                arr[z] = right[j];
                z += 1;
                j += 1;
            }
        }

        // These two while loops catch the possibility that the arrays are different length
        // The previous loop condition will evaluate false if one is empty, but there might still be values to be sorted
        while(i < left.length) {
            arr[z] = left[i];
            z += 1;
            i += 1;
        }

        while(j < right.length) {
            arr[z] = right[j];
            z += 1;
            j += 1;
        }
    }
}