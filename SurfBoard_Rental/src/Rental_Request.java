import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Period;

/* 
* Navigation Guide
* 	> UI Component Declarations & Initializations
* 	> Panel Layouts & Containers
* 	> Finalized Interface & Styling
* 
* Change Log (06-01-2026, 01:30:00 PM)
* Additions
* 1) Created Surf Board Request Form
* 2) Integrated JComboBox for dropdown selection (surfboards, time slots, etc)
* 
* Modifications
* Fixed topLabel font override bug affecting form panel title sizing
* Linked btnSubmit window disposal to route back to Rental_List
* Added btnCancel next to btnSubmit using a horizontal buttonPanel wrapper container
*/

public class Rental_Request extends JFrame{
    private String currentAcc;
    private Rent_Data.RentalEntry editEntry = null;

    private JTextField			RQName, RQNum, boardInput, dateInput,
                            dayInput, hourInput, minuteInput, durationInput,
                            costOutput, dueOutput;
    private JComboBox<String> 	packBox, packPeriod;


	Rental_Request(String role) {
        this.currentAcc = role;
        



        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel				RequestTitle, topLabel,
                            RQNameDisplay, packageDisplay, 
                            RQNumDisplay, boardDisplay,
                            dateDisplay, dayDisplay, 
                            costDisplay, dueDisplay, 
                            hourDisplay, minuteDisplay, 
                            periodDisplay, durationDisplay;
        JPanel				formPanel, TopformPanel, 
                            BotformPanel, BotformPanel2,
                            topPanel, buttonPanel; // Container to align buttons side-by-side
        JButton				btnSubmit, btnCancel;
        
        
        // JLabels, JButtons, JComboBox, and JTextFields

        RequestTitle 	= new JLabel("Surfboard Rental Form", SwingConstants.CENTER);
        RequestTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        topLabel		= new JLabel("Rent a Board", SwingConstants.CENTER);
        RQNameDisplay	= new JLabel("Name");
        RQNumDisplay	= new JLabel("Phone Number:");
        boardDisplay	= new JLabel("Amount of SurfBoards");
        dateDisplay		= new JLabel("Date");
        hourDisplay		= new JLabel("Hour");
        minuteDisplay	= new JLabel("Minute");
        periodDisplay	= new JLabel("Period");
        dayDisplay		= new JLabel("Day(s)");
        durationDisplay = new JLabel("Duration (Hour(s))");
        costDisplay		= new JLabel("Cost");
        dueDisplay		= new JLabel("Due Rent");
        packageDisplay	= new JLabel("Packcage");

        RQName			= new JTextField("Name");
        RQNum			= new JTextField("XXXXXXXXXXX");
        boardInput		= new JTextField("00");
        dateInput		= new JTextField("00/00/0000");
        hourInput		= new JTextField("00");
        minuteInput		= new JTextField("00");
        dayInput		= new JTextField("00");
        durationInput   = new JTextField("00", 5);
        costOutput		= new JTextField("$$$$$$.$$", 8);
        dueOutput		= new JTextField("00/00/0000 XX:XX", 15);

        String [] pTime = {"AM", "PM"};
        String[] pack 	= {"No Instructor","With Instructor"};
        packBox = new JComboBox<>(pack);
        packPeriod = new JComboBox<>(pTime);

        btnSubmit 		= new JButton("Done");
        btnCancel 		= new JButton("Cancel");


        getContentPane().setBackground		(new Color(0xc4d2e0));

        costOutput.setEditable(false);
        dueOutput.setEditable(false);


        //---------------------------------------------------------------------
        
        
        //--------------------------- Panel Layouts ---------------------------

        //Top Panel / Header of Page
        topPanel 		= new JPanel();
        topPanel.setLayout			(new BorderLayout());
        topPanel.setBorder			(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        topPanel.add				(RequestTitle);
        topPanel.setOpaque			(false); 
        
        // Form Header Text
        topLabel.setFont			(new Font("Arial", Font.BOLD, 26));
        topLabel.setAlignmentX		(Component.CENTER_ALIGNMENT);

        // Input Field Labels Styling
        RQName.setFont       		(new Font("Arial", Font.PLAIN, 12));
        RQName.setAlignmentX 		(Component.CENTER_ALIGNMENT);
        
        
        // Horizontal Button Layout Panel
        buttonPanel 	= new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque			(false); // Keeps form panel background clean
        buttonPanel.setMaximumSize		(new Dimension(250, 35));
        
        // Size bounds matching form alignment architecture
        btnCancel.setPreferredSize		(new Dimension(90, 32));
        btnSubmit.setPreferredSize		(new Dimension(110, 32));
        
        // btnPanel
        buttonPanel.add		(btnCancel);
        buttonPanel.add		(btnSubmit);

        // Request Form Panel 
        formPanel = new JPanel();
        formPanel.setPreferredSize		(new Dimension(420, 395)); 
        formPanel.setBackground			(new Color(0x1a3052));
        formPanel.setBorder				(BorderFactory.createEmptyBorder(15, 20, 5, 20)); 
        formPanel.setLayout				(new BoxLayout(formPanel, BoxLayout.Y_AXIS));;

        // Top Request Form Panel
        TopformPanel = new JPanel();
        TopformPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        TopformPanel.setLayout			(new GridLayout(4,4, 10, 10));
        TopformPanel.add	(RQNameDisplay);	TopformPanel.add	(RQName);
        TopformPanel.add	(RQNumDisplay);		TopformPanel.add	(RQNum);
        TopformPanel.add	(boardDisplay);		TopformPanel.add	(boardInput);
        TopformPanel.add	(packageDisplay);	TopformPanel.add	(packBox);

        // Bottom Request Form Panel 
        BotformPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10)); 
        BotformPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        BotformPanel.setOpaque(true); 
        
        BotformPanel.add	(dateDisplay);		BotformPanel.add	(dateInput);
        BotformPanel.add	(hourDisplay);		BotformPanel.add	(hourInput);
        BotformPanel.add	(minuteDisplay);	BotformPanel.add	(minuteInput);
        BotformPanel.add	(periodDisplay);	BotformPanel.add	(packPeriod);
        BotformPanel.add    (durationDisplay);  BotformPanel.add    (durationInput);
        BotformPanel.add	(dayDisplay);		BotformPanel.add	(dayInput);

        BotformPanel2 = new JPanel();
        BotformPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        BotformPanel2.add	(costDisplay); 		BotformPanel2.add	(costOutput);
        BotformPanel2.add	(dueDisplay); 		BotformPanel2.add	(dueOutput);

        BotformPanel.add	(BotformPanel2);

        // Overall Request Form Panel
        formPanel.add		(TopformPanel);
        formPanel.add		(BotformPanel);
        formPanel.add		(buttonPanel);

        //---------------------------------------------------------------------
        
        
        //------------------- Finalized Interface & Styling -------------------
        
        
        // Events
        // Cancel Event Logic
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if("Admin".equalsIgnoreCase(role)){
                    new Admin_List("Admin"); 
                } else {
                    new Rental_List("User");
                }
        
            }
        });

        packBox.addActionListener(new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) {
                String pack_choice = packBox.getSelectedItem().toString();

                if (pack_choice.equals("With Instructor")){
                    durationInput.setText("02");
                    dayInput.setText("00");

                    
                    durationInput.setEditable(false);
                    dayInput.setEditable(false);
                } else {
                    durationInput.setText("00");
                    dayInput.setText("XX");

                    durationInput.setEditable(true);
                    dayInput.setEditable(true);
                }
            }
        });

        FocusListener liveDue = new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                String date = dateInput.getText();
                String hour = hourInput.getText();
                String minute = minuteInput.getText();
                String period = packPeriod.getSelectedItem().toString();

                dueOutput.setText(date + " " + hour + ":" + minute + " " + period);
                ;
            }
            
        };

        dateInput.addFocusListener(liveDue);
        hourInput.addFocusListener(liveDue);
        minuteInput.addFocusListener(liveDue);
        

        // Submit Event
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*  1 hour = 200
                    2hrs with Instructor = 1500
                    1 day = 600
                   */
                double total_cost = 0;
                int day = 600;
                int hour = 200;
                int packInstructor = 1500;

                int hour_choice = 0;
                int min_choice = 0;
                int day_choice = 0;
                int Surfboard_Num = 0;
                int duration_choice = 0;
                
                String surfboardName = "Surfboard";
                String name = RQName.getText();
                String Contact_Num = RQNum.getText();
                String period_choice = packPeriod.getSelectedItem().toString();
                String pack_choice = packBox.getSelectedItem().toString();
                String date_choice = dateInput.getText();

                try {
                    hour_choice     = Integer.parseInt(hourInput.getText().trim());
                    min_choice      = Integer.parseInt(minuteInput.getText().trim());
                    day_choice      = Integer.parseInt(dayInput.getText().trim());
                    Surfboard_Num   = Integer.parseInt(boardInput.getText().trim());
                    duration_choice = Integer.parseInt(durationInput.getText().trim());

                    if (pack_choice.equals("With Instructor")) {
                        total_cost = packInstructor * Surfboard_Num;
                    } else {
                        total_cost = (day_choice * day + duration_choice * hour) * Surfboard_Num;
                    }

                    costOutput.setEditable(true);
                    costOutput.setText(String.format("₱ %.2f", total_cost));
                    dueOutput.setText(String.format("%s %02d:%02d %s",
                        date_choice, hour_choice, min_choice, period_choice));

                    JOptionPane.showMessageDialog(null,
                    "Rental submitted!\nCost: " + String.format("₱ %.2f", total_cost));
                    costOutput.setEditable(false);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                        "Please fill in all fields with valid numbers.\n(" + ex.getMessage() + ")");
                        return; // ← Stop here, don't dispose
                    }   
                

                if (editEntry != null) {
                    editEntry.custName  = name;
                    editEntry.phone     = Contact_Num;
                    editEntry.boardNum  = Surfboard_Num;
                    editEntry.pack      = pack_choice;
                    editEntry.date      = date_choice;
                    editEntry.hour      = hour_choice;
                    editEntry.min       = min_choice;
                    editEntry.period    = period_choice;
                    editEntry.duration  = duration_choice;
                    editEntry.days      = day_choice;
                    editEntry.cost      = total_cost;
                    editEntry.due       = dueOutput.getText();
                    editEntry.status    = "In use";
                    } else {
                    Rent_Data.saveRental(name, Contact_Num, Surfboard_Num, pack_choice,
                                        date_choice, hour_choice, min_choice, period_choice,
                                        duration_choice, day_choice, total_cost, dueOutput.getText());
                }

                dispose();
                
                if ("Admin".equalsIgnoreCase(role)) {
                    new Admin_List(role);
                } else {
                    new Rental_List(role);
                }
            }
        });
        
        
        //Overall Window
        setLayout(new GridBagLayout());
        GridBagConstraints GBConstraints = new GridBagConstraints();
        
        // Center Align Panels
        GBConstraints.gridx		= 0;                        
        GBConstraints.fill		= GridBagConstraints.NONE;   
        
        // Top Header [Row 0]
        GBConstraints.gridy		= 0;                        
        GBConstraints.weighty	= 0.6;                    
        GBConstraints.anchor	= GridBagConstraints.SOUTH;
        add				(topPanel, GBConstraints);

        // Request Form Panel [Row 1]
        GBConstraints.gridy		= 1;                        
        GBConstraints.weighty	= 1;                    
        GBConstraints.anchor	= GridBagConstraints.NORTH;
        add				(formPanel, GBConstraints);
        
        //Color Components
        RQNameDisplay.setForeground		(new Color(0xad9a6f));
        RQNumDisplay.setForeground		(new Color(0xad9a6f));
        boardDisplay.setForeground		(new Color(0xad9a6f));
        packageDisplay.setForeground	(new Color(0xad9a6f));
        dateDisplay.setForeground		(new Color(0xad9a6f));
        hourDisplay.setForeground		(new Color(0xad9a6f));
        minuteDisplay.setForeground		(new Color(0xad9a6f));
        periodDisplay.setForeground		(new Color(0xad9a6f));
        dayDisplay.setForeground		(new Color(0xad9a6f));
        durationDisplay.setForeground	(new Color(0xad9a6f));
        costDisplay.setForeground		(new Color(0xad9a6f));
        dueDisplay.setForeground		(new Color(0xad9a6f));

        setSize							(517, 585);
        setDefaultCloseOperation		(DISPOSE_ON_CLOSE);
        setLocationRelativeTo			(null);
        setVisible						(true);
    }

    Rental_Request(String role, Rent_Data.RentalEntry entry) {
    this(role); 

    if (entry != null) {
        editEntry = entry;
        RQName.setText(entry.custName);
        RQNum.setText(entry.phone);
        boardInput.setText(String.valueOf(entry.boardNum));
        dateInput.setText(entry.date);
        hourInput.setText(String.format("%02d", entry.hour));
        minuteInput.setText(String.format("%02d", entry.min));
        durationInput.setText(String.valueOf(entry.duration));
        dayInput.setText(String.valueOf(entry.days));
        costOutput.setText(String.format("₱ %.2f", entry.cost));
        dueOutput.setText(entry.due);
        packBox.setSelectedItem(entry.pack);
        packPeriod.setSelectedItem(entry.period);
    }
}

    


    

}