package entity;

import java.util.Date;

public class Rental {
  private Member member;
  private Item item;
  private Date date;
  private Date due;

  public Rental(Member member, Item item) {
    this.member = member;
    this.item = item;
    this.date = new Date();
    this.due = new Date(this.date.getTime() + 10000);
  }
}
