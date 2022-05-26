import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class staffProfileEdit extends JFrame{
    private JPanel panel1;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAddress;
    private JTextField txtContact;
    private JTextField txtEmail;
    private JTextField txtUID;
    private JButton backButton;
    private JComboBox cbStatus;
    private JButton confirmButton;
    private JTextField txtIC;
    private JFrame staffEditFrame;
    String[] userData;

    public staffProfileEdit(String UID,String username){
        staffEditFrame = new JFrame("Register");
        staffEditFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        staffEditFrame.setPreferredSize(new Dimension(500, 600));
        staffEditFrame.setResizable(false);

        staffEditFrame.add(panel1);

        staffEditFrame.pack();
        staffEditFrame.setLocationRelativeTo(null);
        staffEditFrame.setVisible(true);

        cbStatus.addItem("Enabled"); cbStatus.addItem("Disabled");
        cbStatus.setSelectedItem("Enabled");

        ReadWrite rw = new ReadWrite();
        Functions fn = new Functions();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffListGUI sl = new staffListGUI(username);
                staffEditFrame.dispose();
            }
        });

        //Data from text file
        userData = rw.getUserData(UID);

        txtUID.setText(userData[0]);
        txtUsername.setText(userData[1]);
        txtPassword.setText(userData[2]);
        txtIC.setText(userData[3]);
        txtFirstName.setText(userData[4]);
        txtLastName.setText(userData[5]);
        txtAddress.setText(userData[6]);
        txtContact.setText(userData[7]);
        txtEmail.setText(userData[8]);
        if(userData[12].equals("Enabled") || userData[12].equals("Disabled")){
            cbStatus.setSelectedItem(userData[12]); }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] input = {txtUID.getText(),txtUsername.getText(),txtPassword.getText(),txtIC.getText(),txtFirstName.getText(),txtLastName.getText(),
                txtAddress.getText(),txtContact.getText(),txtEmail.getText(),cbStatus.getSelectedItem().toString()};
                if(!fn.validateEmail(input[8])){  JOptionPane.showMessageDialog(staffEditFrame,"Invalid email format");
                    return;  }
                if(!fn.validateContact(input[7])){  JOptionPane.showMessageDialog(staffEditFrame,"Invalid Contact Number");
                    return;  }
                if(!fn.generalValidation(input[1]) || !fn.generalValidation(input[2]) || !fn.generalValidation(input[3]) || !fn.generalValidation(input[4]) || !fn.generalValidation(input[5])){
                    JOptionPane.showMessageDialog(staffEditFrame,"Invalid text format");
                    return; }
                input[6] = fn.addressReplace(input[6]);
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    rw.updateStaffInfo(input);
                    staffListGUI sl = new staffListGUI(username);
                    staffEditFrame.dispose();}
            }
        });

    }
}
