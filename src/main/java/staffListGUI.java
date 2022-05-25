import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class staffListGUI extends JFrame {

    private JTable staffTable;
    private JButton backButton;
    private JPanel mainPanel;
    private JFrame staffFrame;

    public staffListGUI(){
        staffFrame = new JFrame("Register");
        staffFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        staffFrame.setPreferredSize(new Dimension(800, 500));
        staffFrame.setResizable(false);

        staffFrame.add(mainPanel);

        staffFrame.pack();
        staffFrame.setLocationRelativeTo(null);
        staffFrame.setVisible(true);

        ReadWrite rw = new ReadWrite();
        createTable(rw.staffTableData());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI();
                staffFrame.dispose();
            }
        });
    }

    private void createTable(Object [] [] data){
//        String[] [] data = {
//                {"1","2","3","4","5","6","7","8","9"}
//        };
//        Object[] [] data = staffTableData();
        staffTable.setModel(new DefaultTableModel(
                data,
                new String[]{"ID","First Name", "Last Name","Address","Contact Number","E-mail","Gender","DOB","User Type","Status"}
        ));
    }



}
