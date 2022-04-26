import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LoginGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JFrame loginFrame;

    String username = "";
    String password = "";

    public LoginGUI() {

        loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setPreferredSize(new Dimension(400, 300));
        loginFrame.setResizable(false);

        loginFrame.add(mainPanel);

        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                username = usernameField.getText();
                password = String.valueOf(passwordField.getPassword());

                ReadWrite readWrite = new ReadWrite();
                boolean x = readWrite.Login(username, password);

                if (x == true) {
                    System.out.println("Login successful");
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful\r\nWelcome back," + username);
                    loginFrame.dispose();
                    IndexGUI indexGUI = new IndexGUI(username); }
                else {
                    System.out.println("Login failed"); }
            }
        });
    }
}
