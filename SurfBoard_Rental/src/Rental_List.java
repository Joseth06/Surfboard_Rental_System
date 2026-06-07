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

public class Rental_List extends JFrame {
    private String currentAcc;
    private JPanel scrollPanel;   // ← promoted from local
    private int gapHeight;
    private int rentCount = 0;


    public void addRentCus(String name, String due, double cost) {
        rentCount++;   
        panels p = new panels();
        scrollPanel.add(Box.createVerticalStrut(gapHeight));
        scrollPanel.add(p.addRentCus("Surfboard " + rentCount, due, cost));
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }

    Rental_List(String acc){
        this.currentAcc = acc;
    
    
        
        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel 		SurfListTitle;
        JPanel 		top, statusCTR1, 
                    statusCTR2, gapPanel;
        JButton 	rentalRQ, surfLogout;
        JScrollPane scroll;

        
        
        //JLabels, JButtons, and JScrollPane
        SurfListTitle 	= new JLabel("Surf Boards List", SwingConstants.CENTER);
        SurfListTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        //JButton to Rental Request Form
        rentalRQ 	= new JButton("+");
        surfLogout = new JButton("Surf out");

        //Scroll Feature, deal with later on.
        scroll 		= new JScrollPane();


        //---------------------------------------------------------------------
        
        
        //-------------------- Panel Layouts & Containers ---------------------
        
        //Top Panel / Header of Page
        top 		= new JPanel();
        top.setLayout	(new BorderLayout());
        top.setBorder	(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add         (surfLogout, BorderLayout.WEST);
        top.add         (SurfListTitle, BorderLayout.CENTER);
        top.add			(rentalRQ, BorderLayout.EAST);

        // Dimension constants
        int itemWidth = 330;
        int itemHeight = 90;
        gapHeight = (int) (itemHeight * 0.20); 
        Dimension itemSize = new Dimension(itemWidth, itemHeight);

        //Status Panel 
        panels p = new panels();
        
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
        panels p1 = new panels();
        for (Rent_Data.RentalEntry entry : Rent_Data.rentals) {
            scrollPanel.add(p.addRentCus(entry.boardName, entry.due, entry.cost));
            scrollPanel.add(Box.createVerticalStrut(gapHeight));
        }
        


        
        
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
                new Rental_Request(currentAcc);
                dispose();
            }  
        });

        surfLogout.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                new SurfBoard();
                dispose();
            }
        });
        //Overall Window
        setLayout	(new BorderLayout()); 
        add			(top, BorderLayout.NORTH); //Adds header to the frame
        add			(scrollPanel, BorderLayout.CENTER); // Adds scrollable panel container to the frame


        //Component Colors
        SurfListTitle.setForeground		(new Color(0xad9a6f));
        

        // Window Parameters
        setSize						(417,485);
        setVisible					(true);
        setDefaultCloseOperation	(EXIT_ON_CLOSE);
        setLocationRelativeTo		(null);

    }
    
}