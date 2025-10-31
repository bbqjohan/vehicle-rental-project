package entity;

public class Motorcycle extends Item {
  private final MotorcycleType motorcycleType;

  public Motorcycle(
      String color, String brand, String model, MotorcycleType carType, boolean available) {
    super(-1, VehicleType.MOTORCYCLE, color, brand, model, available);
    this.motorcycleType = carType;
  }

  public MotorcycleType getMotorCycleType() {
    return this.motorcycleType;
  }

  @Override
  public double getBronzeMembershipFee(int duration) {
    return 50 * duration;
  }

  @Override
  public double getSilverMembershipFee(int duration) {
    return 40 * duration;
  }

  @Override
  public double getGoldMembershipFee(int duration) {
    return 23 * duration;
  }
}
