import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NewSessionGUI extends JFrame{
    private JButton backButton;
    private JTextField txtMemberName;
    private JTextField txtTrainerName;
    private JComboBox cbAvailableTime;
    private JPanel mainPanel;
    private JComboBox cbMemberID;
    private JComboBox cbTrainerID;
    private DatePicker dpSessionDate;
    private JComboBox cbDuration;
    private JButton createButton;
    private JFrame newSessionFrame;

    Functions fn = new Functions();

    public NewSessionGUI(String username){
        newSessionFrame = new JFrame("New Session Booking");
        newSessionFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        newSessionFrame.setPreferredSize(new Dimension(500, 600));
        newSessionFrame.setResizable(false);

        newSessionFrame.add(mainPanel);

        newSessionFrame.pack();
        newSessionFrame.setLocationRelativeTo(null);
        newSessionFrame.setVisible(true);

        cbMemberID.addItem("Please Select");
        String[] memberID = fn.getMemberIDs();
        for(int i = 0; i < memberID.length; i ++){
            cbMemberID.addItem(memberID[i]);
        }
        cbMemberID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbMemberID.getSelectedItem().toString().equals("Please Select")){ return;    }
                cbTrainerID.setEnabled(true);
                String[] split = cbMemberID.getSelectedItem().toString().split(" ; ");
                txtMemberName.setText(split[1]);

                String[] trainerID = fn.getTrainerIDs();
                DefaultComboBoxModel mod = new DefaultComboBoxModel(trainerID);
                cbTrainerID.setModel(mod);
            }
        });
        cbTrainerID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dpSessionDate.setEnabled(true);
                String[] split = cbTrainerID.getSelectedItem().toString().split(" ; ");
                txtTrainerName.setText(split[1]);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionsGUI sg = new SessionsGUI(username);
                newSessionFrame.dispose();
            }
        });
        cbAvailableTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dpSessionDate.getText();
                String trainerID = cbTrainerID.getSelectedItem().toString();
                if(date.isBlank()){ return; }
                int[] availableHours = fn.calculateAvailableTime(trainerID, date);
                for(int i = 0; i<availableHours.length; i++){
                    String time = String.valueOf(availableHours[i]);
                    if(time.length() == 1){
                        cbAvailableTime.addItem("0"+time+"00");
                        continue;}
                    cbAvailableTime.addItem(time+"00");
                }
            }
        });

        dpSessionDate.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                cbAvailableTime.removeAllItems();
                String date = dpSessionDate.getText();
                String[] trainerID = cbTrainerID.getSelectedItem().toString().split(" ; ");
                if(date.isBlank()){ return; }
                if(trainerID[0].isBlank()){ return; }
                int[] availableHoursInt = fn.calculateAvailableTime(trainerID[0], date);
                String[] replace = new String[availableHoursInt.length];
                for(int i = 0; i<availableHoursInt.length; i++){
                    if(String.valueOf(availableHoursInt[i]).length() == 1){
                        String s = String.valueOf(availableHoursInt[i]);
                        s = "0"+s+"00";
                        replace[i] = s;
                        continue;
                    }
                    String s = String.valueOf(availableHoursInt[i]);
                    s = s+"00";
                    replace[i] = s;
                    continue;
                }
                String[] availableHoursString = replace;
                DefaultComboBoxModel mod = new DefaultComboBoxModel(availableHoursString);
                cbAvailableTime.setModel(mod);
                cbAvailableTime.setEnabled(true);
            }
        });
        cbAvailableTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbDuration.setEnabled(true);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {return;}
                String[] trainerInput = cbTrainerID.getSelectedItem().toString().split(" ; ");
                String[] memberInput = cbMemberID.getSelectedItem().toString().split(" ; ");
                String[] input = {trainerInput[0],txtMemberName.getText(),memberInput[0],txtTrainerName.getText(),dpSessionDate.getText(),cbDuration.getSelectedItem().toString(),cbAvailableTime.getSelectedItem().toString()};
                fn.setInput(input);
                fn.newSession();
                SessionsGUI sg = new SessionsGUI(username);
                newSessionFrame.dispose();
            }
        });
    }
}
