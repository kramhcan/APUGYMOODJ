import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Feedback extends JFrame{
    private JLabel lblTrainer;
    private JLabel lblClient;
    private JLabel lblBooking;
    private JTextField txtSession_date;
    private JTextField txtTrainer_id;
    private JTextField txtTrainer_fullname;
    private JTextField txtClient_id;
    private JTextField txtClient_fullname;
    private JLabel lblFeedback;
    private JTextArea atxtTrainer;
    private JTextArea atxtClient;
    private JComboBox cbSessionID;
    private JPanel mainPanel;
    private JLabel lblTitle;
    private JLabel lblBooking_id;
    private JLabel lblBooking_date;
    private JButton btnBack;
    private JLabel lblTrainer_id;
    private JLabel lblTrainer_name;
    private JLabel lblClient_id;
    private JLabel lblClient_name;
    private JButton btnSubmit;
    private JFrame feedbackFrame;

    String SessionID = "";
    String SessionDate = "";
    String TrainerID = "";
    String TrainerName = "";
    String ClientID = "";
    String ClientName = "";
    String TrainerFeedback = "";
    String ClientFeedback = "";


    Functions fn = new Functions();

    public Feedback(String username){
        feedbackFrame = new JFrame("Register");
        feedbackFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        feedbackFrame.setPreferredSize(new Dimension(800, 500));
        feedbackFrame.add(mainPanel);

        feedbackFrame.pack();
        feedbackFrame.setLocationRelativeTo(null);
        feedbackFrame.setVisible(true);

        cbSessionID.addItem("Please Select");
        String[] sessionID = fn.getSessionID();
        for(int i = 0; i < sessionID.length; i ++){
            cbSessionID.addItem(sessionID[i]);              // this is to show sessions ids in the combo box
        }

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI indexGUI = new IndexGUI(username);
                feedbackFrame.dispose();
            }
        });

        /**
         * input: text file sessions with session id info
         * output: show the details of session id obtained from text file on the gui
         * extract session date
         * extract trainer information
         * extract client information
         */
        cbSessionID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // obtain session id from cbSessionID -> it is a string
                String sessionID = (String) cbSessionID.getSelectedItem();

                // extract session date
                String sessionDate = fn.getSessionDate(sessionID);
                System.out.println(sessionDate);
                txtSession_date.setText(sessionDate);

                // extract trainer information
                String[] trainerDetails = fn.getTrainerName(sessionID);
                txtTrainer_id.setText(trainerDetails[0]);
                txtTrainer_fullname.setText(trainerDetails[1]);

                // extract client information
                String[] clientDetails = fn.getClientName(sessionID);
                txtClient_fullname.setText(clientDetails[1]);
                txtClient_id.setText(clientDetails[0]);

            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] input = getFeedback();
                String[] fieldNames = {"Session ID","Session Date","Trainer ID","Trainer Name","Client ID","Client Name","Trainer Feedback","Client Feedback"};

                fn.createFeedback(input);

            }
        });

    }

    private String[] getFeedback() {
        SessionID = cbSessionID.getSelectedItem().toString();
        SessionDate = txtSession_date.getText();

        TrainerID = txtTrainer_id.getText();
        TrainerName = txtTrainer_fullname.getText();

        ClientID = txtClient_id.getText();
        ClientName  = txtClient_fullname.getText();

        TrainerFeedback = atxtTrainer.getText();
        ClientFeedback = atxtClient.getText();

        String[] input = {SessionID, SessionDate, TrainerID, TrainerName, ClientID, ClientName, TrainerFeedback, ClientFeedback};

        return input;
    }
}
