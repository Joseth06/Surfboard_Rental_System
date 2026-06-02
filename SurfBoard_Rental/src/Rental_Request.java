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

public class Rental_Request extends JFrame {
    private String currentAcc;
	Rental_Request(String role) {
        this.currentAcc = role;
        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel				RequestTitle, topLabel,
                            RQNameDisplay, packageDisplay, 
                            RQNumDisplay, boardDisplay,
                            dateDisplay, timeDisplay, 
                            dayDisplay, costDisplay, 
                            dueDisplay, hourDisplay,
                            minuteDisplay, periodDisplay;
        JPanel				formPanel, TopformPanel, 
                            BotformPanel, BotformPanel2,
                            topPanel, buttonPanel; // Container to align buttons side-by-side
        JButton				btnSubmit, btnCancel;
        JTextField			RQName, RQNum, boardInput, dateInput,
                            dayInput, hourInput, minuteInput, periodInput, 
                            costOutput, dueOutput;
        JComboBox<String> 	boardType, packageType;
        
        // JLabels, JButtons, JComboBox, and JTextFields

        RequestTitle 	= new JLabel("Surfboard Rental Form", SwingConstants.CENTER);
        RequestTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        topLabel = new JLabel("Rent a Board", SwingConstants.CENTER);
        RQNameDisplay = new JLabel("Name");
        RQNumDisplay = new JLabel("Phone Number:");
        boardDisplay = new JLabel("Amount of SurfBoards");
        dateDisplay = new JLabel("Date");
        hourDisplay = new JLabel("Hour");
        minuteDisplay = new JLabel("Minute");
        periodDisplay = new JLabel("Period");
        dayDisplay = new JLabel("Day(s)");
        costDisplay = new JLabel("Cost");
        dueDisplay = new JLabel("Due Rent");
        packageDisplay = new JLabel("Packcage");

        RQName = new JTextField("Name");
        RQNum = new JTextField("XXXXXXXXXXX");
        boardInput = new JTextField("XX");
        dateInput = new JTextField("00/00/0000");
        hourInput = new JTextField("XX");
        minuteInput = new JTextField("XX");
        dayInput = new JTextField("XX");
        costOutput = new JTextField("$$$$$$.$$", 15);
        dueOutput = new JTextField("00/00/0000 XX:XX");

        String [] pTime = {"AM", "PM"};
        String[] pack = {"No Instructor","With Instructor"};
        JComboBox<String> packBox = new JComboBox<>(pack);
        JComboBox<String> packPeriod = new JComboBox<>(pTime);

        btnSubmit = new JButton("Done");
        btnCancel = new JButton("Cancel");


        getContentPane().setBackground		(new Color(0xc4d2e0));


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
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        buttonPanel.setOpaque(false); // Keeps form panel background clean
        buttonPanel.setMaximumSize(new Dimension(250, 35));
        
        // Size bounds matching form alignment architecture
        btnCancel.setPreferredSize(new Dimension(80, 35));
        btnSubmit.setPreferredSize(new Dimension(130, 35));
        
        // btnPanel
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSubmit);

        

        // Request Form Panel
        formPanel 	= new JPanel();
        formPanel.setPreferredSize		(new Dimension(400, 450));
        formPanel.setBackground			(new Color(0x1a3052));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setLayout(new GridLayout(3, 1, 10, 10));

       

        
        // Top Request Form Panel
        TopformPanel = new JPanel();
        TopformPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        TopformPanel.setLayout		(new GridLayout(4,4, 10, 10));
        TopformPanel.add(RQNameDisplay); TopformPanel.add(RQName);
        TopformPanel.add(RQNumDisplay); TopformPanel.add(RQNum);
        TopformPanel.add(boardDisplay); TopformPanel.add(boardInput);
        TopformPanel.add(packageDisplay); TopformPanel.add(packBox);

        

        
        // Bottom Request Form Panel
        BotformPanel = new JPanel();
        BotformPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        BotformPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        BotformPanel.setMinimumSize(getMinimumSize());
        BotformPanel.setMaximumSize(getMaximumSize());

        BotformPanel.add(dateDisplay); BotformPanel.add(dateInput);
        BotformPanel.add(hourDisplay); BotformPanel.add(hourInput);
        BotformPanel.add(minuteDisplay); BotformPanel.add(minuteInput);
        BotformPanel.add(dayDisplay); BotformPanel.add(dayInput);
        BotformPanel.add(periodDisplay); BotformPanel.add(packPeriod);

        BotformPanel2 = new JPanel();
        BotformPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        BotformPanel2.add(costDisplay); BotformPanel2.add(costOutput);
        BotformPanel2.add(dueDisplay); BotformPanel2.add(dueOutput);

        BotformPanel.add(BotformPanel2);

        // Overall Request Form Panel
        formPanel.add(TopformPanel);
        formPanel.add(BotformPanel);
        formPanel.add(buttonPanel);
        


        //Color Components
        RQNameDisplay.setForeground(new Color(0xad9a6f));
        RQNumDisplay.setForeground(new Color(0xad9a6f));
        boardDisplay.setForeground(new Color(0xad9a6f));
        packageDisplay.setForeground(new Color(0xad9a6f));
        dateDisplay.setForeground(new Color(0xad9a6f));
        hourDisplay.setForeground(new Color(0xad9a6f));
        minuteDisplay.setForeground(new Color(0xad9a6f));
        periodDisplay.setForeground(new Color(0xad9a6f));
        dayDisplay.setForeground(new Color(0xad9a6f));
        costDisplay.setForeground(new Color(0xad9a6f));
        dueDisplay.setForeground(new Color(0xad9a6f));
        

        //---------------------------------------------------------------------
        
        
        //------------------- Finalized Interface & Styling -------------------
        
        
        // Events
        btnSubmit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        
        }
        });
        
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

        // Submit Event
        btnSubmit.addActionListener(new ActionListener() {
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
        //Component Colors
        

        setSize							(517, 585);
        setDefaultCloseOperation		(DISPOSE_ON_CLOSE);
        setLocationRelativeTo			(null);
        setVisible						(true);
    }

}
