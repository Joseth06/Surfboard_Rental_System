import javax.swing.*;
import java.awt.*;

public class Rental_Request extends JFrame{
    Rental_Request(){
        JLabel ReqTitle, numBoards, pack,
        date, time, day, cost, due;
        JTextField showBoards, showDate, showTime, 
        showDays, showCost, showDue;
        JButton btnDone, btnCancel;
        JPanel ReqPanel;

        String[] Packages = {"With Instructor", "No Instructor"};

        //JLabels Instantiation
        ReqTitle = new JLabel("Surfboard Request");
        numBoards = new JLabel("Amount of Surfboards");
        pack = new JLabel("Package");
        time = new JLabel("HH:MM");
        day = new JLabel("Day(s)");
        cost = new JLabel("Cost");
        due = new JLabel("Due Rent");

        //JComboBox Declaration and Instantiation
        JComboBox<String> optionPack = new JComboBox<>(Packages);

        //JTextField Instantiation
        showBoards = new JTextField("XX");
        showDate = new JTextField("00/00/0000");
        showTime = new JTextField("XX:XX");
        showDays = new JTextField("XX");
        showCost = new JTextField("$$$$$$.$$");
        showDue = new JTextField("00/00/0000 XX:XX");

        //JButton Instantiation
        btnDone = new JButton("Done");
        btnCancel = new JButton("Cancel");

        //JPanel Instantiation
        ReqPanel = new JPanel();

        

        




        // Colors

        setSize(417, 485);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Rental_Request();
    }
}