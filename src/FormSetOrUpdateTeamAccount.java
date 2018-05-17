import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormSetOrUpdateTeamAccount {
    private JFrame frame;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldTeamAccount;
    private JTextField textFieldTeamPassword;
    private JTextField textFieldTeamName;
    private JPanel panelSetOrUpdateTeamAccount;

    public FormSetOrUpdateTeamAccount() {
        frame = new JFrame("Set Or Update Team Account");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelSetOrUpdateTeamAccount);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = textFieldTeamName.getText();
                String teamAccount = textFieldTeamAccount.getText();
                String teamPassword = textFieldTeamPassword.getText();
                SQLiteJDBC.adminSetTeamInfo(teamName, teamAccount, teamPassword);
                JOptionPane.showMessageDialog(frame, "Set or update team account: procedure finished");
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
        new FormSetOrUpdateTeamAccount();
    }
}
