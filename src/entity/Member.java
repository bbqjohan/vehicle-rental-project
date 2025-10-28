package entity;

import java.util.List;

public class Member {
  private final int id;
  private String name;
  private MembershipLevel level;
  private List history;

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
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MembershipLevel getLevel() {
    return level;
  }

  public void setLevel(MembershipLevel level) {
    this.level = level;
  }

  public List getHistory() {
    return history;
  }

  public void setHistory(List history) {
    this.history = history;
  }

  public void display() {
    String[] str = new String[] {"Id\tLevel\tName", this.id + "\t" + this.level + "\t" + this.name};

    for (String line : str) {
      System.out.println(line);
    }
  }
}
