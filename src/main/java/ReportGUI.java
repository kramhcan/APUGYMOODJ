import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportGUI extends JFrame{
    private JPanel panel1;
    private JTable reportTable;
    private JButton backButton;
    private JComboBox cbRange;
    private JTextField txtLabel;
    private JTextField txtTotal;
    private JLabel lblMonthYear;
    private JComboBox cbValue;
    private JFrame reportFrame;

    Functions fn = new Functions();

    public ReportGUI(String username){
        reportFrame = new JFrame("Report");
        reportFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        reportFrame.setPreferredSize(new Dimension(1200, 500));
//        reportFrame.setResizable(false);

        reportFrame.add(panel1);

        reportFrame.pack();
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndexGUI ig = new IndexGUI(username);
                reportFrame.dispose();
            }
        });

        cbRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillValueByRange();
                lblMonthYear.setText(cbRange.getSelectedItem().toString());
            }
        });

        Object[][] tableData = fn.reportTableData();
        createTable(tableData);

        int total = getTableSum();
        txtTotal.setText(String.valueOf(total));
    }

    public void fillValueByRange(){
        String[] values;
        DefaultComboBoxModel mod;
        String chosen = cbRange.getSelectedItem().toString();
        switch(chosen){
            case "Month":
                values = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
                mod = new DefaultComboBoxModel(values);
                cbValue.setModel(mod);
                lblMonthYear.setText("Month");
                break;
            case "Year":
                values = fn.getYears();
                mod = new DefaultComboBoxModel(values);
                cbValue.setModel(mod);
                lblMonthYear.setText("Year");
                break;
        }
    }
    public int getTableSum(){
        int total = 0;
        for(int i = 0; i < reportTable.getRowCount(); i++){
            int Amount = Integer.parseInt(reportTable.getValueAt(i, 4)+"");
            total = Amount+total;
            System.out.println(total);
        }
        return total;
    }
    private void createTable(Object[][] data){
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Payment ID","Payment Type","Payment Date","Corresponding ID","Amount"});
        reportTable.setModel(model);
        TableColumnModel columns = reportTable.getColumnModel();
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        reportTable.setRowSorter(sorter);

        cbValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String[] header = new String[]{"Session ID","Member ID","Member Name","Trainer ID","Trainer Name","Session Date","Session Duration(Hours)","Session Time","Payment Status","Payment Status",""};
//                int i = 0;
//                while(i<header.length){
//                    if (cbValue.getSelectedItem().toString().equals(header[i])){ break;}
//                    i++;
//                }
                RowFilter<DefaultTableModel, Object> rf = null;
                try {
                    rf = RowFilter.regexFilter(cbValue.getSelectedItem().toString(),2);
                } catch (java.util.regex.PatternSyntaxException ex) {
                    return;
                }
                sorter.setRowFilter(rf);

                txtLabel.setText(cbRange.getSelectedItem().toString());
                int total = getTableSum();
                txtTotal.setText(String.valueOf(total));
            }
        });
    }

}
