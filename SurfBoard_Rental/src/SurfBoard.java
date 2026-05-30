import javax.swing.*;
import java.awt.*;

public class SurfBoard extends JFrame{
    SurfBoard(){
        JLabel login;
        JButton btnSignIn;
        JPasswordField pass;
        JTextField name;
        JPanel MainPanel;



        login = new JLabel("Login");
        btnSignIn = new JButton("Sign In");
        name = new JTextField("username", 15);
        pass = new JPasswordField(15);
        MainPanel = new JPanel();
        
        MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        MainPanel.setPreferredSize(new Dimension(250, 205));
        MainPanel.setBackground(new Color(0x1a3052));

        MainPanel.setLayout(new GridLayout(4, 1, 5, 5));
        MainPanel.add(login);
        MainPanel.add(name);
        MainPanel.add(pass);
        MainPanel.add(btnSignIn);
        add(MainPanel);
        

        setBackground(new Color(0x1a3052));
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    public static void main(String[] args) throws Exception {
       new SurfBoard();
    }
}
