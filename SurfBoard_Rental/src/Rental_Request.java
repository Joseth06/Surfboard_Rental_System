import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
	Rental_Request() {

        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel				RequestTitle, 
        					topLabel,
                        	boardTypeLabel,
                        	RQName;
        JPanel				formPanel,
        					topPanel,
                            buttonPanel; // Container to align buttons side-by-side
        JButton				btnSubmit, btnCancel;
        JTextField			renterName;
        JComboBox<String> 	boardType;
        
        // JLabels, JButtons, JComboBox, and JTextFields

        RequestTitle 	= new JLabel("Surfboard Rental Form", SwingConstants.CENTER);
        RequestTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        topLabel		= new JLabel("Rent a Board", SwingConstants.CENTER);
        RQName			= new JLabel("Renter Name:", SwingConstants.CENTER);
        renterName		= new JTextField(15);
        
        boardTypeLabel	= new JLabel("Select Board Type:", SwingConstants.CENTER);
        // Dropdown choices
        String[] types	= { "SurfBoard 1", "SurfBoard 2", "SurfBoard 3", "SurfBoard 4", "SurfBoard 5" };
        boardType		= new JComboBox<>(types);
        
        btnSubmit		= new JButton("Submit Rental");
        btnCancel 		= new JButton("Cancel");

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
        
        boardTypeLabel.setFont      (new Font("Arial", Font.PLAIN, 12));
        boardTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Renter Name Field
        renterName.setMaximumSize	(new Dimension(200, 30));
        renterName.setAlignmentX	(Component.CENTER_ALIGNMENT);

        // Dropdown Combo Box Field
        boardType.setMaximumSize	(new Dimension(200, 30));
        boardType.setAlignmentX		(Component.CENTER_ALIGNMENT);

        // Horizontal Button Layout Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
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
        formPanel.setPreferredSize		(new Dimension(265, 320));
        formPanel.setBackground			(new Color(0x1a3052));

        formPanel.setLayout		(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add			(Box.createVerticalStrut(15));
        formPanel.add			(topLabel);
        formPanel.add			(Box.createVerticalStrut(25));
        formPanel.add			(RQName);
        formPanel.add			(Box.createVerticalStrut(5));
        formPanel.add			(renterName);
        formPanel.add			(Box.createVerticalStrut(15));
        formPanel.add			(boardTypeLabel);
        formPanel.add			(Box.createVerticalStrut(5));
        formPanel.add			(boardType);
        formPanel.add			(Box.createVerticalStrut(25));
        formPanel.add			(buttonPanel); // Adds the horizontal button group card row
        formPanel.add			(Box.createVerticalStrut(20));


        //---------------------------------------------------------------------
        
        
        //------------------- Finalized Interface & Styling -------------------
        
        
        // Events
        btnSubmit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String inputName = renterName.getText().trim();
        String selectedBoard = (String) boardType.getSelectedItem();

        if (!inputName.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Successfully rented a " + selectedBoard + " for " + inputName + "!");
            dispose();
            new Rental_List(); 
        } else {
            JOptionPane.showMessageDialog(formPanel, "Please enter a renter name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });
        
        // Cancel Event Logic
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Rental_List(); 
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
        RequestTitle.setForeground		(new Color(0x1a3052));
        topLabel.setForeground			(new Color(0xf4efe9));
        RQName.setForeground			(new Color(0xf4efe9));
        boardTypeLabel.setForeground	(new Color(0xf4efe9));
        
        btnCancel.setBackground			(new Color(0xd9534f)); 
        btnCancel.setForeground			(Color.WHITE);
        btnSubmit.setBackground			(new Color(0xad9a6f));
        formPanel.setBackground			(new Color(0x1a3052));

        setSize							(417, 485);
        setDefaultCloseOperation		(DISPOSE_ON_CLOSE);
        setLocationRelativeTo			(null);
        setVisible						(true);
    }

    public static void main(String[] args) {
        new Rental_Request();
    }
}
