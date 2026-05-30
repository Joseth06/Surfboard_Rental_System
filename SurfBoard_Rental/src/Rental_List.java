import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rental_List extends JFrame {

    Rental_List(){
        JLabel SurfList, nameOfboard, stats;
        JButton add;
        JPanel status, top, statusCenter;
        JScrollPane scroll;

        //JLabel
        SurfList = new JLabel("SurfBoards List", SwingConstants.CENTER);
        SurfList.setFont(new Font("Arial", Font.BOLD, 20));
        nameOfboard = new JLabel("SurfBoard1", SwingConstants.LEFT);
        stats = new JLabel("Occupied", SwingConstants.RIGHT);

        //JButton
        add = new JButton("+");

        //Scroll not for now
        scroll = new JScrollPane();

        //Top Panel
        top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        top.add(SurfList);
        top.add(add, BorderLayout.EAST);

        //Status Panel
        status = new JPanel();
        status.setLayout(new GridLayout());
        status.setPreferredSize(new Dimension(330,90));
        status.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statusCenter = new JPanel(new GridBagLayout());
        status.add(nameOfboard);
        status.add(stats);
        
        
        // Events
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Rental_Request();
                dispose();
            }  
        });


        //Overall Window
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        statusCenter.add(status);
        add(statusCenter, BorderLayout.CENTER);

        //Colors
        status.setBackground(new Color(0x1a3052));
        SurfList.setForeground(new Color(0xad9a6f));
        nameOfboard.setForeground(new Color(0xad9a6f));
        stats.setForeground(new Color(0xad9a6f));

        setSize(417,485);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    public static void main(String arg[]){
        new Rental_List();
    }
}