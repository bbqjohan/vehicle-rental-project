public interface PricePolicy {
  default double getMembershipFee(MembershipLevel status) {
    return switch (status) {
      case BRONZE -> this.getBronzeMembershipFee();
      case SILVER -> this.getSilverMembershipFee();
      case GOLD -> this.getGoldMembershipFee();
      case null -> 0;
    };
  }

  double getBronzeMembershipFee();

  double getSilverMembershipFee();

  double getGoldMembershipFee();
}
