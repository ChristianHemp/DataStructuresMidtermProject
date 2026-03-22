public class Car extends Vehicle {
    private int maxPassengers;

    public Car(int year, double dailyRentPrice, String make, String model, int maxPassengers) {
        super(year, dailyRentPrice, make, model);
        this.maxPassengers = maxPassengers;
    }

    public Car(int id, int year, double dailyRentPrice, String make, String model, int maxPassengers) {
        super(year, dailyRentPrice, make, model);
        this.maxPassengers = maxPassengers;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Max Passengers: " + this.maxPassengers);
    }

    @Override
    public String toString() {
        return String.format("(%d) %8d %9s %10s %15.2f %12b %13d", this.getId(), this.getYear(),
                this.getMake(), this.getModel(), this.getDailyRentPrice(), this.isAvailable(), this.maxPassengers);
    }

    public int getMaxPassengers() {
        return this.maxPassengers;
    }
}
