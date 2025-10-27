import java.util.List;
import java.util.Scanner;

public class RentalService {
  Inventory inventory;

  RentalService(Inventory inventory) {
    this.inventory = inventory;
  }

  public void displayBookVehicleView() {
    while (true) {
      System.out.println("\n");
      System.out.println(":: Book vehicle");
      System.out.println("=========================================");

      System.out.println("\nSelect an option.\n");
      System.out.println("1. Show all vehicles\n2. Filter vehicles\n3. Book vehicle\n");

      innerLoop:
      while (true) {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
          case Input.exit -> Input.maybeExit(input);
          case Input.back -> {
            return;
          }
          case "1" -> {
            displayAllVehicles();
            System.out.println("\nPress any key to continue.");
            scanner.nextLine();
            break innerLoop;
          }
        }
      }
    }
  }

  public void displayAllVehicles() {
    List<Item> items = this.inventory.getList();

    System.out.println("Id\tType\t\tBrand\t\tModel\t\tConfiguration  Available");
    System.out.println("-----------------------------------------------------------------");

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
      colAvailableWidth = Math.max(String.valueOf(item.isAvailable()).length(), colAvailableWidth);

      if (item instanceof Car) {
        colConfigWidth = Math.max(((Car) item).getCarType().toString().length(), colConfigWidth);
      } else if (item instanceof Motorcycle) {
        colConfigWidth =
            Math.max(((Motorcycle) item).getMotorCycleType().toString().length(), colConfigWidth);
      }
    }

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

      String idColPadding = this.getColStringPadding(this.getColPadding(colIdWidth, idLen));
      String typeColPadding = this.getColStringPadding(this.getColPadding(colTypeWidth, typeLen));
      String brandColPadding =
          this.getColStringPadding(this.getColPadding(colBrandWidth, brandLen));
      String modelColPadding =
          this.getColStringPadding(this.getColPadding(colModelWidth, modelLen));
      String configColPadding =
          this.getColStringPadding(this.getColPadding(colConfigWidth, configLen));
      String availableColPadding =
          this.getColStringPadding(this.getColPadding(colAvailableWidth, availableLen));

      System.out.printf(
          "%s"
              + idColPadding
              + "%s"
              + typeColPadding
              + "%s"
              + brandColPadding
              + "%s"
              + modelColPadding
              + "%s"
              + configColPadding
              + "%s"
              + availableColPadding
              + "\n",
          item.getId(),
          item.getType(),
          item.getBrand(),
          item.getModel(),
          vehicleClass,
          item.isAvailable());
    }
  }

  public int getColPadding(int colLength, int valueLength) {
    return valueLength < colLength ? colLength - valueLength + 2 : 2;
  }

  public String getColStringPadding(int colPadding) {
    StringBuilder padding = new StringBuilder();

    for (; colPadding > 0; colPadding--) {
      padding.append(" ");
    }

    return padding.toString();
  }
}
