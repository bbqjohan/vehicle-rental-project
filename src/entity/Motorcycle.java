package entity;

public class Motorcycle extends Item {
    private final MotorcycleType motorcycleType;

    public Motorcycle(
            String color, String brand, String model, MotorcycleType carType, boolean available) {
        super(VehicleType.MOTORCYCLE, color, brand, model, available);
        this.motorcycleType = carType;
    }

    public MotorcycleType getMotorCycleType() {
        return this.motorcycleType;
    }

    @Override
    public double getBronzeMembershipFee(int duration) {
        return 20 * duration;
    }

    @Override
    public double getSilverMembershipFee(int duration) {
        return 10 * duration;
    }

    @Override
    public double getGoldMembershipFee(int duration) {
        return 5 * duration;
    }
}
