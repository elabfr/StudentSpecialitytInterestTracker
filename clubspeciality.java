import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class clubspeciality extends JFrame {
    JComboBox<String> b1;
    JComboBox<String> b2;
    public String[] specialities = { "Math", "Science", "History", "Art", "Computer Science", "Languages", "Physics",
            "Biology" };
    public String[] clubs = { "Music", "Sports", "Drama", "Debate", "Chess", "Photography", "Programming", "Robotics" };
    private final String url = "jdbc:mysql://localhost:3306/etudiant";
    private DefaultTableModel tableModel;
    private JTable table;

    public clubspeciality() {
        super("club OR speciality");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        b1 = new JComboBox<>();
        b1.addItem("Select Speciality");
        for (String spec : specialities) {
            b1.addItem(spec);
        }
        b1.setBackground(Color.decode("#3B3B3B"));
        b1.setForeground(Color.decode("#009a75"));
        b1.setBorder(new EmptyBorder(5, 10, 5, 10));
        b1.addActionListener(new ComboBoxListener());

        b2 = new JComboBox<>();
        b2.addItem("Select Club");
        for (String club : clubs) {
            b2.addItem(club);
        }
        b2.setBackground(Color.decode("#3B3B3B"));
        b2.setForeground(Color.decode("#009a75"));
        b2.setBorder(new EmptyBorder(5, 10, 5, 10));
        b2.addActionListener(new ComboBoxListener());

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.BLACK);
        panel.add(b1);
        panel.add(b2);

        tableModel = new DefaultTableModel(new String[] { "Name", "Age", "Speciality", "Club", "Percentage" }, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.decode("#3B3B3B"));
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    class ComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
            String selected = (String) comboBox.getSelectedItem();
            if (!selected.equals("Select Speciality") && !selected.equals("Select Club")) {
                populateTable(selected);
            }
        }
    }

    private void populateTable(String selected) {
        try {
            Connection connection = DriverManager.getConnection(url, "ela", "7#HxPw&9zY$");

            String countQuery = "SELECT COUNT(*) AS total FROM information";
            Statement countStatement = connection.createStatement();
            ResultSet countResultSet = countStatement.executeQuery(countQuery);
            countResultSet.next();
            int totalStudents = countResultSet.getInt("total");
            countResultSet.close();
            countStatement.close();

            String dataQuery = "SELECT name, age, speciality, club FROM information WHERE speciality = '" + selected
                    + "' OR club = '" + selected + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(dataQuery);

            tableModel.setRowCount(0);

            int selectedStudents = 0;

            while (resultSet.next()) {
                Object[] rowData = new Object[] {
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("speciality"),
                        resultSet.getString("club")
                };
                tableModel.addRow(rowData);

                String spec = resultSet.getString("speciality");
                String club = resultSet.getString("club");
                if (spec.equals(selected) || club.equals(selected)) {
                    selectedStudents++;
                }
            }

            resultSet.close();
            statement.close();
            connection.close();

            if (totalStudents > 0) {
                double percentage = ((double) selectedStudents / totalStudents) * 100;
                Object[] percentageData = new Object[] { "", "", "", "", String.format("%.2f%%", percentage) };
                tableModel.addRow(percentageData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
