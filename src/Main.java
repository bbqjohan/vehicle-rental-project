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
    membershipService.promptInitMenu();
  }
}
