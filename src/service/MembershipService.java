package service;

import registry.MemberRegistry;
import entity.Member;
import entity.MembershipLevel;
import lib.Input;
import registry.RentalRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembershipService {
    private final MemberRegistry memberRegistry;
    private final RentalRegistry rentalRegistry;

    public MembershipService(MemberRegistry memberRegistry, RentalRegistry rentalRegistry) {
        this.memberRegistry = memberRegistry;
        this.rentalRegistry = rentalRegistry;
    }

    public void displayAddMemberView() {
        System.out.println("\n");
        System.out.println(":: Add a new member");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Name");
        System.out.print("> ");
        String name = scanner.nextLine();

        Input.maybeExit(name);

        if (Input.isBack(name)) {
            return;
        }

        MembershipLevel level = null;

        while (level == null) {
            System.out.println("Membership level (bronze, silver, gold)");
            System.out.print("> ");
            String input = scanner.nextLine();

            Input.maybeExit(input);

            if (Input.isBack(input)) {
                return;
            }

            level = MembershipLevel.from(input);
        }

        this.memberRegistry.add(new Member(name, level, null));

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
            System.out.println("Name");
            System.out.print("> ");
            String input = scanner.nextLine();

            Input.maybeExit(input);

            if (Input.isBack(input)) {
                return;
            }

            members = this.memberRegistry.getByName(input);

            if (members.isEmpty()) {
                System.out.println("A member with that name couldn't be found.");
            }
        }

        System.out.println();
        this.memberRegistry.print(members);
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
            System.out.println("Id");
            System.out.print("> ");
            String input = scanner.nextLine();

            Input.maybeExit(input);

            if (Input.isBack(input)) {
                return;
            }

            int id = -1;

            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Id must be an integer.");
            }

            member = this.memberRegistry.get(id);

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
                    System.out.println("New name");
                    System.out.print("> ");

                    input = scanner.nextLine();

                    Input.maybeExit(input);

                    if (Input.isBack(input)) {
                        return;
                    }

                    member.setName(input);

                    break;
                } else if (input.equals("2")) {
                    System.out.println("\nCurrent membership level: " + member.getLevel());
                    System.out.print("New level (bronze, silver, gold)");
                    System.out.print("> ");
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

                    break;
                }
            }
        }
    }

    public void listMemberHistory() {
        //        System.out.println("\n");
        //        System.out.println(":: Update member information");
        //        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);
        Member member = null;

        while (member == null) {
            System.out.println("Id");
            System.out.print("> ");
            String input = scanner.nextLine();

            Input.maybeExit(input);

            if (Input.isBack(input)) {
                return;
            }

            int id = -1;

            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Id must be an integer.");
            }

            member = this.memberRegistry.get(id);

            if (member == null) {
                System.out.println("A member with that ID couldn't be found.");
            }
        }

        this.rentalRegistry.print(member.getHistory());
    }
}
