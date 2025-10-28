package entity;

public class Car extends Item {
  private final CarType carType;

  public Car(String color, String brand, String model, CarType carType, boolean available) {
    super(-1, VehicleType.CAR, color, brand, model, available);
    this.carType = carType;
  }

  public CarType getCarType() {
    return this.carType;
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
