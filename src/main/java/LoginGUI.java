import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JFrame loginFrame;

    public LoginGUI(){

        loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setPreferredSize(new Dimension(288,204));
        loginFrame.setResizable(false);

        loginFrame.add(mainPanel);

        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }
}
