package entity;

public interface PricePolicy {
    default double getMembershipFee(MembershipLevel status, int daysRented) {
        return switch (status) {
            case BRONZE -> this.getBronzeMembershipFee(daysRented);
            case SILVER -> this.getSilverMembershipFee(daysRented);
            case GOLD -> this.getGoldMembershipFee(daysRented);
            case null -> 0;
        };
    }

    double getBronzeMembershipFee(int daysRented);

    double getSilverMembershipFee(int daysRented);

    double getGoldMembershipFee(int daysRented);
}
