import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormSetOrUpdateEventInfo {
    private JFrame frame;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldEventName;
    private JTextField textFieldMaxPeopleNumberPerTeam;
    private JTextField textFieldMaxOnCourtPeopleNumberPerGame;
    private JTextField textFieldTeamScoreThresholdPeopleNumber;
    private JPanel panelSetOrUpdateEventInfo;


    public FormSetOrUpdateEventInfo() {
        frame = new JFrame("Set Or Update Event Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelSetOrUpdateEventInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String maxPeopleNumberPerTeam = textFieldMaxPeopleNumberPerTeam.getText();
                String maxOnCourtPeopleNumberPerGame = textFieldMaxOnCourtPeopleNumberPerGame.getText();
                String teamScoreThresholdPeopleNumber = textFieldTeamScoreThresholdPeopleNumber.getText();
                SQLiteJDBC.adminSetEventInfo(eventName, maxPeopleNumberPerTeam, maxOnCourtPeopleNumberPerGame, teamScoreThresholdPeopleNumber);
                JOptionPane.showMessageDialog(frame,"Set or update event info: procedure finished");
                frame.dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormAdminSetEventInfo();
    }
}
