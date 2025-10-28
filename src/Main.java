import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Inventory inventory = new Inventory();

    var cars =
        new Car[] {
          new Car("Red", "Audi", "S3", CarType.SEDAN, true),
          new Car("White", "Honda", "Accord", CarType.SEDAN, true),
          new Car("Black", "Toyota", "Crown", CarType.SEDAN, true),
          new Car("White", "Tesla", "Model Y", CarType.SUV, false),
          new Car("White", "Ford", "Explorer", CarType.SUV, true),
          new Car("White", "Ford", "Bronco", CarType.SUV, false),
          new Car("Blue", "Fiat", "500", CarType.COMPACT, true),
          new Car("Red", "Volkswagen", "Golf", CarType.COMPACT, true),
          new Car("Black", "Toyota", "Corolla", CarType.COMPACT, true),
        };

    var motorcycles =
        new Motorcycle[] {
          new Motorcycle("Red", "Suzuki", "Hayabusa", MotorcycleType.SPORT, true),
          new Motorcycle("White", "Yamaha", "YZF-R6R", MotorcycleType.SPORT, true),
          new Motorcycle("Black", "Kawasaki", "Ninja", MotorcycleType.SPORT, true),
          new Motorcycle("White", "Suzuki", "Boulevard", MotorcycleType.CRUISER, true),
          new Motorcycle("Black", "Yamaha", "V Star 250", MotorcycleType.CRUISER, true),
          new Motorcycle("White", "Honda", "CMX 1100", MotorcycleType.CRUISER, true),
          new Motorcycle("Blue", "Honda", "SH350", MotorcycleType.SCOOTER, true),
          new Motorcycle("Red", "Aprilla", "SR150", MotorcycleType.SCOOTER, true),
          new Motorcycle("Blue", "Aprilla", "SR160", MotorcycleType.SCOOTER, true),
        };

    for (Car car : cars) {
      inventory.add(car);
    }

    for (Motorcycle motorcycle : motorcycles) {
      inventory.add(motorcycle);
    }

    //    startMenu(inventory);
    new RentalService(inventory).displayFilterView();
  }

  static void startMenu(Inventory inventory) {
    System.out.println(
        "=========================================================================================");
    System.out.println("Welcome to Vehicle Rentals!");
    System.out.println(
        "A club where you can register and start renting exclusive vehicles for your pleasure!");
    System.out.println(
        "=========================================================================================");

    Scanner scanner = new Scanner(System.in);
    MembershipService membershipService = new MembershipService();
    RentalService rentalService = new RentalService(inventory);

    while (true) {
      System.out.println("\nPlease select an option below.\n");
      System.out.println("Type \"exit\" at any point to exit the application.");
      System.out.println("Type \"back\" to go back to a previous menu\n");
      System.out.println("Members\t\t\t\tBooking");
      System.out.println("----------------    -------------------");
      System.out.println("1. Add member\t\t4. Book vehicle");
      System.out.println("2. Find member\t\t5. Return vehicle");
      System.out.println("3. Update member");
      System.out.println();

      innerLoop:
      while (true) {
        System.out.print("> ");
        String input = scanner.nextLine();

        Input.maybeExit(input);

        switch (input) {
          case "1":
            membershipService.displayAddMemberView();
            break innerLoop;
          case "2":
            membershipService.displayFindMemberView();
            break innerLoop;
          case "3":
            membershipService.displayUpdateMemberView();
            break innerLoop;
          case "4":
            rentalService.displayBookVehicleView();
            break innerLoop;
        }
      }
    }
  }
}
