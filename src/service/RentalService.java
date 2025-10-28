package service;

import database.Inventory;
import database.MemberRegistry;
import entity.*;
import lib.Input;

import java.util.*;

public class RentalService {
  Inventory inventory;
  MemberRegistry memberRegistry;
  List<Rental> rentals = new ArrayList<>();

  RentalService(Inventory inventory, MemberRegistry memberRegistry) {
    this.inventory = inventory;
    this.memberRegistry = memberRegistry;
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
          case "1" -> {
            displayAllVehicles(this.inventory.getList());
            System.out.println("\nPress any key to continue.");
            scanner.nextLine();
            break innerLoop;
          }
          case "2" -> {
            displayFilterView();
            System.out.println("\nPress any key to continue.");
            scanner.nextLine();
            break innerLoop;
          }
          case Input.exit -> Input.maybeExit(input);
          case Input.back -> {
            return;
          }
        }
      }
    }
  }

  public void displayFilterView() {
    String explanation =
"""
* ========================================================
*
* How to filter.
*
* Available filters:
*
*   - type
*   - configuration
*   - available
*
* Single filter example:
*   type = car
*
* Multiple filters example:
*   type = car, available = true
*
* Spaces are optional and can be left out.
*
* ========================================================
""";
    Scanner scanner = new Scanner(System.in);

    mainLoop:
    while (true) {
      System.out.println("Enter your filtering criteria. Type \"help\" for an explanation.");
      System.out.print("> ");
      String input = scanner.nextLine();

      switch (input) {
        case Input.exit -> Input.maybeExit(input);
        case "help" -> System.out.println(explanation);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            Map<String, String> filters = this.inventory.parseFilters(input);
            List<Item> items = this.inventory.filter(filters);

            this.displayAllVehicles(items);
            break mainLoop;
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      }
    }

    System.out.println("\nPress any key to continue.");
    scanner.nextLine();
  }

  public void displayBookingView() {
    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {
      System.out.println("Enter vehicle ID");
      System.out.println("> ");

      Item vehicle = null;
      Member member = null;

      input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            vehicle = this.inventory.get(Integer.parseInt(input));
          } catch (Exception e) {
            if (e instanceof NumberFormatException) {
              System.out.println("A vehicle with that ID does not exist.");
            }
          }
        }
      }

      input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            member = this.memberRegistry.get(Integer.parseInt(input));
          } catch (Exception e) {
            if (e instanceof NumberFormatException) {
              System.out.println("A member with that ID does not exist.");
            }
          }
        }
      }

      if (member != null && vehicle != null) {
        Rental rental = new Rental(member, vehicle);
        this.rentals.add(rental);
      }
    }
  }

  public void displayAllVehicles(List<Item> items) {
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
              + this.getColPadding(this.getColPaddingLength(colConfigWidth, configLen))
              + "%s"
              + this.getColPadding(this.getColPaddingLength(colAvailableWidth, availableLen))
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
