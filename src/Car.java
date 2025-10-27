public class Car extends Item {
  private String color;
  private String brand;
  private String model;
  private CarType type;

  public Car(String color, String brand, String model, CarType type, int id, boolean available) {
    super(id, available, VehicleType.CAR);
    this.color = color;
    this.brand = brand;
    this.model = model;
    this.type = type;
  }

  public Car(String color, String brand, String model, CarType type) {
    this(color, brand, model, type, -1, true);
  }

  public String getColor() {
    return color;
  }

  public String getBrand() {
    return this.brand;
  }

  public String getModel() {
    return this.model;
  }

  public CarType getType() {
    return this.type;
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
