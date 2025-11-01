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
        return this.getList().stream()
                .anyMatch(
                        (rental) ->
                                rental.getItem().getId() == item.getId() && !item.isAvailable());
    }

    public List<Rental> getList() {
        return new ArrayList<>(this.rentals.values());
    }

    public void print(List<Rental> rentals) {
        System.out.println("Id\tMemberId\tItemId\tDays\tRented\t\tEnded");
        System.out.println("---------------------------------------------------------------");

        for (Rental booking : rentals) {
            System.out.printf(
                    booking.getId()
                            + "\t"
                            + booking.getMember().getId()
                            + "\t\t\t"
                            + booking.getItem().getId()
                            + "\t\t"
                            + booking.getDuration()
                            + "\t\t"
                            + booking.getStarted()
                            + "\t"
                            + booking.getEnded()
                            + "\n");
        }
    }

    public double getProfits() {
        return this.getList().stream()
                .filter((r) -> r.getEnded() != null)
                .map(Rental::getFee)
                .reduce(0d, Double::sum);
    }
}
