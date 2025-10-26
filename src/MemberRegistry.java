import java.util.*;

public class MemberRegistry {
  private int idCount = 0;
  private Map<Integer, Member> members = new TreeMap<>();

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
    return new ArrayList<Member>(this.members.values());
  }

  public Member getByName(String name) {
    for (Member member : this.getList()) {
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }

    return null;
  }
}
