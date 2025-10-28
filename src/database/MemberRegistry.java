package database;

import entity.Member;

import java.util.*;

public class MemberRegistry {
  private int idCount = 0;
  private final Map<Integer, Member> members = new TreeMap<>();

  public void add(Member member) {
    if (member == null) {
      return;
    }

    this.members.put(
        idCount, new Member(idCount, member.getName(), member.getLevel(), member.getHistory()));

    idCount++;
  }

  public boolean has(int id) {
    return this.members.containsKey(id);
  }

  public void set(Member member) {
    if (member == null || !has(member.getId())) {
      return;
    }

    this.members.put(
        member.getId(),
        new Member(member.getId(), member.getName(), member.getLevel(), member.getHistory()));
  }

  public Member get(int id) {
    Member member = this.members.get(id);

    if (member != null) {
      member = new Member(member);
    }

    return member;
  }

  public List<Member> getList() {
    return new ArrayList<>(this.members.values());
  }

  public List<Member> getByName(String name) {
    List<Member> members = new ArrayList<>();

    for (Member member : this.getList()) {
      if (member.getName().equalsIgnoreCase(name)) {
        members.add(member);
      }
    }

    return members;
  }

  public void listMembers(List<Member> members) {
    System.out.println("Id\tLevel\tName");
    System.out.println("--------------------------------");

    for (Member member : members) {
      System.out.printf("%s\t%s\t%s\n", member.getId(), member.getLevel(), member.getName());
    }
  }
}
