import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI extends JFrame {
    private JPanel panel1;
    private JButton loginButton;
    private JButton registerButton;
    private JButton button1;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton logoutButton;
    private JLabel nameLabel;
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI lg = new LoginGUI();
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
