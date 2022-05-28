import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class staffListGUI extends JFrame {

    private JTable staffTable;
    private JButton backButton;
    private JPanel mainPanel;
    private JButton refreshButton;
    private JFrame staffFrame;
    JButton button = new JButton();

    public staffListGUI(String username){
        staffFrame = new JFrame("Staff List");
        staffFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        staffFrame.setPreferredSize(new Dimension(1200, 500));
//        staffFrame.setResizable(false);

        staffFrame.add(mainPanel);

        staffFrame.pack();
        staffFrame.setLocationRelativeTo(null);
        staffFrame.setVisible(true);

        Functions fn = new Functions();

        Object[][] data = fn.staffTableData();
        System.out.println(data.toString());
        createTable(data);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI(username);
                staffFrame.dispose();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int column = 0;
                int row = staffTable.getSelectedRow();
                String uID = staffTable.getModel().getValueAt(row, column).toString();
                staffProfileEdit spEdit = new staffProfileEdit(uID, username);
                staffFrame.dispose();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterGUI rg = new RegisterGUI(username);
                staffFrame.dispose();
            }
        });
    }

    private void createTable(Object [] [] data){
        staffTable.setModel(new DefaultTableModel(
                data,
                new String[]{"ID","First Name","IC","Last Name","Address","Contact Number","E-mail","Gender","DOB","User Type","Status","Button"}
        ));
        TableColumnModel columns = staffTable.getColumnModel();
        columns.getColumn(4).setMinWidth(150);
        columns.getColumn(5).setMinWidth(100);
        staffTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
        staffTable.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
    }


    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Modify" : value.toString());
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
            label = (value == null) ? "Modify" : value.toString();
            button.setText(label);
            return button;
        }
        public Object getCellEditorValue()
        {
            return new String(label);
        }
    }


}
