import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Inventory {
  private int idCount = 0;
  private TreeMap<Integer, Item> items = new TreeMap<>();

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
}
