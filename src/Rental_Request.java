import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.time.LocalDate;


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
* Added btnCancel next to btnSubmit using a horizontal buttonPanel wrapper container sd
*/

public class Rental_Request extends JFrame{
    private String currentAcc;
    private Rent_Data.RentalEntry editEntry = null;

    private JTextField			RQName, RQNum, boardInput, dateInput,
                            dayInput, hourInput, minuteInput, durationInput;
    private JComboBox<String> 	packBox, packPeriod;
    private JLabel costOutput, dueOutput;
    private JButton btnSubmit;
    private boolean isLoading = false;

    
	Rental_Request(String role) {
        this.currentAcc = role;
        BackgroundPanel bgPanel = new BackgroundPanel("req_form.jpg");
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);
        



        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel				RequestTitle, topLabel,
                            RQNameDisplay, packageDisplay, 
                            RQNumDisplay, boardDisplay,
                            dateDisplay, dayDisplay, 
                            costDisplay, dueDisplay, 
                            colonLabel, timeDisplay, 
                            durationDisplay;
        JPanel				formPanel, TopformPanel, 
                            BotformPanel, BotformPanel2,
                            topPanel, buttonPanel, timePanel; // Container to align buttons side-by-side
        JButton				btnCancel;
        
        
        // JLabels, JButtons, JComboBox, and JTextFields

        RequestTitle 	= new JLabel("Surfboard Rental Form", SwingConstants.CENTER);
        RequestTitle.setFont(new Font("Arial", Font.BOLD, 20));
        RequestTitle.setForeground(new Color(0x1a3052));
        
        topLabel		= new JLabel("Rent a Board", SwingConstants.CENTER);
        RQNameDisplay	= new JLabel("Name");
        RQNumDisplay	= new JLabel("Phone Number:");
        boardDisplay	= new JLabel("Amount of SurfBoards");
        dateDisplay		= new JLabel("Date");
        timeDisplay     = new JLabel("Start Time");
        colonLabel      = new JLabel(":");
        dayDisplay		= new JLabel("Day(s)");
        durationDisplay = new JLabel("Duration (Hour(s))");
        costDisplay		= new JLabel("Cost");
        dueDisplay		= new JLabel("Due Rent");
        packageDisplay	= new JLabel("Package");
        costOutput		= new JLabel("₱ 0.00");
        dueOutput		= new JLabel("00/00/0000 XX:XX");

        RQName			= new JTextField("Name");
        RQNum			= new JTextField("###########");
        boardInput		= new JTextField("00");
        dateInput		= new JTextField("00/00/0000");
        hourInput		= new JTextField("00");
        minuteInput		= new JTextField("00");
        dayInput		= new JTextField("00");
        durationInput   = new JTextField("00", 5);
        
        

        String [] pTime = {"AM", "PM"};
        String[] pack 	= {"No Instructor","With Instructor"};
        packBox = new JComboBox<>(pack);
        packPeriod = new JComboBox<>(pTime);

        btnSubmit 		= new JButton("Done");
        btnCancel 		= new JButton("Cancel");



        //---------------------------------------------------------------------
        
        
        //--------------------------- Panel Layouts ---------------------------

        //Top Panel / Header of Page
        topPanel         = new JPanel();
        topPanel.setLayout            (new BorderLayout());
        topPanel.setBorder            (BorderFactory.createEmptyBorder(10, 0, 10, 10));
        topPanel.add                (RequestTitle);
        topPanel.setBackground(new Color(0x1a, 0x30, 0x52, 180)); // semi-transparent blue box
        topPanel.setOpaque            (false); 
        
        // Form Header Text
        topLabel.setFont            (new Font("Arial", Font.BOLD, 26));
        topLabel.setAlignmentX        (Component.CENTER_ALIGNMENT);

        // Input Field Labels Styling
        RQName.setFont               (new Font("Arial", Font.PLAIN, 12));
        RQName.setAlignmentX         (Component.CENTER_ALIGNMENT);
        
        
        // Horizontal Button Layout Panel
        buttonPanel     = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque            (false); // Keeps form panel background clean
        buttonPanel.setMaximumSize        (new Dimension(250, 35));
        
        // Size bounds matching form alignment architecture
        btnCancel.setPreferredSize        (new Dimension(90, 32));
        btnSubmit.setPreferredSize        (new Dimension(110, 32));
        
        // btnPanel
        buttonPanel.add        (btnCancel);
        buttonPanel.add        (btnSubmit);

        // Request Form Panel 
        formPanel = new JPanel();
        formPanel.setPreferredSize        (new Dimension(420, 395)); 
        formPanel.setBackground            (new Color(0x1a3052));
        formPanel.setBorder                (BorderFactory.createEmptyBorder(15, 20, 5, 20)); 
        formPanel.setLayout                (new BoxLayout(formPanel, BoxLayout.Y_AXIS));;
        formPanel.setOpaque(true);
        

        
        // Top Request Form Panel
        TopformPanel = new JPanel();
        TopformPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        TopformPanel.setLayout            (new GridLayout(4,4, 10, 10));
        TopformPanel.add    (RQNameDisplay);    TopformPanel.add    (RQName);
        TopformPanel.add    (RQNumDisplay);        TopformPanel.add    (RQNum);
        TopformPanel.add    (boardDisplay);        TopformPanel.add    (boardInput);
        TopformPanel.add    (packageDisplay);    TopformPanel.add    (packBox);
        
        //Time Panel

        timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 0));
        timePanel.setOpaque(false);
        timePanel.add(hourInput);
        timePanel.add(colonLabel);
        timePanel.add(minuteInput);
        timePanel.add(packPeriod);

        // Bottom Request Form Panel 
        BotformPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10)); 
        BotformPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        BotformPanel.setOpaque(true); 
        
        BotformPanel.add    (dateDisplay);        BotformPanel.add    (dateInput);
        BotformPanel.add    (timeDisplay);      BotformPanel.add    (timePanel);
        BotformPanel.add    (durationDisplay);  BotformPanel.add    (durationInput);
        BotformPanel.add    (dayDisplay);        BotformPanel.add    (dayInput);

        BotformPanel2 = new JPanel();
        BotformPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        BotformPanel2.add    (costDisplay);         BotformPanel2.add    (costOutput);
        BotformPanel2.add    (dueDisplay);         BotformPanel2.add    (dueOutput);

        BotformPanel.add    (BotformPanel2);

        // Overall Request Form Panel
        formPanel.add        (TopformPanel);
        formPanel.add        (BotformPanel);
        formPanel.add        (buttonPanel);

        

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

        // With Instructor Package 
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
                    dayInput.setText("00");

                    durationInput.setEditable(true);
                    dayInput.setEditable(true);
                }
            }
        });

        //Updates the costOutput and dueOutput during the ReqForm
        Runnable updateLabels = () -> {
            if (isLoading) return;
            try {
                String pack_choice  = packBox.getSelectedItem().toString();
                String period       = packPeriod.getSelectedItem().toString();
                String date         = dateInput.getText();
                String hour         = hourInput.getText();
                String minute       = minuteInput.getText();
                int Surfboard_Num   = Integer.parseInt(boardInput.getText().trim());
                int duration_choice = Integer.parseInt(durationInput.getText().trim());
                int day_choice      = Integer.parseInt(dayInput.getText().trim());
                       
                // Update due label
                dueOutput.setText(date + " " + hour + ":" + minute + " " + period);

                // Update cost label
                double total_cost;
                    if (pack_choice.equals("With Instructor")) {
                        total_cost = 1500 * Surfboard_Num;
                    } else {
                        total_cost = (day_choice * 600 + duration_choice * 200) * Surfboard_Num;
                    }
                    costOutput.setText(String.format("₱ %.2f", total_cost));

                } catch (NumberFormatException ex) {
                    costOutput.setText("₱ 0.00");
                }
            };

                
                dateInput.addActionListener(e -> updateLabels.run());
                hourInput.addActionListener(e -> updateLabels.run());
                minuteInput.addActionListener(e -> updateLabels.run());
                boardInput.addActionListener(e -> updateLabels.run());
                durationInput.addActionListener(e -> updateLabels.run());
                dayInput.addActionListener(e -> updateLabels.run());
                packPeriod.addActionListener(e -> updateLabels.run());
      

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


                    

                    // Fields/Input checking
                    // Validation of Duplicates
                    if (editEntry == null && Rent_Data.isDuplicate(name)) {
                        JOptionPane.showMessageDialog(null,
                            "A rental for " + name + " already exists!",
                            "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    //Validation of Name (if Empty)
                    if (name.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                            "Enter a name.",
                            "Name", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    //Validates Phone number
                    if (Contact_Num.equals("###########") || Contact_Num.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Input a valid phone number.");
                        return;
                    }

                    // Validation of Quantity of Surfboards
                    if (Surfboard_Num == 0 || Surfboard_Num < 0) {
                        JOptionPane.showMessageDialog(null, "Input a valid number of surfboards.");
                        return;
                    }

                    // Validate of Quantity of Surfboards in Inventory
                    int available = Rent_Data.getAvailableBoards();
                    if (Surfboard_Num > available) {
                        JOptionPane.showMessageDialog(null, "Only " + available + " surfboards available!");
                        return; 
                    }

                    //Checking of Dates
                    if (!Rent_Data.isValidDate(date_choice)){
                        JOptionPane.showMessageDialog(null, "Invalid date. Use MM/DD/YYYY", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Checks if pastDate
                    if (Rent_Data.isPastDate(date_choice)){
                        JOptionPane.showMessageDialog(null, "Input a valid date. Cannot book a rental past", "Invalid date", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Checks if past hour and minute
                    String fullDateTime = date_choice + " " + String.format("%02d:%02d %s", hour_choice, min_choice, period_choice);
                    if (Rent_Data.getStatus(fullDateTime).startsWith("Overdue")) {
                        JOptionPane.showMessageDialog(null, "Cannot book a time that has already passed!", "Invalid Time", JOptionPane.WARNING_MESSAGE);
                        return;
                    }                    
                    
                    // 24th duration Bug
                    if (duration_choice > 24) {
                        JOptionPane.showMessageDialog(null, "Duration cannot exceed 24 hours.");
                        return;
                    }

                    if ((hour_choice == 0 && duration_choice == 0) || (hour_choice < 0 && duration_choice < 0)){
                        JOptionPane.showMessageDialog(null, "Input a valid hour.");
                        return;
                    }

                    // Calculation of Cost if "With Instructor" in package
                    if (pack_choice.equals("With Instructor")) {
                        total_cost = packInstructor * Surfboard_Num;
                    } else {
                        total_cost = (day_choice * day + duration_choice * hour) * Surfboard_Num;
                    }

                    
                    
                    // Reveals the total cost
                    costOutput.setText(String.format("₱ %.2f", total_cost));

                    // Starts the time calculation
                    LocalDate startDate = LocalDate.parse(date_choice, Rent_Data.formatter);
                    LocalDate dueDate   = startDate.plusDays(day_choice);
                    String dueString    = String.format("%s %02d:%02d %s", dueDate.format(Rent_Data.formatter),
                    hour_choice, min_choice, period_choice);
                    dueOutput.setText(dueString);


                    JOptionPane.showMessageDialog(null,
                    "Rental submitted!\nCost: " + String.format("₱ %.2f", total_cost));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                        "Please fill in all fields with valid inputs.\n(" + ex.getMessage() + ")");
                        return; // ← Stop here, don't dispose
                    }   
                
                if (editEntry == null && Rent_Data.isDuplicate(name)) {
                    JOptionPane.showMessageDialog(null,
                    "A rental for " + name + " already exists!", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return; 
                }

                // Checks if theres is an existing data  
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

                // Saves the Data
                Rent_Data.saveToFile();
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
        bgPanel.add(topPanel, GBConstraints);

        // Request Form Panel [Row 1]
        GBConstraints.gridy		= 1;                        
        GBConstraints.weighty	= 1;                    
        GBConstraints.anchor	= GridBagConstraints.NORTH;
        bgPanel.add(formPanel, GBConstraints);
        
        //Color Components
        RQNameDisplay.setForeground		(new Color(0x1a3052));
        RQNumDisplay.setForeground		(new Color(0x1a3052));
        boardDisplay.setForeground		(new Color(0x1a3052));
        packageDisplay.setForeground	(new Color(0x1a3052));
        dateDisplay.setForeground		(new Color(0x1a3052));
        dayDisplay.setForeground		(new Color(0x1a3052));
        timeDisplay.setForeground       (new Color(0x1a3052));
        durationDisplay.setForeground	(new Color(0x1a3052));
        costDisplay.setForeground		(new Color(0x1a3052));
        dueDisplay.setForeground		(new Color(0x1a3052));
        costOutput.setForeground        (new Color(0x1a3052));
        dueOutput.setForeground         (new Color(0x1a3052));

        setSize							(517, 585);
        setDefaultCloseOperation		(DISPOSE_ON_CLOSE);
        setLocationRelativeTo			(null);
        setVisible						(true);
        setResizable                    (false);
    }


    //Data Saving
    Rental_Request(String role, Rent_Data.RentalEntry entry) {
    this(role); 

    // Loads the reqform if have information already (Admin can edit and User can only view)
    if (entry != null) {
        editEntry = entry;
        isLoading = true;

        RQName.setText(entry.custName);
        RQNum.setText(entry.phone);
        boardInput.setText(String.valueOf(entry.boardNum));
        dateInput.setText(entry.date);
        hourInput.setText(String.format("%02d", entry.hour));
        minuteInput.setText(String.format("%02d", entry.min));
        durationInput.setText(String.valueOf(entry.duration));
        dayInput.setText(String.valueOf(entry.days));
        packBox.setSelectedItem(entry.pack);
        packPeriod.setSelectedItem(entry.period);

        isLoading = false;
        costOutput.setText(String.format("₱ %.2f", entry.cost));
        dueOutput.setText(entry.due);

        if ("User".equalsIgnoreCase(role)) {
            RQName.setEditable(false);
            RQNum.setEditable(false);
            boardInput.setEditable(false);
            dateInput.setEditable(false);
            hourInput.setEditable(false);
            minuteInput.setEditable(false);
            dayInput.setEditable(false);
            durationInput.setEditable(false);
            packBox.setEnabled(false);
            packPeriod.setEnabled(false);
            btnSubmit.setVisible(false); // Hides Done Button
            }
        }

    }  

    //Background Image
    class BackgroundPanel extends JPanel {
    private Image image;

    BackgroundPanel(String imagePath) {
        image = new ImageIcon(getClass().getResource("/" + imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
} 

    


    

}