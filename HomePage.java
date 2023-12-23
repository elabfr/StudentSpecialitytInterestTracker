import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener {
    JButton admin;
    JButton student;

    public static void main(String args[]) {
        new HomePage();
    }

    public HomePage() {
        super("Home Page");
        setSize(300, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.BLACK);

        JLabel label1 = new JLabel("welcome to Michingan University");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setForeground(Color.WHITE);
        contentPane.add(label1);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.BLACK);

        admin = new JButton("Admin");
        student = new JButton("Student");

        admin.setBackground(Color.decode("#009a75"));
        student.setBackground(Color.decode("#009a75"));

        admin.setForeground(Color.WHITE);
        student.setForeground(Color.WHITE);

        panel2.add(admin);
        panel2.add(student);

        contentPane.add(panel2);
        admin.addActionListener(this);
        student.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == admin) {
            AdminWindow adminWindow = new AdminWindow();
            adminWindow.setVisible(true);
        } else if (e.getSource() == student) {
            Form f = new Form();
            f.setVisible(true);
        }
    }
}
