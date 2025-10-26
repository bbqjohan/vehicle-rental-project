import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembershipService {
  private MemberRegistry registry = new MemberRegistry();

  // TODO Constructor is only for testing.
  MembershipService() {
    Member[] members = {
      new Member("Bosse", MembershipLevel.BRONZE),
      new Member("Sara", MembershipLevel.SILVER),
      new Member("Emil", MembershipLevel.GOLD),
      new Member("Bosse", MembershipLevel.BRONZE),
      new Member("Jennifer", MembershipLevel.SILVER),
    };

    for (Member member : members) {
      registry.add(member);
    }
  }

  public void displayAddMemberView() {
    System.out.println("\n");
    System.out.println(":: Add a new member");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);

    System.out.print("Name: ");
    String name = scanner.nextLine();

    Input.maybeExit(name);

    if (Input.isBack(name)) {
      return;
    }

    MembershipLevel level = null;

    while (level == null) {
      System.out.print("Membership level (bronze, silver, gold): ");
      String input = scanner.nextLine();

      Input.maybeExit(input);

      if (Input.isBack(input)) {
        return;
      }

      level = MembershipLevel.from(input);
    }

    this.registry.add(new Member(name, level));

    System.out.printf("\n-- New member \"%s\" registered! --%n", name);
    System.out.println("Press any key to continue");

    scanner.nextLine();
  }

  public void displayFindMemberView() {
    System.out.println("\n");
    System.out.println(":: Find a member");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);
    List<Member> members = new ArrayList<>();

    while (members.isEmpty()) {
      System.out.print("Name: ");
      String input = scanner.nextLine();

      Input.maybeExit(input);

      if (Input.isBack(input)) {
        return;
      }

      members = this.registry.getByName(input);

      if (members.isEmpty()) {
        System.out.println("A member with that name couldn't be found.");
      }
    }

    System.out.println();
    this.registry.listMembers(members);
    System.out.println("\nPress any key to continue.");
    scanner.nextLine();
  }

  public void displayUpdateMemberView() {
    System.out.println("\n");
    System.out.println(":: Update member information");
    System.out.println("=========================================");

    Scanner scanner = new Scanner(System.in);
    Member member = null;

    while (member == null) {
      System.out.print("Id: ");
      String input = scanner.nextLine();

      Input.maybeExit(input);

      if (Input.isBack(input)) {
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
      System.out.printf(
          "\n<< Selected member: %s, %s, %s >>\n\n",
          member.getId(), member.getName(), member.getLevel());
      System.out.println("1. Name");
      System.out.println("2. Membership level");

      System.out.print("> ");
      String input = scanner.nextLine();

      Input.maybeExit(input);

      if (Input.isBack(input)) {
        return;
      }

      while (true) {
        if (input.equals("1")) {
          System.out.println("\nCurrent name: " + member.getName());
          System.out.print("New name: ");

          input = scanner.nextLine();

          Input.maybeExit(input);

          if (Input.isBack(input)) {
            return;
          }

          member.setName(input);
          this.registry.set(member);

          break;
        } else if (input.equals("2")) {
          System.out.println("\nCurrent membership level: " + member.getLevel());
          System.out.print("New level (bronze, silver, gold): ");
          input = scanner.nextLine();

          Input.maybeExit(input);

          if (Input.isBack(input)) {
            return;
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
