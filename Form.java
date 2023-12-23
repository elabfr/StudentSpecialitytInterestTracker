import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Form extends JFrame implements ActionListener {
    JTextField nameField;
    JTextField ageField;
    JComboBox<String> specialityComboBox;
    JComboBox<String> clubsComboBox;
    String url = "jdbc:mysql://localhost:3306/etudiant";
    public String[] specialities = { "Math", "Science", "History", "Art", "Computer Science", "Languages", "Physics",
            "Biology" };
    public String[] clubs = { "Music", "Sports", "Drama", "Debate", "Chess", "Photography", "Programming", "Robotics" };

    public Form() {
        super("Student Window");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 2));
        contentPane.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(Color.WHITE);
        ageField = new JTextField();

        JLabel specialityLabel = new JLabel("Speciality:");
        specialityLabel.setForeground(Color.WHITE);
        specialityComboBox = new JComboBox<>(specialities);

        JLabel clubsLabel = new JLabel("Clubs:");
        clubsLabel.setForeground(Color.WHITE);
        clubsComboBox = new JComboBox<>(clubs);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(Color.decode("#009a75"));
        signInButton.setForeground(Color.WHITE);
        signInButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.decode("#009a75"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);

        contentPane.add(nameLabel);
        contentPane.add(nameField);
        contentPane.add(ageLabel);
        contentPane.add(ageField);
        contentPane.add(specialityLabel);
        contentPane.add(specialityComboBox);
        contentPane.add(clubsLabel);
        contentPane.add(clubsComboBox);
        contentPane.add(signInButton);
        contentPane.add(cancelButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sign In")) {
            String name = nameField.getText();
            String age = ageField.getText();
            String speciality = (String) specialityComboBox.getSelectedItem();
            String club = (String) clubsComboBox.getSelectedItem();

            try {
                Connection connection = DriverManager.getConnection(url, "ela", "7#HxPw&9zY$");
                String insertQuery = "INSERT INTO information (name, age, speciality, club) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, age);
                preparedStatement.setString(3, speciality);
                preparedStatement.setString(4, club);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();

                JOptionPane.showMessageDialog(this, "Student information saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Cancel")) {
            nameField.setText("");
            ageField.setText("");
            specialityComboBox.setSelectedIndex(0);
            clubsComboBox.setSelectedIndex(0);
        }
    }
}
