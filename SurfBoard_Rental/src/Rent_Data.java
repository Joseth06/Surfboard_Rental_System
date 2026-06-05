import java.util.ArrayList;

public class Rent_Data {

    public static class RentalEntry {
        public String boardName;
        public String due;
        public double cost;
        public String status;

        RentalEntry(String boardName, String due, double cost, String status) {
            this.boardName = boardName;
            this.due       = due;
            this.cost      = cost;
            this.status    = status;
        }
    }

    public static ArrayList<RentalEntry> rentals = new ArrayList<>();
    public static int rentCount = 0;

    public static void addRental(String due, double cost, String status) {
        rentCount++;
        rentals.add(new RentalEntry("Surfboard " + rentCount, due, cost, status));
    }
}