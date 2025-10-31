package entity;

import java.util.List;

public class Member {
  private final int id;
  private String name;
  private MembershipLevel level;
  private List<Rental> history;

  public Member(int id, String name, MembershipLevel level, List history) {
    this.name = name;
    this.level = level;
    this.history = history;
    this.id = id;
  }

  public Member(String name, MembershipLevel level) {
    this(-1, name, level, null);
  }

  public Member(Member member) {
    this(member.id, member.name, member.level, member.history);
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

  public void addHistory(Rental rental) {
    this.history.add(rental);
  }
}
