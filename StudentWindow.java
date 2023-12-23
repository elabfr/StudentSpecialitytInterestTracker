import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentWindow extends JFrame implements ActionListener {
    JButton signin;
    JButton signon;

    public StudentWindow() {
        super("student window");
        setSize(280, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Obtaining the contentPane of the JFrame
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout()); // Utilisation d'un FlowLayout

        // Setting the background color to black
        contentPane.setBackground(Color.BLACK);

        // Adding vertical space between the label and the buttons
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // 20 pixels spacing

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(0, 1, 0, 10)); // GridLayout avec une colonne, espacement vertical de 10 pixels
        panel2.setBackground(Color.BLACK); // Setting the background of the JPanel to black

        signin = new JButton("Sign in");
        signon = new JButton("Sign on");

        // Setting the background color of the buttons to #009a75
        signin.setBackground(Color.decode("#009a75"));
        signon.setBackground(Color.decode("#009a75"));

        signin.setForeground(Color.WHITE); // Changing the text color to white
        signon.setForeground(Color.WHITE); // Changing the text color to white

        panel2.add(signin);
        panel2.add(signon);

        contentPane.add(panel2);
        signin.addActionListener(this);
        signon.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signin) {
            Form f = new Form();
            f.setVisible(true);
        } else if (e.getSource() == signon) {
            // StudentWindow signonWindow = new StudentWindow();
            // signonWindow.setVisible(true);
        }
    }
}
