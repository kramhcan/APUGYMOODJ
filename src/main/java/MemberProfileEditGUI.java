import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberProfileEditGUI extends JFrame {
    private JButton backButton;
    private JButton confirmButton;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAddress;
    private JTextField txtContact;
    private JTextField txtEmail;
    private JTextField txtIC;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private DatePicker dpDOB;
    private JTextField txtID;
    private JPanel memberEditPanel;
    private JComboBox cbStatus;
    private ButtonGroup buttonGroup1;
    private JFrame memberEditFrame;
    String[] userData;

    ReadWrite rw = new ReadWrite();
    Functions fn = new Functions();

    public MemberProfileEditGUI(String UID, String username){
        memberEditFrame = new JFrame("Edit Member");
        memberEditFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        memberEditFrame.setPreferredSize(new Dimension(500, 600));
        memberEditFrame.setResizable(false);

        memberEditFrame.add(memberEditPanel);

        memberEditFrame.pack();
        memberEditFrame.setLocationRelativeTo(null);
        memberEditFrame.setVisible(true);

        SetDataFields(UID);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberListGUI sl = new memberListGUI(username);
                memberEditFrame.dispose();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] input = getTextField();
                rw.setInput(input);
                if(!fn.validateEmail(input[6])){  JOptionPane.showMessageDialog(memberEditFrame,"Invalid email format");
                    return;  }
                if(!fn.validateContact(input[5])){  JOptionPane.showMessageDialog(memberEditFrame,"Invalid Contact Number");
                    return;  }
                for(int i = 0; i<8; i++){ if(!fn.generalValidation(input[i])){
                    if(i==4 || i==5 || i==6){ continue; }
                    JOptionPane.showMessageDialog(memberEditFrame,"Invalid text format");
                    return; }
                }
                input[6] = fn.addressReplace(input[6]);
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    rw.updateMemberInfo();
                    memberListGUI ml = new memberListGUI(username);
                    memberEditFrame.dispose();}
            }
        });
    }
    private String[] getTextField(){
        String UID = txtID.getText();
        String IC = txtIC.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String contNumber = txtContact.getText();
        String email = txtEmail.getText();
        String status = cbStatus.getSelectedItem().toString();
        String gender;
        String DOB;
        if(maleRadioButton.isSelected()){ gender = "Male";}
        else if(femaleRadioButton.isSelected()){ gender = "Female";}
        else { gender = ""; }
        DOB = dpDOB.getText();

        String[] input = {UID,IC,firstName,lastName,address,contNumber,email,gender,DOB,status};

        return input;
    }

    public void SetDataFields(String UID){
        userData = rw.getMemberData(UID);

        txtID.setText(userData[0]);
        txtIC.setText(userData[1]);
        txtFirstName.setText(userData[2]);
        txtLastName.setText(userData[3]);
        txtAddress.setText(userData[4]);
        txtContact.setText(userData[5]);
        txtEmail.setText(userData[6]);
        if(userData[7].equals("Male")){ maleRadioButton.setSelected(true);  }
        else if(userData[7].equals("Female")){ femaleRadioButton.setSelected(true); }
        else {  buttonGroup1.clearSelection();  }
        dpDOB.setText(userData[8]);
        if(userData[9].equals("Enabled") || userData[9].equals("Disabled")){
            cbStatus.setSelectedItem(userData[9]); }
    }
}
