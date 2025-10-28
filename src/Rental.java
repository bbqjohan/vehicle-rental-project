import java.util.Date;

public class Rental {
  private int memberId;
  private int itemId;
  private double fee;
  private Date date;
  private Date due;

  public Rental(int memberId, int itemId, double fee, Date date, Date due) {
    this.memberId = memberId;
    this.itemId = itemId;
    this.fee = fee;
    this.date = date;
    this.due = due;
  }
}
