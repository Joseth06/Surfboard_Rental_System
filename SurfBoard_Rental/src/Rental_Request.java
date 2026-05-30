import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rental_Request extends JFrame{
    Rental_Request(){
        JLabel ReqTitle, numBoards, pack,
        date, time, day, cost, due;
        JTextField showBoards, showDate, showTime, 
        showDays, showCost, showDue;
        JButton btnDone, btnCancel;
        JPanel ReqPanel, btnPanel, TitlePanel, centerReqPanel;

        String[] Packages = {"With Instructor", "No Instructor"};
        getContentPane().setBackground(new Color(0x1a3052));

        //JLabels Instantiation
        ReqTitle = new JLabel("Surfboard Request", SwingConstants.CENTER);
        ReqTitle.setFont(new Font("Arial", Font.BOLD, 28));

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
        TitlePanel = new JPanel();
        TitlePanel.setLayout(new BorderLayout());
        TitlePanel.setBackground(new Color(0x1a3052));
        TitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        TitlePanel.add(ReqTitle, BorderLayout.NORTH);

        //Req Panel
        ReqPanel = new JPanel();
        ReqPanel.setLayout(new GridLayout());
        ReqPanel.setPreferredSize(new Dimension(300, 330));
        centerReqPanel = new JPanel(new GridBagLayout());
        centerReqPanel.setBackground(new Color(0x1a3052));
        //Add all Components in ReqPanel ex: ReqPanl.add(nameofcomponent)


        // btnPanel
        btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        btnPanel.setBackground(new Color(0x1a3052));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnPanel.add(btnCancel, BorderLayout.WEST);
        btnPanel.add(btnDone, BorderLayout.EAST);
     
        //Overall Window
        setLayout(new BorderLayout());
        add(TitlePanel, BorderLayout.NORTH);
        centerReqPanel.add(ReqPanel);
        add(centerReqPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        
        // Colors

        
        // Events
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Rental_List();
                dispose();
            }  
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Rental_List();
                dispose();
            }
        });

        setSize(417, 485);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Rental_Request();
    }
}