import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JFrame loginFrame;

    public LoginGUI(){

        loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setPreferredSize(new Dimension(400,300));
        loginFrame.setResizable(false);

        loginFrame.add(mainPanel);

        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                //Test if fields are working
                System.out.println("Username : " + username + ";Password : " + password);
            }
        });
    }
}
