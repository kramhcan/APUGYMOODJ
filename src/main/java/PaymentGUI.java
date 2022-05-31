import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentGUI extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JComboBox cbMemberID;
    private JComboBox comboBox1;
    private JPanel membershipPanel;
    private JPanel sessionsPanel;
    private JTextField txtDuration;
    private JComboBox cbSessionID;
    private JTextField txtCostSession;
    private JButton OKSessionButton;
    private JTextField txtPackage;
    private JTextField txtCostMember;
    private JButton OKMemberButton;
    private JFrame paymentFrame;

    Functions fn = new Functions();

    public PaymentGUI(String username){
        paymentFrame = new JFrame("Payment Page");
        paymentFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        paymentFrame.setPreferredSize(new Dimension(500, 400));
        paymentFrame.setResizable(false);

        paymentFrame.add(mainPanel);

        paymentFrame.pack();
        paymentFrame.setLocationRelativeTo(null);
        paymentFrame.setVisible(true);

        membershipPanel.setVisible(false);
        sessionsPanel.setVisible(false);



        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = comboBox1.getSelectedItem().toString();
                if(res.equals("Please Select")){ return;    }
                if(res.equals("Gym Membership")){
                    sessionsPanel.setVisible(false);
                    membershipPanel.setVisible(true);

                    String[] memberID = fn.getMemberIDs(true);
                    DefaultComboBoxModel modMember = new DefaultComboBoxModel(memberID);
                    cbMemberID.setModel(modMember);
                    return;
                }
                if(res.equals("Session Payment")){
                    membershipPanel.setVisible(false);
                    sessionsPanel.setVisible(true);

                    String[] sessionID = fn.getSessionId();
                    DefaultComboBoxModel modSession = new DefaultComboBoxModel(sessionID);
                    cbSessionID.setModel(modSession);
                    return;
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI(username);
                paymentFrame.dispose();
            }
        });
        cbMemberID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] split = cbMemberID.getSelectedItem().toString().split(" ; ");
                String pack = fn.getMemberPackageByID(split[0]);
                txtPackage.setText(pack);

                if(pack.equals("Normal(1 Month)")){txtCostMember.setText("100");
                    return;}
                if(pack.equals("Normal(3 Months)")){txtCostMember.setText("270");
                    return;}
                if(pack.equals("Normal(1 Year)")){txtCostMember.setText("850");
                    return;}
                if(pack.equals("Student(1 Month)")){txtCostMember.setText("85");
                    return;}
                if(pack.equals("Student(3 Months)")){txtCostMember.setText("230");
                    return;}
                if(pack.equals("Student(1 Year)")){txtCostMember.setText("700");
                    return;}
            }
        });
        OKMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {return;}
                String[] split = cbMemberID.getSelectedItem().toString().split(" ; ");
                String cost = txtCostMember.getText();
                fn.enableMember(split[0], cost);
                memberListGUI sg = new memberListGUI(username);
                paymentFrame.dispose();
            }
        });
        cbSessionID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String duration = fn.getSessionDurationByID(cbSessionID.getSelectedItem().toString());
                txtDuration.setText(duration);

                if(duration.equals("1")){txtCostSession.setText("100");
                    return;}
                if(duration.equals("2")){txtCostSession.setText("200");
                    return;}
                if(duration.equals("3")){txtCostSession.setText("300");
                    return;}
                if(duration.equals("4")){txtCostSession.setText("400");
                    return;}
                if(duration.equals("5")){txtCostSession.setText("500");
                    return;}
            }
        });
        OKSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {return;}
                String sessionID = cbSessionID.getSelectedItem().toString();
                String cost = txtCostSession.getText();
                fn.setSessionPaid(sessionID, cost);
                SessionsGUI sg = new SessionsGUI(username);
                paymentFrame.dispose();
            }
        });
    }
}
