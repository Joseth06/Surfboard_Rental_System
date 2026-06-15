import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* 
* Navigation Guide
* 	> UI Component Declarations & Initializations
* 	> Panel Layouts & Containers
* 	> Finalized Interface & Styling
* 
* Change Log (06-01-2026, 10:32:26 AM)
* Additions
* 1) Added tab spacing for readability
* 2) Added background text "password" visibility 
* 3) Utilized Grid Bag Constraints for mapping out panels.
* 
* Modifications
* MainPanel 	--> loginPanel
* 
* 
* Change Log (06-01-2026, 01:58:26 PM)
* 1) Transfered code from Login_Page.java
*/

public class SurfBoard extends JFrame {
    SurfBoard() {

        //------------ UI Component Declarations & Initializations ------------
    	
        JLabel			LoginTitle, 
        				login;
        JPanel			loginPanel,
        				top;
        JButton			btnSignIn;
        JTextField		name;
        JPasswordField	pass;
        
        //JLabels, JButtons, JPasswordFields, and JTextFields

        LoginTitle 	= new JLabel("Welcome!", SwingConstants.CENTER);
        LoginTitle.setFont(new Font("Arial", Font.BOLD, 30));
        
        login		= new JLabel("Login", SwingConstants.CENTER);
        name 		= new JTextField(" username", 15);
        pass 		= new JPasswordField("password", 15);
        btnSignIn 	= new JButton("Sign In");

        getContentPane().setBackground		(new Color(0xc4d2e0));


        //---------------------------------------------------------------------
        
        
        //--------------------------- Panel Layouts ---------------------------

        //Top Panel / Header of Page
        top         = new JPanel();
        top.setLayout   (new BorderLayout());
        top.setBorder   (BorderFactory.createEmptyBorder(10, 0, 10, 10));
        top.setBackground(new Color(0x0077B6));
        top.add         (LoginTitle);
        LoginTitle.setForeground(new Color(0xFFFFFF));

       // Login
        login.setFont           (new Font("Arial", Font.BOLD, 28));
        login.setAlignmentX     (Component.CENTER_ALIGNMENT);
        login.setForeground(new Color(0xFFFFFF));

        // Username & Password
        name.setMaximumSize     (new Dimension(200, 35));
        name.setAlignmentX      (Component.CENTER_ALIGNMENT);
        name.setBackground(new Color(0xFFFFFF));
        name.setForeground(new Color(0x000000));

        pass.setMaximumSize     (new Dimension(200, 35));
        pass.setAlignmentX      (Component.CENTER_ALIGNMENT);
        pass.setEchoChar((char) 0);
        pass.setBackground(new Color(0xFFFFFF));
        pass.setForeground(new Color(0x000000));


        // Button
        btnSignIn.setMaximumSize    (new Dimension(100, 35));
        btnSignIn.setAlignmentX     (Component.CENTER_ALIGNMENT);
        btnSignIn.setBackground(new Color(0x0077B6));
        btnSignIn.setForeground(new Color(0xFFFFFF));

         // Login Panel
        loginPanel  = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(new Color(0x1D3557));   
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        loginPanel.setPreferredSize     (new Dimension(265, 285));
        loginPanel.setBackground        (new Color(0x00B4D8));   // ← Login panel background
        loginPanel.setLayout        (new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.add          (Box.createVerticalStrut(20));
        loginPanel.add          (login);
        loginPanel.add          (Box.createVerticalStrut(40));
        loginPanel.add          (name);
        loginPanel.add          (Box.createVerticalStrut(20));
        loginPanel.add          (pass);
        loginPanel.add          (Box.createVerticalStrut(20));
        loginPanel.add          (btnSignIn);
        loginPanel.add          (Box.createVerticalStrut(40));

        // Events (Accounts)
        btnSignIn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String Inputusername 	= name.getText().trim();
        String Inputpassword 	= new String(pass.getPassword()).trim();

        if (Inputusername.equals("user") && Inputpassword.equals("123")) {
            JOptionPane.showMessageDialog(loginPanel, "Login successfully, Welcome Customer!");
            dispose();
            new Rental_List("user");
            
        } else if (Inputusername.equals("admin") && Inputpassword.equals("000")){
            JOptionPane.showMessageDialog(loginPanel, "Login successfully, Welcome Admin!");
            dispose();  
            new Admin_List("admin");
        } else {
            JOptionPane.showMessageDialog(loginPanel, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });

        //---------------------------------------------------------------------
        
        
        //------------------- Finalized Interface & Styling -------------------
        
        
        // Make Header bg transparent
        top.setOpaque(false); 

        //Overall Window
        setLayout(new GridBagLayout());
        GridBagConstraints GBConstraints = new GridBagConstraints();
        
        // Center Align Panels
        GBConstraints.gridx		= 0;                      
        GBConstraints.fill		= GridBagConstraints.NONE;  
        
        // Top Header [Row 0]
        GBConstraints.gridy		= 0;                       
        GBConstraints.weighty	= 3;                    
        GBConstraints.anchor	= GridBagConstraints.CENTER;
        add				(top, GBConstraints);

        // Login Panel [Row 1]
        GBConstraints.gridy		= 1;                    
        GBConstraints.weighty	= 3;                    
        GBConstraints.anchor	= GridBagConstraints.NORTH;
        add(loginPanel, GBConstraints);

        //Component Colors
        LoginTitle.setForeground	(new Color(0x1a3052));
        
        login.setForeground         (new Color(0xE0F2FF));
        name.setBackground          (new Color(0xF0F4F8));
        name.setForeground          (new Color(0x555555));
        pass.setBackground          (new Color(0xF0F4F8));
        pass.setForeground          (new Color(0x555555));
        btnSignIn.setBackground     (new Color(0x2E86C1));
        btnSignIn.setForeground     (new Color(0xFFFFFF));

        //Main Panel Color
        loginPanel.setBackground    (new Color(0x1D3557));

        //Content Pane
        BackgroundPanel bgPanel = new BackgroundPanel("login.png"); 
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);


        // Top Header [Row 0]
        GBConstraints.gridy     = 0;
        GBConstraints.weighty   = 3;
        GBConstraints.anchor    = GridBagConstraints.CENTER;
        bgPanel.add(top, GBConstraints);

        // Login Panel [Row 1]
        GBConstraints.gridy     = 1;
        GBConstraints.weighty   = 3;
        GBConstraints.anchor    = GridBagConstraints.NORTH;
        bgPanel.add(loginPanel, GBConstraints);

        setSize						(417, 485);
        setDefaultCloseOperation	(EXIT_ON_CLOSE);
        setLocationRelativeTo		(null);
        setVisible					(true);
        setResizable                (false);
    }

    // The Screen 
    public static void main(String[] args) {
        Rent_Data.loadFromFile();
        new SurfBoard();
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