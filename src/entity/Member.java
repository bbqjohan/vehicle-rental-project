package entity;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private static int idCount = 0;
    private final int id;
    private String name;
    private MembershipLevel level;
    private final List<Rental> history;

    public Member(String name, MembershipLevel level, List<Rental> history) {
        this.name = name;
        this.level = level;
        this.history = history == null ? new ArrayList<>() : history;
        this.id = idCount++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MembershipLevel getLevel() {
        return this.level;
    }

    public void setLevel(MembershipLevel level) {
        this.level = level;
    }

    public List<Rental> getHistory() {
        return this.history;
    }

    public void addHistoryEntry(Rental rental) {
        this.history.add(rental);
    }
}
