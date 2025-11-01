package entity;

public abstract class Item implements PricePolicy {
    private int id;
    private boolean available;
    private final VehicleType type;
    private final String color;
    private final String brand;
    private final String model;

    public Item(
            int id, VehicleType type, String color, String brand, String model, boolean available) {
        this.id = id;
        this.available = available;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.model = model;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public VehicleType getType() {
        return type;
    }
}
