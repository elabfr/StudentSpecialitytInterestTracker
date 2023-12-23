import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminWindow extends JFrame implements ActionListener {
    JButton signInButton;
    JTextField adminNameField;
    JTextField adminPasswordField;

    public AdminWindow() {
        super("Admin Window");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modification de la couleur de fond du panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK); // Définition du fond en noir

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setOpaque(false); // Rendre le panneau transparent pour la couleur de fond noire

        JLabel label4 = new JLabel("Admin Name");
        label4.setForeground(Color.WHITE); // Définition de la couleur du texte en blanc
        adminNameField = new JTextField(20);
        JLabel label5 = new JLabel("Admin Password");
        label5.setForeground(Color.WHITE); // Définition de la couleur du texte en blanc
        adminPasswordField = new JTextField(20);

        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(label4);
        panel.add(adminNameField);
        panel.add(label5);
        panel.add(adminPasswordField);

        mainPanel.add(panel, BorderLayout.CENTER);

        // Création et modification de la couleur du bouton "Sign in"
        signInButton = new JButton("Sign in");
        signInButton.setBackground(Color.decode("#009a75")); // Définition de la couleur du bouton
        signInButton.addActionListener(this); // Ajout de l'écouteur d'événements au bouton

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.BLACK); // Définition du fond du panel inférieur en noir
        bottomPanel.add(signInButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            String enteredName = adminNameField.getText();
            String enteredPassword = adminPasswordField.getText();

            if (enteredName.equals("adminadmin") && enteredPassword.equals("0000")) {
                clubspeciality cs = new clubspeciality();
                cs.setVisible(true);
            } else {
                // Les informations d'identification sont incorrectes
                JOptionPane.showMessageDialog(this, "Authentication failed. Please check your credentials.");
            }
        }
    }

}
