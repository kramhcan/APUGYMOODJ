import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI extends JFrame {
    private JPanel panel1;
    private JButton loginButton;
    private JButton registerButton;
    private JButton bookingButton;
    private JButton paymentButton;
    private JButton feedbackButton;
    private JButton sessionsButton;
    private JButton logoutButton;
    private JLabel nameLabel;
    private JButton customersButton;
    private JButton cusRegisterButton;
    private JFrame mainFrame;

    public IndexGUI(){
        mainFrame = new JFrame("Index Page");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500,500));
        mainFrame.setResizable(false);

        mainFrame.add(panel1);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        logoutButton.setVisible(false);

        bookingButton.setEnabled(false);
        paymentButton.setEnabled(false);
        feedbackButton.setEnabled(false);
        sessionsButton.setEnabled(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI lg = new LoginGUI();
                mainFrame.dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterGUI rg = new RegisterGUI();
                mainFrame.dispose();
            }
        });
        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffListGUI sl = new staffListGUI();
                mainFrame.dispose();
            }
        });
    }

    public IndexGUI(String username){
        mainFrame = new JFrame("Index Page");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500,500));
        mainFrame.setResizable(false);

        mainFrame.add(panel1);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        nameLabel.setText(username);

        loginButton.setVisible(false);
        registerButton.setVisible(false);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to log out?","Confirm",JOptionPane.YES_NO_OPTION);
                if (x==0){  mainFrame.dispose(); new IndexGUI(); }
            }
        });
    }
}
