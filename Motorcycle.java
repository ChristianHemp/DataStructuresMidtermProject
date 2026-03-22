public class Motorcycle extends Vehicle {
    private int maxSpeed;

    public Motorcycle(int year, double dailyRentPrice, String make, String model, int maxSpeed) {
        super(year, dailyRentPrice, make, model);
        this.maxSpeed = maxSpeed;
    }

    public Motorcycle(int id, int year, double dailyRentPrice, String make, String model, int maxSpeed) {
        super(year, dailyRentPrice, make, model);
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Max Speed: " + this.maxSpeed);
    }

    @Override
    public String toString() {
        return String.format("(%d) %8d %10s %13s %10.2f %14b %12d", this.getId(), this.getYear(),
                this.getMake(), this.getModel(), this.getDailyRentPrice(), this.isAvailable(), this.maxSpeed);
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }
}
