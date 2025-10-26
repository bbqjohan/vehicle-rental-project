public interface PricePolicy {
  double getMembershipFee(MembershipLevel status);

  double getBronzeMembershipFee();

  double getSilverMembershipFee();

  double getGoldMembershipFee();
}
