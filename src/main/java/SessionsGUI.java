import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionsGUI extends JFrame{
    private JButton backButton;
    private JTable sessionsTable;
    private JComboBox cbTrainerId;
    private JPanel mainPanel;
    private JTextField filterText;
    private JButton filterButton;
    private JComboBox cbFilter;
    private JButton newButton;
    private JFrame sessionsFrame;
    JButton button = new JButton();

    Functions fn = new Functions();

    public SessionsGUI(String username){
        sessionsFrame = new JFrame("Sessions List");
        sessionsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        sessionsFrame.setPreferredSize(new Dimension(1200, 500));
//        staffFrame.setResizable(false);

        sessionsFrame.add(mainPanel);

        sessionsFrame.pack();
        sessionsFrame.setLocationRelativeTo(null);
        sessionsFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI indexGUI = new IndexGUI(username);
                sessionsFrame.dispose();
            }
        });
//        String[] cbItems = fn.getTrainerIDs();
//        cbTrainerId.addItem("Please Select");
//        for(int i = 0; i < cbItems.length; i ++){   cbTrainerId.addItem(cbItems[i]);    }

        Object[][] tableData = fn.sessionsTableData();
        createTable(tableData);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int column = 0;
                int row = sessionsTable.getSelectedRow();
                String uID = sessionsTable.getModel().getValueAt(row, column).toString();
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    fn.cancelSession(uID);
                    SessionsGUI sg = new SessionsGUI(username);
                    sessionsFrame.dispose();
                }
            }
        });


        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSessionGUI ns = new NewSessionGUI(username);
                sessionsFrame.dispose();
            }
        });
    }

    private void createTable(Object[][] data){
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Session ID","Member ID","Member Name","Trainer ID","Trainer Name","Session Date","Session Duration(Hours)","Session Time","Payment Status","Is Canceled",""});
        sessionsTable.setModel(model);
        TableColumnModel columns = sessionsTable.getColumnModel();
        sessionsTable.getColumn("").setCellRenderer(new ButtonRenderer());
        sessionsTable.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        sessionsTable.setRowSorter(sorter);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] header = new String[]{"Session ID","Member ID","Member Name","Trainer ID","Trainer Name","Session Date","Session Duration(Hours)","Session Time","Payment Status","Payment Status",""};
                int i = 0;
                while(i<header.length){
                    if (cbFilter.getSelectedItem().toString().equals(header[i])){ break;}
                    i++;
                }
                RowFilter<DefaultTableModel, Object> rf = null;
                try {
                    rf = RowFilter.regexFilter(filterText.getText(),i);
                } catch (java.util.regex.PatternSyntaxException ex) {
                    return;
                }
                sorter.setRowFilter(rf);
            }
        });
    }


    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Cancel" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor
    {
        private String label;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            label = (value == null) ? "Cancel" : value.toString();
            button.setText(label);
            return button;
        }
        public Object getCellEditorValue()
        {
            return new String(label);
        }
    }
}
