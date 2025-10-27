public class Motorcycle extends Item {
  private MotorcycleType motorcycleType;

  public Motorcycle(String color, String brand, String model, MotorcycleType carType) {
    super(-1, VehicleType.MOTORCYCLE, color, brand, model, true);
    this.motorcycleType = carType;
  }

  public MotorcycleType getMotorCycleType() {
    return this.motorcycleType;
  }

  @Override
  public double getBronzeMembershipFee() {
    return 50;
  }

  @Override
  public double getSilverMembershipFee() {
    return 40;
  }

  @Override
  public double getGoldMembershipFee() {
    return 23;
  }
}
