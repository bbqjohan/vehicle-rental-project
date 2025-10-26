import java.util.Scanner;

public class MembershipService {
  private MemberRegistry registry = new MemberRegistry();

  public void promptInitMenu() {
    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {
      System.out.println("\n=========================================");
      System.out.println("Welcome to the membership service.");
      System.out.println("=========================================");
      System.out.println("\nPlease select an option below.");
      System.out.println("\n1. Add member");
      System.out.println("2. Find member");
      System.out.println("3. Update member\n");

      while (true) {
        System.out.print("Your choice: ");
        input = scanner.nextLine();

        if (this.hasUserExited(input)) {
          return;
        }

        if (input.equals("1")) {
          promptAddMember();
          break;
        } else if (input.equals("2")) {
          promptFindMember();
          break;
        } else if (input.equals("3")) {
          promptUpdateMember();
          break;
        }
      }
    }
  }

  private void promptAddMember() {
    System.out.println("\n=========================================");
    System.out.println("Add a new member");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter name: ");
    String name = scanner.nextLine();

    if (this.hasUserExited(name)) {
      return;
    }

    MembershipLevel level = null;

    while (level == null) {
      System.out.print("Enter membership level (bronze, silver, gold): ");
      String line = scanner.nextLine();

      level = MembershipLevel.from(line);
    }

    this.registry.add(new Member(name, level));
  }

  private void promptFindMember() {
    System.out.println("\n=========================================");
    System.out.println("Find a member");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);
    Member member = null;

    while (member == null) {
      System.out.print("Enter member name: ");
      String input = scanner.nextLine();

      if (this.hasUserExited(input)) {
        return;
      }

      member = this.registry.getByName(input);

      if (member == null) {
        System.out.println("A member with that name couldn't be found.");
      }
    }

    System.out.println();
    member.display();
    System.out.println("\nPress any key to continue.");
    scanner.nextLine();
  }

  private void promptUpdateMember() {
    System.out.println("\n=========================================");
    System.out.println("Update a member");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);
    Member member = null;

    while (member == null) {
      System.out.print("Enter member id: ");
      String input = scanner.nextLine();

      if (this.hasUserExited(input)) {
        return;
      }

      int id = -1;

      try {
        id = Integer.parseInt(input);
      } catch (Exception e) {
        // Do nothing.
      }

      member = this.registry.get(id);

      if (member == null) {
        System.out.println("A member with that ID couldn't be found.");
      }
    }

    while (true) {
      System.out.println("\nSelect what you want to update for member " + member.getId() + "\n");
      System.out.println("1. Name");
      System.out.println("2. Membership level");

      System.out.print("Your choice: ");
      String input = scanner.nextLine();

      if (this.hasUserExited(input)) {
        return;
      }

      while (true) {
        if (input.equals("1")) {
          System.out.println("\nCurrent name: " + member.getName());
          System.out.print("New name: ");

          input = scanner.nextLine();

          if (this.hasUserExited(input)) {
            break;
          }

          member.setName(input);
          this.registry.set(member);

          break;
        } else if (input.equals("2")) {
          System.out.println("\nCurrent membership level: " + member.getLevel());
          System.out.print("New level (bronze, silver, gold): ");
          input = scanner.nextLine();

          if (this.hasUserExited(input)) {
            break;
          }

          MembershipLevel newLevel = MembershipLevel.from(input);

          if (newLevel == null) {
            System.out.println(
                "Invalid membership level. It must be either bronze, silver or gold.");
          }

          member.setLevel(newLevel);
          this.registry.set(member);

          break;
        }
      }
    }
  }

  public boolean hasUserExited(String input) {
    return input.equalsIgnoreCase("exit");
  }
}
