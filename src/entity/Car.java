package entity;

public class Car extends Item {
    private final CarType carType;

    public Car(String color, String brand, String model, CarType carType, boolean available) {
        super(VehicleType.CAR, color, brand, model, available);
        this.carType = carType;
    }

    public CarType getCarType() {
        return this.carType;
    }

    @Override
    public double getBronzeMembershipFee(int daysRented) {
        return 30 * daysRented;
    }

    @Override
    public double getSilverMembershipFee(int daysRented) {
        return 20 * daysRented;
    }

    @Override
    public double getGoldMembershipFee(int daysRented) {
        return 10 * daysRented;
    }
}
