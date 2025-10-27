public abstract class Item implements PricePolicy {
  private final int id;
  private boolean available;
  private VehicleType type;

  public Item(int id, boolean available, VehicleType type) {
    this.id = id;
    this.available = available;
    this.type = type;
  }

  public int getId() {
    return this.id;
  }

  public boolean isAvailable() {
    return this.available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
