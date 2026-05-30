import javax.swing.*;
import java.awt.*;

public class Rental_List extends JFrame {

    Rental_List(){
        JLabel SurfList;
        JButton add;
        JPanel SurfBoard_Status;

        SurfList = new JLabel("SurfBoards List");
        add = new JButton("+");

        //Top 
        add(SurfList);
        add(add);

        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        //Colors
        
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    public static void main(String arg[]){
        new Rental_List();
    }
}