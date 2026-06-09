import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panels{
    private int itemWidth = 330;
    private int itemHeight = 120;
    private int gapHeight = (int) (itemHeight * 0.20); 
    private Dimension itemSize = new Dimension(itemWidth, itemHeight);
    


    private JPanel EQPStatus, topRow, midRow, botRow, totalRows, scrollPanel;
    private JButton remove, viewAdmin, viewCus;
    private JLabel nameBoard, status, displayDue;
    private JTextField rent, costOutput;
    
    
    
   

    
   
    public panels(){
        
    }

    public Component addRentCus(String name, String Due, double cost, JFrame parentFrame, Rent_Data.RentalEntry entry){
        viewCus = new JButton("View");
        nameBoard  = new JLabel(name);
        costOutput = new JTextField(String.format("₱ %.2f", cost));
        displayDue = new JLabel("Due Rent");
        rent = new JTextField(Due);

        costOutput.setEditable(false);
        rent.setEditable(false);

        EQPStatus = new JPanel(new GridLayout(3, 2, 0, 5));
        EQPStatus.setPreferredSize(itemSize); 
        EQPStatus.setMaximumSize(itemSize);
        EQPStatus.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //Appearance
        EQPStatus.add(nameBoard);
        EQPStatus.add(costOutput);
        EQPStatus.add(displayDue);
        EQPStatus.add(rent);
        EQPStatus.add(new JLabel());
        EQPStatus.add(viewCus);

        //Events 
        viewCus.addActionListener(e -> {
            if (parentFrame != null) {
                parentFrame.dispose(); // Closes the actual active screen
            }
            new Rental_Request("User", entry);
        });
        //Colors
        nameBoard.setForeground (new Color(0xad9a6f));
        displayDue.setForeground(new Color(0xad9a6f));
        EQPStatus.setBackground(new Color(0x1a3052)); 

        return EQPStatus;
    }

    public Component AdminRentList(String name, String stat, String Due, double cost, JFrame parentFrame, Rent_Data.RentalEntry entry){
        nameBoard  = new JLabel(name);
        status = new JLabel(stat);
        displayDue = new JLabel("Due Rent");
        rent = new JTextField(Due);

        viewAdmin = new JButton("View");
        remove = new JButton("Remove");

        rent.setEditable(false);

        viewAdmin.addActionListener(e -> {
            if (parentFrame != null) {
                parentFrame.dispose(); // Closes the actual active screen
            }
            new Rental_Request("Admin", entry);
            
        });


        remove.addActionListener(e -> {
            Rent_Data.removeRental(entry); 
            parentFrame.dispose();
            Rent_Data.saveToFile();
            new Admin_List("Admin");
        });

        EQPStatus = new JPanel(new BorderLayout());
        EQPStatus.setPreferredSize(new Dimension(330, 120));
        EQPStatus.setMaximumSize(new Dimension(330, 120));
        EQPStatus.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    

        //Top row: name and edit button
        topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);
        topRow.add(nameBoard, BorderLayout.WEST);
        topRow.add(viewAdmin, BorderLayout.EAST);

        // Middle row: state and remove button
        midRow = new JPanel(new BorderLayout());
        midRow.setOpaque(false);
        midRow.add(status, BorderLayout.WEST);
        midRow.add(remove, BorderLayout.EAST);

        // Bottom row: Due Rent 
        botRow = new JPanel(new GridLayout(0, 2));
        botRow.setOpaque(false);
        botRow.add(displayDue);
        botRow.add(rent);

        // Stack the 3 rows
        totalRows = new JPanel(new GridLayout(3, 1, 3, 4));
        totalRows.setOpaque(false);
        totalRows.add(topRow);
        totalRows.add(midRow);
        totalRows.add(botRow);
        EQPStatus.add(totalRows, BorderLayout.CENTER);

        //Colors
        nameBoard.setForeground (new Color(0xad9a6f));
        displayDue.setForeground(new Color(0xad9a6f));
        status.setForeground(new Color(0xad9a6f));
        EQPStatus.setBackground(new Color(0x1a3052));

        return EQPStatus;


    }
}
