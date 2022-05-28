import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cRegisterGUI extends JFrame{
    private JPanel panel1;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAddress;
    private JTextField txtContact;
    private JTextField txtEmail;
    private JTextField txtIC;
    private JButton backButton;
    private JButton registerButton;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private DatePicker dpDOB;
    private JComboBox cbPackage;
    private JFrame cRegisterFrame;

    String IC = "";
    String firstName = "";
    String lastName = "";
    String address = "";
    String contNumber = "";
    String email = "";
    String gender = "";
    String DOB = "";
    String pack = "";

    public cRegisterGUI(String username){
        cRegisterFrame = new JFrame("New Member");
        cRegisterFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cRegisterFrame.setPreferredSize(new Dimension(500, 500));
        cRegisterFrame.setResizable(false);

        cRegisterFrame.add(panel1);

        cRegisterFrame.pack();
        cRegisterFrame.setLocationRelativeTo(null);
        cRegisterFrame.setVisible(true);

        
        Functions fn = new Functions();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberListGUI memberListGUI = new memberListGUI(username);
                cRegisterFrame.dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] input = getTextField();
                fn.setInput(input);
                String[] fieldNames = {"IC","First Name","Last Name","Address","Contact Number","E-mail","Gender","DOB","Package Type"};
                input[3] = fn.addressReplace(input[3]);
                if(fn.checkMissingInput(input, fieldNames, cRegisterFrame)){
                    return; }
                if(fn.existingMember(cRegisterFrame)){
                    return; }
                if(!fn.validateEmail(input[5])){  JOptionPane.showMessageDialog(cRegisterFrame,"Invalid email format");
                    return;  }
                if(!fn.validateContact(input[4])){  JOptionPane.showMessageDialog(cRegisterFrame,"Invalid Contact Number");
                    return;  }
                for(int i = 0; i<9; i++){ if(!fn.generalValidation(input[i])){
                    if(i==3 || i==4 || i==5){ continue; }
                    JOptionPane.showMessageDialog(cRegisterFrame,"Invalid text format");
                    return; }
                }
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    return; }


                fn.registerMember();
                JOptionPane.showMessageDialog(cRegisterFrame, "New member successfully registered");

            }
        });
    }
    private String[] getTextField(){
        IC = txtIC.getText();
        firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        address = txtAddress.getText();
        contNumber = txtContact.getText();
        email = txtEmail.getText();
        if(maleRadioButton.isSelected()){ gender = "Male";}
        else if(femaleRadioButton.isSelected()){ gender = "Female";}
        else { gender = ""; }
        DOB = dpDOB.getText();
        pack = cbPackage.getSelectedItem().toString();


        String[] input = {IC,firstName,lastName,address,contNumber,email,gender,DOB,pack};

        return input;
    }
}
