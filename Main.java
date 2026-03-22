import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalService rentalService = new RentalService();

        CSVManager.loadCustomerData("customerData.csv", rentalService);
        CSVManager.loadVehicleData("vehicleData.csv", rentalService);
        CSVManager.loadRentalHistory("rentalHistory.csv", rentalService);

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

            switch(num) {
                case 1:
                    System.out.println();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("(ID)      Name      Phone Number      Drivers License #");
                    System.out.println("--------------------------------------------------------");
                    for(int i = 1; i <= rentalService.getCustomers().getSize(); i++) {
                        System.out.println(rentalService.getCustomers().get(String.valueOf(i)));
                    }
                    System.out.print("\nEnter Any Key when finished: ");
                    String input1 = scanner.nextLine();
                    if(input1 != null) {
                        continue;
                    }
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

                            if(currVehicle instanceof Car) {
                                System.out.print(currVehicle + "            ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        String input2a = scanner.nextLine();
                        if(input2a != null) {
                            continue;
                        }
                    } else if (picked == 2) {
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        System.out.println("(ID)    Year      Make          Model     CostPerDay($)  " +
                                "Available    MaxWeight(lbs)       Waitlist(IDs)");
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        for(int i = 1; i <= rentalService.getVehicles().getSize(); i++) {
                            Vehicle currVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(i));

                            if(currVehicle instanceof Truck) {
                                System.out.print(currVehicle + "           ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        String input2b = scanner.nextLine();
                        if(input2b != null) {
                            continue;
                        }
                    } else {
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        System.out.println("(ID)    Year        Make        Model     CostPerDay($)  " +
                                "Available    MaxSpeed(mph)       Waitlist(IDs)");
                        System.out.println("--------------------------------------------------------" +
                                "----------------------------------------------");
                        for(int i = 1; i <= rentalService.getVehicles().getSize(); i++) {
                            Vehicle currVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(i));

                            if(currVehicle instanceof Motorcycle) {
                                System.out.print(currVehicle + "              ");
                                currVehicle.getWaitlist().printIds();
                            }
                        }
                        System.out.print("\nEnter Any Key when finished: ");
                        String input2c = scanner.nextLine();
                        if(input2c != null) {
                            continue;
                        }
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
                    if(type > 3 || type <= 0) {
                        System.out.println("\nNumber must be 1-3");
                        continue;
                    }
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
                    System.out.println("Please Enter the ID of the desired vehicle: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int vehicleId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Please Enter the ID of the customer: ");
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
                    System.out.println("Please enter the ID of the vehicle being returned: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int returnVehicleId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Please enter the number of days the vehicle was rented: ");
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
                    System.out.print("Please Enter Customer ID: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int pickedIdC = scanner.nextInt();
                    scanner.nextLine();

                    Customer currentCustomer = (Customer) rentalService.getCustomers().get(String.valueOf(pickedIdC));
                    if(currentCustomer == null) {
                        System.out.println("Customer does not exist");
                        continue;
                    }
                    currentCustomer.displayInfo();

                    System.out.print("\nEnter Any Key when finished: ");
                    String inputC = scanner.nextLine();
                    if(inputC != null) {
                        continue;
                    }
                case 8:
                    System.out.print("Please Enter Vehicle ID: ");
                    if(!scanner.hasNextInt()) {
                        System.out.println("Only numbers are allowed");
                        scanner.nextLine();
                        continue;
                    }
                    int pickedIdV = scanner.nextInt();
                    scanner.nextLine();

                    Vehicle currentVehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(pickedIdV));
                    if(currentVehicle == null) {
                        System.out.println("Vehicle does not exist");
                        continue;
                    }
                    currentVehicle.displayInfo();

                    System.out.println("\nEnter Any Key when finished: ");
                    String inputV = scanner.nextLine();
                    if(inputV != null) {
                        continue;
                    }
                case 9:
                    System.out.println("Program Terminated");
                    break;
            }
            break;
        }


        scanner.close();
        CSVManager.saveCustomerData("customerData.csv", rentalService);
        CSVManager.saveVehicleData("vehicleData.csv", rentalService);
        CSVManager.saveRentalHistory("rentalHistory.csv", rentalService);
    }
}
