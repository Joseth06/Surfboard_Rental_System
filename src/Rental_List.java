import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private JPanel scrollPanel;  
    private int gapHeight;


    

    Rental_List(String acc){
        this.currentAcc = acc;

        // Loades Backgroud Image
        setContentPane(new BackgroundPanel("list.jpg"));
    
    
        
        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel 		SurfListTitle;
        JPanel 		top;
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
        top         = new JPanel();
        top.setLayout   (new BorderLayout());
        top.setBorder   (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.setBackground(new Color(0x1D3557));
        surfLogout.setBackground(new Color(0x2E86C1));
        surfLogout.setForeground(new Color(0xFFFFFF));
        rentalRQ.setBackground(new Color(0x2E86C1));
        rentalRQ.setForeground(new Color(0xFFFFFF));
        
        

        top.add         (surfLogout, BorderLayout.WEST);
        top.add         (SurfListTitle, BorderLayout.CENTER);
        top.add         (rentalRQ, BorderLayout.EAST);

        // Dimension constants
        int itemWidth = 330;
        int itemHeight = 90;
        gapHeight = (int) (itemHeight * 0.20);
        Dimension itemSize = new Dimension(itemWidth, itemHeight);

        //Status Panel 
        panels p = new panels();

        
        // Container for Panels
        scrollPanel = new JPanel();
        scrollPanel.setBorder       (BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scrollPanel.setBackground   (new Color(0xD6E4F0));   // ← scroll area background
        
        // Box Layout cuz idk how I would've figured that out in Gridlayout
        scrollPanel.setLayout   (new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

        // Loads and displays all existing rentals in customers perspective
        for (Rent_Data.RentalEntry entry : Rent_Data.rentals) {
            scrollPanel.add(p.addRentCus(entry.boardName, entry.due, entry.cost, this, entry));
            scrollPanel.add(Box.createVerticalStrut(gapHeight));
        }
        

        // Scroll Panel
        scroll = new JScrollPane(scrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(0xD6E4F0));
        scrollPanel.setOpaque(false);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false); // ← viewport background


        
        
        // Initialize scroll container with your panel nestled inside
      
        

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
        add         (scroll, BorderLayout.CENTER); // Adds scrollable panel container to the frame



        //Component Colors
        SurfListTitle.setForeground		(new Color(0xad9a6f));
        

        // Window Parameters
        setSize						(417,485);
        setVisible					(true);
        setDefaultCloseOperation	(EXIT_ON_CLOSE);
        setLocationRelativeTo		(null);
        setResizable                (false);

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