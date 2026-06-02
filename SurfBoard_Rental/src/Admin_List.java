import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 /* 
 * Navigation Guide
 * 	> UI Component Declarations & Initializations
 * 	> Panel Layouts & Containers
 * 	> Finalized Interface & Styling
 * 
 * Change Log (05-31-2026, 04:02:39 PM)
 * Additions
 * 1) Added tab spacing for readability
 * 2) Added a scroll panel container to hold new panels for each equipment (Line 87-92)
 * 3) Added gap panel feature using boxlayout, intended for spacing between equipment panels
 * 
 * 
 * Modifications
 * SurfList		--> SurfListTitle
 * nameOfboard 	--> EQPName
 * stats 		--> EQPState
 * status		--> EQPStatus
 * statusCenter --> statusCTR
 * add 			--> rentalRQ
*/

public class Admin_List extends JFrame {
    Admin_List(){
        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel 		SurfListTitle, 
        			EQPName1, EQPName2, 
        			EQPState1, EQPState2;
        JPanel 		EQPStatus1, EQPStatus2, 
					top, 
					statusCTR1, statusCTR2,
					gapPanel, scrollPanel;
        JButton 	rentalRQ, surfLogout;
        JScrollPane scroll;
        
        //JLabels, JButtons, and JScrollPane
        SurfListTitle 	= new JLabel("Surf Boards List", SwingConstants.CENTER);
        SurfListTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        EQPName1 	= new JLabel("Surf Board 1", SwingConstants.LEFT);
        EQPState1 	= new JLabel("Available", SwingConstants.RIGHT);
        EQPName2 	= new JLabel("Surf Board 2", SwingConstants.LEFT);
        EQPState2 	= new JLabel("Under Maintenance", SwingConstants.RIGHT);

        //JButton to Rental Request Form
        surfLogout  = new JButton("Surf out");
        rentalRQ 	= new JButton("+");

        //Scroll Feature, deal with later on.
        scroll 		= new JScrollPane();


        //---------------------------------------------------------------------
        
        
        //-------------------- Panel Layouts & Containers ---------------------
        
        //Top Panel / Header of Page
        top 		= new JPanel();
        top.setLayout	(new BorderLayout());
        top.setBorder	(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add         (surfLogout, BorderLayout.WEST);
        top.add			(SurfListTitle, SwingConstants.CENTER);
        top.add			(rentalRQ, BorderLayout.EAST);

        // Dimension constants
        int itemWidth = 330;
        int itemHeight = 90;
        int gapHeight = (int) (itemHeight * 0.20); 
        Dimension itemSize = new Dimension(itemWidth, itemHeight);

        //Status Panel for Equipment 1
        EQPStatus1 	= new JPanel	();
        EQPStatus1.setLayout		(new GridLayout());
        EQPStatus1.setPreferredSize	(itemSize); 
        EQPStatus1.setMaximumSize	(itemSize); // Locks size for BoxLayout
        EQPStatus1.setBorder		(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        statusCTR1 	= new JPanel(new GridBagLayout());
        EQPStatus1.add	(EQPName1);
        EQPStatus1.add	(EQPState1);
        
        //Status Panel for Equipment 2
        EQPStatus2 	= new JPanel();
        EQPStatus2.setLayout		(new GridLayout());
        EQPStatus2.setPreferredSize	(itemSize); // Corrected from 10 to 90
        EQPStatus2.setMaximumSize	(itemSize); // Locks size for BoxLayout
        EQPStatus2.setBorder		(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statusCTR2 	= new JPanel(new GridBagLayout());
        EQPStatus2.add				(EQPName2);
        EQPStatus2.add				(EQPState2);

        
        //Status Panel for Spacing between Equipments
        gapPanel 	= new JPanel();
        gapPanel.setLayout			(new GridLayout());
        gapPanel.setPreferredSize	(new Dimension(330,90));
        gapPanel.setBorder			(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Container for Panels
        scrollPanel = new JPanel();
        scrollPanel.setBorder	(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Box Layout cuz idk how I would've figured that out in Gridlayout
        scrollPanel.setLayout	(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS)); 
        scrollPanel.add			(EQPStatus1);
        scrollPanel.add			(Box.createVerticalStrut(gapHeight)); // Gap
        scrollPanel.add			(EQPStatus2);
        
        // Initialize scroll container with your panel nestled inside
        scroll = new JScrollPane(scrollPanel);

        

        //---------------------------------------------------------------------
        

        //------------------- Finalized Interface & Styling -------------------
        
        // Events
        // Rental Request Button (+)
        rentalRQ.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                new Rental_Request();
                dispose();
            }  
        }
        );

        //Overall Window
        setLayout	(new BorderLayout()); 
        add			(top, BorderLayout.NORTH); //Adds header to the frame
        add			(scrollPanel, BorderLayout.CENTER); // Adds scrollable panel container to the frame


        //Component Colors
        SurfListTitle.setForeground		(new Color(0xad9a6f));

  	  	EQPName1.setForeground		(new Color(0xad9a6f));
        EQPStatus1.setBackground	(new Color(0x1a3052));
        EQPState1.setForeground		(new Color(0xad9a6f));
        
    	EQPName2.setForeground		(new Color(0xad9a6f));
        EQPStatus2.setBackground	(new Color(0x1a3052));
        EQPState2.setForeground		(new Color(0xad9a6f));
          
        // Window Parameters
        setSize						(417,485);
        setVisible					(true);
        setDefaultCloseOperation	(EXIT_ON_CLOSE);
        setLocationRelativeTo		(null);


        //---------------------------------------------------------------------
        

        //------------------------- Start of Program --------------------------
        
    }

    public static void main(String arg[]){
        new Admin_List();
    }
}