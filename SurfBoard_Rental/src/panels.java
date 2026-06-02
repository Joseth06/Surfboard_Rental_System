import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panels{
    private int itemWidth = 330;
    private int itemHeight = 90;
    private Dimension itemSize = new Dimension(itemWidth, itemHeight);

    private JPanel EQPStatus, topRow, midRow, botRow, totalRows;
    private JButton remove, view;
    private JLabel nameBoard, status, displayDue;
    private JTextField rent;
   

    
   
    public panels(){
        
    }

    public Component RentCus(String name, String Due){
        nameBoard  = new JLabel(name);
        displayDue = new JLabel("Due Rent");
        rent = new JTextField(Due);

        rent.setEditable(false);

        EQPStatus = new JPanel(new GridLayout(2, 2));
        EQPStatus.setPreferredSize(itemSize); 
        EQPStatus.setMaximumSize(itemSize);
        EQPStatus.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //Appearance
        EQPStatus.add(nameBoard);
        EQPStatus.add(new JLabel());
        EQPStatus.add(displayDue);
        EQPStatus.add(rent);

        //Colors
        nameBoard.setForeground (new Color(0xad9a6f));
        displayDue.setForeground(new Color(0xad9a6f));
        EQPStatus.setBackground(new Color(0x1a3052)); 

        return EQPStatus;
    }

    public Component AdminRentList(String name, String stat, String Due, JFrame parentFrame){
        nameBoard  = new JLabel(name);
        status = new JLabel(stat);
        displayDue = new JLabel("Due Rent");
        rent = new JTextField(Due);

        view = new JButton("View");
        remove = new JButton("Remove");

        rent.setEditable(false);

        view.addActionListener(e -> {
            if (parentFrame != null) {
                parentFrame.dispose(); // Closes the actual active screen
            }
            new Rental_Request("Admin");
        });

        remove.addActionListener(e -> {
            // None yet
        });

        EQPStatus = new JPanel(new BorderLayout());
        EQPStatus.setPreferredSize(new Dimension(330, 120));
        EQPStatus.setMaximumSize(new Dimension(330, 120));
        EQPStatus.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    

        //Top row: name and edit button
        topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);
        topRow.add(nameBoard, BorderLayout.WEST);
        topRow.add(view, BorderLayout.EAST);

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