import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class memberListGUI extends JFrame {
    private JButton backButton;
    private JButton refreshButton;
    private JTable memberTable;
    private JPanel mainPanel;
    private JFrame memberFrame;
    JButton button = new JButton();

    public memberListGUI(String username){
        memberFrame = new JFrame("Register");
        memberFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        memberFrame.setPreferredSize(new Dimension(1200, 500));
//        staffFrame.setResizable(false);

        memberFrame.add(mainPanel);

        memberFrame.pack();
        memberFrame.setLocationRelativeTo(null);
        memberFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI(username);
                memberFrame.dispose();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cRegisterGUI cRegisterGUI = new cRegisterGUI(username);
                memberFrame.dispose();
            }
        });
    }
    private void createTable(Object [] [] data){
        memberTable.setModel(new DefaultTableModel(
                data,
                new String[]{"ID","First Name","IC","Last Name","Address","Contact Number","E-mail","Gender","DOB","User Type","Status","Button"}
        ));
        TableColumnModel columns = memberTable.getColumnModel();
        columns.getColumn(4).setMinWidth(150);
        columns.getColumn(5).setMinWidth(100);
        memberTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
        memberTable.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
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
