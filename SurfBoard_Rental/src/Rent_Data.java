import java.util.ArrayList;
import java.io.*;

public class Rent_Data {
    public static int totalBoards = 10;
    static final String FILE_PATH = "rentals.txt";
    

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
        rentals.add(new RentalEntry("Surfboard Rent " + rentCount, due, cost, status));
    }

    public static void saveRental(String custName, String phone, int boardNum, String pack,
                                  String date, int hour, int min, String period,
                                  int duration, int days, double cost, String due)
    {
        rentCount++;
        rentals.add(new RentalEntry("Surfboard Rent " + rentCount, due, cost, "In use",
                                    custName, phone, boardNum, pack, date, hour, min,
                                    period, duration, days

        ));                       

    }
    public static boolean isDuplicate(String custName) {
    for (RentalEntry e : rentals) {
        if (e.custName != null) {
            if (e.custName.equalsIgnoreCase(custName)) {
                return true; // discovers the duplicate
                }
            }
        }
    return false;
    }
    public static void removeRental(RentalEntry entry) {
    rentals.remove(entry);
    rentCount = rentals.size(); // ← reset to actual size, not just decrementing it

    // Renumber all remaining entries
    for (int i = 0; i < rentals.size(); i++) {
        rentals.get(i).boardName = "Surfboard Rent " + (i + 1);
        }
    }


    // File Handling
    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (RentalEntry e : rentals) {
                writer.println(
                    e.boardName + "," + e.status + "," + e.due + "," + e.cost + "," +
                    e.custName + "," + e.phone + "," + e.boardNum + "," + e.pack + "," +
                    e.date + "," + e.hour + "," + e.min + "," + e.period + "," +
                    e.duration + "," + e.days
                );
         }
        } catch (IOException ex) {
            System.out.println("Save error: " + ex.getMessage());
        }
    }
    public static void loadFromFile() {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
        return; // no file yet, just start empty
    }

    rentals.clear();
    rentCount = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] p = line.split(",");
            if (p.length < 14) continue;
            rentCount++;
            RentalEntry e = new RentalEntry(p[0], p[2], Double.parseDouble(p[3]), p[1]);
            e.custName = p[4];
            e.phone    = p[5];
            e.boardNum = Integer.parseInt(p[6]);
            e.pack     = p[7];
            e.date     = p[8];
            e.hour     = Integer.parseInt(p[9]);
            e.min      = Integer.parseInt(p[10]);
            e.period   = p[11];
            e.duration = Integer.parseInt(p[12]);
            e.days     = Integer.parseInt(p[13]);
            rentals.add(e);
        }
    } catch (IOException ex) {
        System.out.println("Load error: " + ex.getMessage());
    }
}
    
}