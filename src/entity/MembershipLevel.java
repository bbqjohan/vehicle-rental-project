package entity;

public enum MembershipLevel {
    BRONZE,
    GOLD,
    SILVER;

    public static MembershipLevel from(String str) {
        return switch (str.toLowerCase()) {
            case "bronze" -> BRONZE;
            case "silver" -> SILVER;
            case "gold" -> GOLD;
            default -> null;
        };
    }
}
