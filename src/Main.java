import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    //    MemberRegistry memberRegisty = new MemberRegistry();
    //
    //    Member[] members = {
    //      new Member("A", MembershipLevel.BRONZE),
    //      new Member("B", MembershipLevel.SILVER),
    //      new Member("C", MembershipLevel.GOLD)
    //    };
    //
    //    for (Member member : members) {
    //      memberRegisty.add(member);
    //    }
    //
    //    Inventory inventory = new Inventory();
    //
    //    var cars =
    //        new Car[] {
    //          new Car("Red", "Audi", "S3", CarType.SEDAN, 4),
    //          new Car("White", "Tesla", "Model Y", CarType.SUV, 4),
    //          new Car("Blue", "Fiat", "500", CarType.COMPACT, 2),
    //        };

    //    for (Car car : cars) {
    //      inventory.add(car);
    //    }

    MembershipService membershipService = new MembershipService();
    //    membershipService.promptInitMenu();

    startMenu();
  }

  static void startMenu() {
    System.out.println(
        "=========================================================================================");
    System.out.println("Welcome to Vehicle Rentals!");
    System.out.println(
        "A club where you can register and start renting exclusive vehicles for your pleasure!");
    System.out.println(
        "=========================================================================================");

    System.out.println("\nPlease select an option below.\n");
    System.out.println("Type \"exit\" at any point to exit the application.");
    System.out.println("Type \"back\" to go back to a previous menu\n");
    System.out.println("Members\t\t\t\tBooking");
    System.out.println("----------------    -------------------");
    System.out.println("1. Add member\t\t4. Book vehicle");
    System.out.println("2. Find member\t\t5. Return vehicle");
    System.out.println("3. Update member");

    Scanner scanner = new Scanner(System.in);

    System.out.print("\n> ");
    String input = scanner.nextLine();

    System.out.println(input);
  }
}
