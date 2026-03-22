import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class CSVManager {
    public static void loadCustomerData(String fileName, RentalService rentalService) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    continue;
                }
                String[] columns = line.split(",");

                int id = Integer.parseInt(columns[0]);
                String name = columns[1];
                String phoneNumber = columns[2];
                String licenseNumber = columns[3];

                Customer customer = new Customer(id, name, phoneNumber, licenseNumber);
                rentalService.addCustomer(customer);
            }
        } catch(Exception e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }

    public static void saveCustomerData(String fileName, RentalService rentalService) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id,name,phoneNumber,licenseNumber");
            writer.newLine();

            HTEntry[] table = rentalService.getCustomers().getTable();

            for(HTEntry entry : table) {
                if(entry != null && !entry.deleted) {
                    Customer customer = (Customer) entry.value;

                    writer.write(customer.getId() + "," +
                            customer.getName() + "," +
                            customer.getPhoneNumber() + "," +
                            customer.getDriversLicenseNumber());
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("an error has occured");
            e.printStackTrace();
        }
    }

    public static void loadVehicleData(String fileName, RentalService rentalService) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    continue;
                }
                // Creates array of data from CSV split by the commas (comma separated values)
                String[] columns = line.split(",");

                if(columns.length < 10) {
                    System.out.println("Incorrectly formatted row skipped");
                    continue;
                }

                // parse methods used to modify data types for object creation
                String type = columns[0];
                int id = Integer.parseInt(columns[1]);
                int year = Integer.parseInt(columns[2]);
                double dailyRentPrice = Double.parseDouble(columns[3]);
                String make = columns[4];
                String model = columns[5];
                boolean isAvailable = Boolean.parseBoolean(columns[6]);
                int currentCustomerId = Integer.parseInt(columns[7]);
                String waitlistData = columns[8];
                String extra = columns[9];

                Vehicle vehicle;

                if(type.equals("Car")) {
                    int maxPassengers = Integer.parseInt(extra);
                    vehicle = new Car(id, year, dailyRentPrice, make, model, maxPassengers);
                } else if(type.equals("Motorcycle")) {
                    int maxSpeed = Integer.parseInt(extra);
                    vehicle = new Motorcycle(id, year, dailyRentPrice, make, model, maxSpeed);
                } else if(type.equals("Truck")) {
                    double maxWeight = Double.parseDouble(extra);
                    vehicle = new Truck(id, year, dailyRentPrice, make, model, maxWeight);
                } else {
                    System.out.println("Unexpected Vehicle Type: " + type);
                    continue;
                }

                vehicle.setAvailability(isAvailable);
                vehicle.setCurrentCustomerId(currentCustomerId);

                if(!waitlistData.isEmpty()) {
                    String[] waitlistIds = waitlistData.split("\\|");

                    for(String waitlistId : waitlistIds) {
                        int customerId = Integer.parseInt(waitlistId);
                        Customer currCustomer = (Customer) rentalService.getCustomers().get(String.valueOf(customerId));

                        if(currCustomer != null) {
                            vehicle.addWaitlist(currCustomer);
                        }
                    }
                }

                rentalService.addVehicle(vehicle);

                if(currentCustomerId != -1) {
                    Customer currentCustomer = (Customer) rentalService.getCustomers().get(String.valueOf(currentCustomerId));

                    if(currentCustomer != null) {
                        currentCustomer.addRental(vehicle);
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("an error has occurred");
            e.printStackTrace();
        }
    }

    public static void saveVehicleData(String fileName, RentalService rentalService) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("type,id,year,dailyRentPrice,make,model,isAvailable,currentCustomerId,waitlist,extra");
            writer.newLine();

            HTEntry[] table = rentalService.getVehicles().getTable();

            for(HTEntry entry : table) {
                if(entry != null && !entry.deleted) {
                    Vehicle vehicle = (Vehicle) entry.value;

                    String type = "";
                    String extra = "";

                    if(vehicle instanceof Car) {
                        type = "Car";
                        extra = String.valueOf(((Car) vehicle).getMaxPassengers());
                    } else if(vehicle instanceof Motorcycle) {
                        type = "Motorcycle";
                        extra = String.valueOf(((Motorcycle) vehicle).getMaxSpeed());
                    } else if(vehicle instanceof Truck) {
                        type = "Truck";
                        extra = String.valueOf(((Truck) vehicle).getMaxWeight());
                    }

                    String waitlistData = csvWaitlistData(vehicle);

                    writer.write(type + "," +
                            vehicle.getId() + "," +
                            vehicle.getYear() + "," +
                            vehicle.getDailyRentPrice() + "," +
                            vehicle.getMake() + "," +
                            vehicle.getModel() + "," +
                            vehicle.isAvailable() + "," +
                            vehicle.getCurrentCustomerId() + "," +
                            waitlistData + "," +
                            extra);
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

    // helper to format the waitlist data in csv
    private static String csvWaitlistData(Vehicle vehicle) {
        MyQueue<Customer> waitlist = vehicle.getWaitlist();
        int size = waitlist.getSize();
        String output = "";

        for(int i = 0; i < size; i++) {
            Customer customer = waitlist.dequeue();

            if(i > 0) {
                output += "|";
            }

            output += customer.getId();
            waitlist.enqueue(customer);
        }
        return output;
    }

    public static void loadRentalHistory(String fileName, RentalService rentalService) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null) {
                // Skips empty lines
                if(line.trim().isEmpty()) {
                    continue;
                }

                String[] columns = line.split(",");

                int customerId = Integer.parseInt(columns[0]);
                int vehicleId = Integer.parseInt(columns[1]);

                Customer customer = (Customer) rentalService.getCustomers().get(String.valueOf(customerId));
                Vehicle vehicle = (Vehicle) rentalService.getVehicles().get(String.valueOf(vehicleId));

                if(customer != null && vehicle != null) {
                    customer.addPreviousRental(vehicle);
                }
            }
        } catch(Exception e) {
            System.out.println("an error has occurred");
            e.printStackTrace();
        }
    }

    public static void saveRentalHistory(String fileName, RentalService rentalService) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("customerId,vehicleId");
            writer.newLine();

            HTEntry[] table = rentalService.getCustomers().getTable();

            for(HTEntry entry : table) {
                if(entry != null && !entry.deleted) {
                    Customer customer = (Customer) entry.value;

                    for(int i = 0; i < customer.getPreviousRentals().size(); i++) {
                        Vehicle vehicle = customer.getPreviousRentals().get(i);

                        writer.write(customer.getId() + "," + vehicle.getId());
                        writer.newLine();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }
}
