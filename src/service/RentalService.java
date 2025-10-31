package service;

import registry.Inventory;
import registry.MemberRegistry;
import entity.*;
import lib.Input;

import java.util.*;

public class RentalService {
  Inventory inventory;
  MemberRegistry memberRegistry;
  List<Rental> rentals = new ArrayList<>();

  public RentalService(Inventory inventory, MemberRegistry memberRegistry) {
    this.inventory = inventory;
    this.memberRegistry = memberRegistry;
  }

  public void displayRentVehicleView() {
    while (true) {
      System.out.println("\n");
      System.out.println(":: Rent vehicle");
      System.out.println("=========================================");

      System.out.println("\nSelect an option.\n");
      System.out.println("1. Show all vehicles\n2. Filter vehicles\n3. Choose vehicle\n");

      innerLoop:
      while (true) {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
          case "1" -> {
            this.inventory.printInventory(this.inventory.getList());
            System.out.println("\nPress any key to continue.");
            scanner.nextLine();
            break innerLoop;
          }
          case "2" -> {
            displayFilterVehiclesView();
            System.out.println("\nPress any key to continue.");
            scanner.nextLine();
            break innerLoop;
          }
          case "3" -> {
            displayChooseVehicleView();
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

  public void displayFilterVehiclesView() {
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

            this.inventory.printInventory(items);
            break mainLoop;
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      }
    }
  }

  public void displayChooseVehicleView() {
    Scanner scanner = new Scanner(System.in);
    String input;
    Item item;
    Member member;
    int duration;

    loop:
    while (true) {
      System.out.println("Enter vehicle ID");
      System.out.print("> ");

      input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            item = this.inventory.get(Integer.parseInt(input));

            if (item == null) {
              throw new Exception("No item with supplied ID.");
            } else if (this.isBooked(item)) {
              System.out.println("Vehicle is already booked. Please select another.");
              break;
            }

            break loop;
          } catch (Exception e) {
            // Matches if input cannot be parsed to an integer, or if the
            // item cannot be found.
            System.out.println("A vehicle with that ID does not exist.");
          }
        }
      }
    }

    loop:
    while (true) {
      System.out.println("Enter member ID");
      System.out.print("> ");
      input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            member = this.memberRegistry.get(Integer.parseInt(input));

            if (member == null) {
              throw new Exception("No member with supplied ID.");
            }

            break loop;
          } catch (Exception e) {
            System.out.println("A member with that ID does not exist.");
          }
        }
      }
    }

    loop:
    while (true) {
      System.out.println("For how many days? (integer)");
      System.out.print("> ");

      input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        default -> {
          try {
            duration = Integer.parseInt(input);
            break loop;
          } catch (Exception e) {
            System.out.println("Please enter an integer.");
          }
        }
      }
    }

    Rental rental = new Rental(member, item, duration);
    this.rentals.add(rental);

    System.out.println("\nPress any key to continue.");
    scanner.nextLine();
  }

  public void displayEndBookingView() {
    Scanner scanner = new Scanner(System.in);

    mainLoop:
    while (true) {
      System.out.println("1. List bookings");
      System.out.println("2. End booking\n");
      System.out.print("> ");

      String input = scanner.nextLine();

      switch (input.toLowerCase()) {
        case Input.exit -> Input.maybeExit(input);
        case Input.back -> {
          return;
        }
        case "1" -> {
          this.printBookings(this.rentals);
        }
        case "2" -> {}
      }
    }
  }

  public void printBookings(List<Rental> bookings) {
    System.out.println("Id\tMemberId\tItemId\tBooked\t\tEnded");
    System.out.println("---------------------------------------------------------------");

    for (Rental booking : bookings) {
      System.out.printf(
          booking.getId()
              + "\t"
              + booking.getMember().getId()
              + "\t\t\t"
              + booking.getItem().getId()
              + "\t\t"
              + booking.getStarted()
              + "\t"
              + booking.getEnded()
              + "\n");
    }
  }

  public boolean isBooked(Item item) {
    return this.rentals.stream().anyMatch((rental) -> rental.getItem().getId() == item.getId());
  }
}
