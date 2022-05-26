import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JTextField emailTxt;
    private JButton backButton;
    private DatePicker datePicker;
    private JButton registerButton;
    private JRadioButton staffRadioButton;
    private JRadioButton trainerRadioButton;
    private JTextField txtIC;
    private JFrame registerFrame;

    String IC = "";
    String username = "";
    String password = "";
    String firstName = "";
    String lastName = "";
    String address = "";
    String contNumber = "";
    String email = "";
    String gender = "";
    String eType = "";
    String DOB = "";

    public RegisterGUI(String usernameInput){
        
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
                staffListGUI ig = new staffListGUI(usernameInput);
                registerFrame.dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] input = getTextField();
                String[] fieldNames = {"Username","Password","IC","First Name","Last Name","Address","Contact","E-mail","Gender","User Type","DOB"};

                ReadWrite rw = new ReadWrite();
                Functions fn = new Functions();

                if(!fn.validateEmail(input[7])){  JOptionPane.showMessageDialog(registerFrame,"Invalid email format");
                    return;  }
                if(!fn.validateContact(input[6])){  JOptionPane.showMessageDialog(registerFrame,"Invalid Contact Number");
                    return;  }
                if(!fn.generalValidation(input[1]) || !fn.generalValidation(input[2]) || !fn.generalValidation(input[3]) || !fn.generalValidation(input[4]) || !fn.generalValidation(input[5])){
                    JOptionPane.showMessageDialog(registerFrame,"Invalid text format");
                    return; }
                if(fn.checkMissingInput(input, fieldNames, registerFrame)) {
                    return; }
                if(rw.existingStaffUser(input, registerFrame)) {
                    return; }
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    return; }

                input[5] = fn.addressReplace(input[5]);

                rw.RegisterStaff(input);
                JOptionPane.showMessageDialog(registerFrame, "New staff successfully registered");

            }
        });

    }

    private String[] getTextField(){
        username = usernameTxt.getText();
        password = String.valueOf(passwordTxt.getPassword());
        IC = txtIC.getText();
        firstName = firstNameTxt.getText();
        lastName = lastNameTxt.getText();
        address = addressTxt.getText();
        contNumber = contactTxt.getText();
        email = emailTxt.getText();
        if(maleRadioButton.isSelected()){ gender = "Male";}
        else if(femaleRadioButton.isSelected()){ gender = "Female";}
        else { gender = ""; }
        if(trainerRadioButton.isSelected()){ eType = "Trainer";}
        else if(staffRadioButton.isSelected()){ eType = "Staff";}
        else { eType = ""; }
        DOB = datePicker.getText();

        String[] input = {username,password,IC,firstName,lastName,address,contNumber,email,gender,eType,DOB};

        return input;
    }
}
