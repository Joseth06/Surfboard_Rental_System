import javax.swing.*;
import java.awt.*;

public class Rental_Request extends JFrame{
    Rental_Request(){
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Rental_Request();
    }
}