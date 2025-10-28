package database;

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

  public boolean has(int id) {
    return this.items.containsKey(id);
  }

  public void set(Item item) {
    if (item == null || !has(item.getId())) {
      return;
    }

    this.items.put(item.getId(), item);
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
                && !((Motorcycle) item).getMotorCycleType().toString().equalsIgnoreCase(value)) {
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
}
