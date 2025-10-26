public abstract class Item implements PricePolicy {
  private final int id;
  private boolean available;

  public Item(int id, boolean available) {
    this.id = id;
    this.available = available;
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

  abstract void print();
}
