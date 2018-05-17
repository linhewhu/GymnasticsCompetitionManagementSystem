import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMain {
    private JPanel panelMain;
    private JButton buttonAdminSetTeamInfo;
    private JButton buttonAdminSetEventInfo;
    private JButton buttonTeamSignUp;
    private JFrame frame;

    public FormMain() {
        frame = new JFrame("Gymnastics Competition Management System: Main Form");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonAdminSetTeamInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAdminSetTeamInfo();
            }
        });
        buttonAdminSetEventInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAdminSetEventInfo();
            }
        });
        buttonTeamSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormTeamSignUp();
            }
        });
    }

    public static void main(String[] args){
        SQLiteJDBC.openConnection();
        new FormMain();
    }
}
