import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormAdminSetEventInfo {
    private JFrame frame;
    private JButton setUpdateEventInfoButton;
    private JButton buttonRefresh;
    private JTable tableEventInfo;
    private JPanel panelAdminSetEventInfo;
    private JScrollPane scrollPaneEventInfo;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;

    public FormAdminSetEventInfo() {
        frame = new JFrame("Admin Set Event Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelAdminSetEventInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        columnNames = new String[]{"Event Name", "Max People Number Per Team", "Max On Court People Number Per Game", "Team Score Threshold People Number"};
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnNames);
        try {
            ResultSet resultSet = SQLiteJDBC.adminQueryEventInfo();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                Object[] objects = new Object[columnCount];
                for (int i = 0; i < objects.length; i++) {
                    objects[i] = resultSet.getObject(i + 1);
                }
                defaultTableModel.addRow(objects);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tableEventInfo.setModel(defaultTableModel);

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.setRowCount(0);
                try {
                    ResultSet resultSet = SQLiteJDBC.adminQueryEventInfo();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()){
                        Object[] objects = new Object[columnCount];
                        for (int i = 0; i < objects.length; i++) {
                            objects[i] = resultSet.getObject(i + 1);
                        }
                        defaultTableModel.addRow(objects);
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        setUpdateEventInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteJDBC.openConnection();
                new FormSetOrUpdateEventInfo();
            }
        });
    }

    public static void main(String args[]){
        new FormAdminSetEventInfo();
    }
}
