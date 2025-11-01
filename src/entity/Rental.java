package entity;

import java.time.LocalDate;

public class Rental {
    private static int idCount = 0;
    private final int id;
    private final Member member;
    private final Item item;
    private final int duration;
    private LocalDate started;
    private LocalDate ended;
    private double fee = 0;

    public Rental(Member member, Item item, int duration) {
        this.member = member;
        this.item = item;
        this.duration = duration;
        this.started = LocalDate.now();
        this.id = idCount++;
    }

    public int getId() {
        return this.id;
    }

    public void end() {
        if (this.ended == null) {
            this.ended = this.started.plusDays(this.duration);
            this.fee = this.item.getMembershipFee(this.member.getLevel(), this.duration);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public LocalDate getStarted() {
        return this.started;
    }

    public LocalDate getEnded() {
        return this.ended;
    }

    public Member getMember() {
        return this.member;
    }

    public Item getItem() {
        return this.item;
    }
}
