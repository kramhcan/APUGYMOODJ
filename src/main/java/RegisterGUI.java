import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JTextField usernameTxt;
    private JPanel mainPanel;
    private JPasswordField passwordTxt;
    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField addressTxt;
    private JTextField contactTxt;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField textField1;
    private JButton backButton;
    private DatePicker datePicker;
    private JFrame registerFrame;

    String username = "";
    String password = "";
    String firstName = "";
    String lastName = "";
    String address = "";
    String contNumber = "";
    String email = "";

    public RegisterGUI(){
        
        registerFrame = new JFrame("Register");
        registerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        registerFrame.setPreferredSize(new Dimension(500, 500));
        registerFrame.setResizable(false);

        registerFrame.add(mainPanel);

        registerFrame.pack();
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI();
                registerFrame.dispose();
            }
        });
    }
}
