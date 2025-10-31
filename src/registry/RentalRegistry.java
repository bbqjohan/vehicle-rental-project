package registry;

import entity.Item;
import entity.Rental;

import java.util.*;

public class RentalRegistry {
  private final TreeMap<Integer, Rental> rentals = new TreeMap<>();

  public void add(Rental rental) {
    if (rental == null) {
      return;
    }

    this.rentals.put(rental.getId(), rental);
  }

  public Rental get(int id) {
    return this.rentals.get(id);
  }

  public boolean isRented(Item item) {
    return this.getList().stream().anyMatch((rental) -> rental.getItem().getId() == item.getId());
  }

  public List<Rental> getList() {
    return new ArrayList<>(this.rentals.values());
  }

  public void print(List<Rental> rentals) {
    System.out.println("Id\tMemberId\tItemId\tDays\tBooked\t\tEnded");
    System.out.println("---------------------------------------------------------------");

    for (Rental booking : rentals) {
      System.out.printf(
          booking.getId()
              + "\t"
              + booking.getMember().getId()
              + "\t\t\t"
              + booking.getItem().getId()
              + "\t"
              + booking.getDuration()
              + "\t\t"
              + booking.getStarted()
              + "\t"
              + booking.getEnded()
              + "\n");
    }
  }
}
