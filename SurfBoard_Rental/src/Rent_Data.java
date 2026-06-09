import java.util.ArrayList;

public class Rent_Data {

    public static class RentalEntry {
        public String boardName, name, due, status, custName, phone,
                      pack, date, period;
        public int boardNum, hour, min, duration, days; 
        public double cost;
        




        RentalEntry(String boardName, String due, double cost, String status) {
            this.boardName = boardName;
            this.due       = due;
            this.cost      = cost;
            this.status    = status;
        }

        RentalEntry(String boardName, String due, double cost, String status,
                    String custName, String phone, int boardNum, String pack,
                    String date, int hour, int min, String period, int duration, int days){
            this.boardName = boardName;
            this.status = status;
            this.custName = custName;
            this.phone = phone;
            this.boardNum = boardNum;
            this.pack = pack;
            this.date = date;
            this.hour = hour;
            this.min = min;
            this.period = period;
            this.duration = duration;
            this.days = days;
            this.cost = cost;
            this.due = due;
        }

    }

    public static ArrayList<RentalEntry> rentals = new ArrayList<>();
    public static int rentCount = 0;

    
    public static void addRental(String due, double cost, String status) {
        rentCount++;
        rentals.add(new RentalEntry("Surfboard " + rentCount, due, cost, status));
    }

    public static void saveRental(String custName, String phone, int boardNum, String pack,
                                  String date, int hour, int min, String period,
                                  int duration, int days, double cost, String due)
    {
        rentCount++;
        rentals.add(new RentalEntry("Surfboard " + rentCount, due, cost, "In use",
                                    custName, phone, boardNum, pack, date, hour, min,
                                    period, duration, days

        ));                       

    }

    
}