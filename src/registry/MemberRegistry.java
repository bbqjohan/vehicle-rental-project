package registry;

import entity.Member;

import java.util.*;

public class MemberRegistry {
    private final Map<Integer, Member> members = new TreeMap<>();

    public void add(Member member) {
        if (member == null) {
            return;
        }

        this.members.put(member.getId(), member);
    }

    public Member get(int id) {
        return this.members.get(id);
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

    public void print(List<Member> members) {
        System.out.println("Id\tLevel\tName");
        System.out.println("--------------------------------");

        for (Member member : members) {
            System.out.printf("%s\t%s\t%s\n", member.getId(), member.getLevel(), member.getName());
        }
    }
}
