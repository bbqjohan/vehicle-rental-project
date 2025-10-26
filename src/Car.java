public class Car extends Item {
  private String color;
  private String brand;
  private String model;
  private CarType type;
  private int doors;

  public Car(
      String color,
      String brand,
      String model,
      CarType type,
      int doors,
      int id,
      boolean available) {
    super(id, available);
    this.color = color;
    this.brand = brand;
    this.model = model;
    this.type = type;
    this.doors = doors;
  }

  public Car(String color, String brand, String model, CarType type, int doors) {
    this(color, brand, model, type, doors, -1, true);
  }

  @Override
  public void print() {
    System.out.println("CAR");
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

  public int doors() {
    return this.doors;
  }

  @Override
  public double getMembershipFee(MembershipLevel status) {
    return switch (status) {
      case BRONZE -> this.getBronzeMembershipFee();
      case SILVER -> this.getSilverMembershipFee();
      case GOLD -> this.getGoldMembershipFee();
      case null -> 0;
    };
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
