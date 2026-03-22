public class Truck extends Vehicle {
    private double maxWeight;

    public Truck(int year, double dailyRentPrice, String make, String model, double maxWeight) {
        super(year, dailyRentPrice, make, model);
        this.maxWeight = maxWeight;
    }

    public Truck(int id, int year, double dailyRentPrice, String make, String model, double maxWeight) {
        super(year, dailyRentPrice, make, model);
        this.maxWeight = maxWeight;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Max Weight: " + this.maxWeight);
    }

    @Override
    public String toString() {
        return String.format("(%d) %8d %12s %12s %10.2f %12b %17.2f", this.getId(), this.getYear(),
                this.getMake(), this.getModel(), this.getDailyRentPrice(), this.isAvailable(), this.maxWeight);
    }

    public double getMaxWeight() {
        return this.maxWeight;
    }
}
