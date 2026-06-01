import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SurfBoard extends JFrame {
    SurfBoard() {

        JLabel login;
        JButton btnSignIn;
        JPasswordField pass;
        JTextField name;
        JPanel MainPanel;

        getContentPane().setBackground(new Color(0xf4efe9));

        // Login
        login = new JLabel("text", SwingConstants.CENTER);
        login.setFont(new Font("Arial", Font.BOLD, 28));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username
        name = new JTextField("username", 15);
        name.setMaximumSize(new Dimension(200, 35));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Password
        pass = new JPasswordField(15);
        pass.setMaximumSize(new Dimension(200, 35));
        pass.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button
        btnSignIn = new JButton("Sign In");
        btnSignIn.setMaximumSize(new Dimension(100, 35));
        btnSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login Panel
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(265, 285));
        MainPanel.setBackground(new Color(0x1a3052));

        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));

        MainPanel.add(Box.createVerticalStrut(20));
        MainPanel.add(login);
        MainPanel.add(Box.createVerticalStrut(40));
        MainPanel.add(name);
        MainPanel.add(Box.createVerticalStrut(20));
        MainPanel.add(pass);
        MainPanel.add(Box.createVerticalStrut(20));
        MainPanel.add(btnSignIn);
        MainPanel.add(Box.createVerticalStrut(40));

        setLayout(new GridBagLayout());
        add(MainPanel);

        //Components Colors
        login.setForeground(new Color(0xf4efe9));
        name.setForeground(Color.GRAY);
        btnSignIn.setBackground(new Color(0xad9a6f));

        //Main Panel Color
        MainPanel.setBackground(new Color(0x1a3052));

        // Events
        btnSignIn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String Inputusername = name.getText();
        String Inputpassword = new String(pass.getPassword());

        if (Inputusername.equals("user") && Inputpassword.equals("123")) {
            JOptionPane.showMessageDialog(MainPanel, "Login successfully, Welcome Customer!");
            dispose();
            new Rental_List();
            
        } else {
            JOptionPane.showMessageDialog(MainPanel, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });

        
        setSize(417, 485);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    

    public static void main(String[] args) {
        new SurfBoard();
    }
}