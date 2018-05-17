import com.sun.deploy.panel.JreTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NonWritableChannelException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class FormAdminSetTeamInfo {
    private JFrame frame;
    private JTable tableTeamAccountInfo;
    private JButton buttonSetOrUpdateTeamAccount;
    private JPanel panelAdminSetTeamInfo;
    private JScrollPane strollPaneTeamAccountInfo;
    private JButton buttonRefresh;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;

    public FormAdminSetTeamInfo() {
        frame = new JFrame("Admin Set Team Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelAdminSetTeamInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        columnNames = new String[]{"Team Name", "Team Account", "Team Password"};
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnNames);
        try {
            ResultSet resultSet = SQLiteJDBC.adminQueryTeamAccountInfo();
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
        tableTeamAccountInfo.setModel(defaultTableModel);

        buttonSetOrUpdateTeamAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormSetOrUpdateTeamAccount();
            }
        });
        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.setRowCount(0);
                try {
                    ResultSet resultSet = SQLiteJDBC.adminQueryTeamAccountInfo();
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
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormAdminSetTeamInfo();
    }
}
