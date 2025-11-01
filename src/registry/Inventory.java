package registry;

import entity.Car;
import entity.Item;
import entity.Motorcycle;

import java.util.*;

public class Inventory {
    private int idCount = 0;
    private final TreeMap<Integer, Item> items = new TreeMap<>();

    public void add(Item item) {
        if (item == null) {
            return;
        }

        item.setId(idCount);
        this.items.put(idCount, item);

        idCount++;
    }

    public Item get(int id) {
        return this.items.get(id);
    }

    public List<Item> getList() {
        return new ArrayList<>(this.items.values());
    }

    public List<Item> filter(Map<String, String> filters) {
        List<Item> items = this.getList();
        List<Item> filteredItems = this.getList();

        for (String key : filters.keySet()) {
            String value = filters.get(key);

            for (Item item : items) {
                switch (key.toLowerCase()) {
                    case "type" -> {
                        if (!item.getType().toString().equalsIgnoreCase(value)) {
                            filteredItems.remove(item);
                        }
                    }
                    case "available" -> {
                        if (!String.valueOf(item.isAvailable()).equalsIgnoreCase(value)) {
                            filteredItems.remove(item);
                        }
                    }
                    case "configuration" -> {
                        if (item instanceof Car
                                && !((Car) item).getCarType().toString().equalsIgnoreCase(value)) {
                            filteredItems.remove(item);
                        } else if (item instanceof Motorcycle
                                && !((Motorcycle) item)
                                        .getMotorCycleType()
                                        .toString()
                                        .equalsIgnoreCase(value)) {
                            filteredItems.remove(item);
                        }
                    }
                }
            }
        }

        return filteredItems;
    }

    public Map<String, String> parseFilters(String input) throws Exception {
        String[] filters = input.split(",");
        Map<String, String> result = new HashMap<>();

        for (String filter : filters) {
            String[] split = filter.split("=");

            if (split.length != 2) {
                throw new Exception(
                        "Invalid filter syntax, failed on \"=\" when parsing \""
                                + filter
                                + "\". Make sure there "
                                + "is "
                                + "only one \"=\" separating filter name and value.");
            }

            String type = split[0].trim();
            String value = split[1].trim();

            if (!this.isValidFilter(type)) {
                throw new Exception("Filter \"" + type + "\" is not a valid filter.");
            }

            result.put(type, value);
        }

        return result;
    }

    public boolean isValidFilter(String str) {
        return str.equalsIgnoreCase("type")
                || str.equalsIgnoreCase("configuration")
                || str.equalsIgnoreCase("available");
    }

    public void printInventory(List<Item> items) {
        int colIdWidth = 2;
        int colTypeWidth = 4;
        int colBrandWidth = 5;
        int colModelWidth = 5;
        int colConfigWidth = 13;
        int colAvailableWidth = 9;

        for (Item item : items) {
            colIdWidth = Math.max(String.valueOf(item.getId()).length(), colIdWidth);
            colTypeWidth = Math.max(item.getType().toString().length(), colTypeWidth);
            colBrandWidth = Math.max(item.getBrand().length(), colBrandWidth);
            colModelWidth = Math.max(item.getModel().length(), colModelWidth);
            colAvailableWidth =
                    Math.max(String.valueOf(item.isAvailable()).length(), colAvailableWidth);

            if (item instanceof Car) {
                colConfigWidth =
                        Math.max(((Car) item).getCarType().toString().length(), colConfigWidth);
            } else if (item instanceof Motorcycle) {
                colConfigWidth =
                        Math.max(
                                ((Motorcycle) item).getMotorCycleType().toString().length(),
                                colConfigWidth);
            }
        }

        String header =
                "Id"
                        + this.getColPadding(this.getColPaddingLength(colIdWidth, 2))
                        + "Type"
                        + this.getColPadding(this.getColPaddingLength(colTypeWidth, 4))
                        + "Brand"
                        + this.getColPadding(this.getColPaddingLength(colBrandWidth, 5))
                        + "Model"
                        + this.getColPadding(this.getColPaddingLength(colModelWidth, 5))
                        + "Configuration"
                        + this.getColPadding(this.getColPaddingLength(colConfigWidth, 13))
                        + "Available"
                        + this.getColPadding(this.getColPaddingLength(colAvailableWidth, 9));

        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        for (Item item : items) {
            int idLen = String.valueOf(item.getId()).length();
            int typeLen = item.getType().toString().length();
            int brandLen = item.getBrand().length();
            int modelLen = item.getModel().length();
            int configLen = 0;
            int availableLen = String.valueOf(item.isAvailable()).length();

            String vehicleClass = "";

            if (item instanceof Car) {
                vehicleClass = ((Car) item).getCarType().toString();
                configLen = ((Car) item).getCarType().toString().length();
            } else if (item instanceof Motorcycle) {
                vehicleClass = ((Motorcycle) item).getMotorCycleType().toString();
                configLen = ((Motorcycle) item).getMotorCycleType().toString().length();
            }

            System.out.printf(
                    "%s"
                            + this.getColPadding(this.getColPaddingLength(colIdWidth, idLen))
                            + "%s"
                            + this.getColPadding(this.getColPaddingLength(colTypeWidth, typeLen))
                            + "%s"
                            + this.getColPadding(this.getColPaddingLength(colBrandWidth, brandLen))
                            + "%s"
                            + this.getColPadding(this.getColPaddingLength(colModelWidth, modelLen))
                            + "%s"
                            + this.getColPadding(
                                    this.getColPaddingLength(colConfigWidth, configLen))
                            + "%s"
                            + this.getColPadding(
                                    this.getColPaddingLength(colAvailableWidth, availableLen))
                            + "\n",
                    item.getId(),
                    item.getType(),
                    item.getBrand(),
                    item.getModel(),
                    vehicleClass,
                    item.isAvailable());
        }
    }

    public int getColPaddingLength(int colLength, int valueLength) {
        return valueLength < colLength ? colLength - valueLength + 2 : 2;
    }

    public String getColPadding(int colPadding) {
        return " ".repeat(colPadding);
    }
}
