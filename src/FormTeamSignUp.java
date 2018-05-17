import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTeamSignUp {
    private JFrame frame;
    private JButton buttonCheck;
    private JPanel panelTeamSignUp;
    private JPanel panelTeamAuthentication;
    private JPanel panelTeamInfoSignUp;
    private JTextField textFieldTeamAccount;
    private JPasswordField passwordFieldTeamPassword;
    private JTextField textFieldLeaderID;
    private JTextField textFieldRefereeName;
    private JTextField textFieldLeaderName;
    private JTextField textFieldLeaderTel;
    private JTextField textFieldDoctorName;
    private JTextField textFieldCoachName;
    private JTextField textFieldDoctorTel;
    private JTextField textFieldDoctorID;
    private JTextField textFieldCoachTel;
    private JTextField textFieldCoachID;
    private JTextField textFieldRefereeID;
    private JTextField textFieldRefereeTel;
    private JButton buttonSubmit;
    private JRadioButton radioButtonMale;
    private JRadioButton radioButtonFemale;
    private String teamAccount;
    private String teamPassword;

    public FormTeamSignUp() {
        panelTeamInfoSignUp.setVisible(false);
        frame = new JFrame("Team Sign Up");
        frame.setLocationByPlatform(true);
        frame.setContentPane(panelTeamSignUp);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamAccount = textFieldTeamAccount.getText();
                teamPassword = String.valueOf(passwordFieldTeamPassword.getPassword());
                if (SQLiteJDBC.authenticateTeam(teamAccount, teamPassword)){
                    JOptionPane.showMessageDialog(frame, "Succeeded to authenticate");
                    panelTeamAuthentication.setVisible(false);
                    panelTeamInfoSignUp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to authenticate");
                }
            }
        });
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String leaderName = textFieldLeaderName.getText();
                String leaderID = textFieldLeaderID.getText();
                String leaderTel = textFieldLeaderTel.getText();
                String doctorName = textFieldDoctorName.getText();
                String doctorID = textFieldDoctorID.getText();
                String doctorTel = textFieldDoctorTel.getText();
                String coachName = textFieldCoachName.getText();
                String coachID = textFieldCoachID.getText();
                String coachTel = textFieldCoachTel.getText();
                int coachSex;
                String refereeName = textFieldRefereeName.getText();
                String refereeID = textFieldRefereeID.getText();
                String refereeTel = textFieldRefereeTel.getText();
                if (radioButtonMale.isSelected()){
                    coachSex = 0;
                } else if (radioButtonFemale.isSelected()){
                    coachSex = 1;
                } else {
                    coachSex = -1;
                }
                if (leaderName.isEmpty() || leaderID.isEmpty() || leaderTel.isEmpty() || doctorName.isEmpty() || doctorID.isEmpty() || doctorTel.isEmpty() || coachName.isEmpty() || coachID.isEmpty() || coachTel.isEmpty() || coachSex != -1){
                    JOptionPane.showMessageDialog(frame, "Compulsive Information Is Not Complete");
                } else if (!(refereeName.isEmpty() && refereeID.isEmpty() && refereeTel.isEmpty()) && (refereeName.isEmpty() || refereeID.isEmpty() || refereeTel.isEmpty())){
                    JOptionPane.showMessageDialog(frame, "Optional Information Is Filled In But Not Complete");
                } else {
                    SQLiteJDBC.teamSignUpCompulsive(teamAccount, teamPassword, leaderName, leaderID, leaderTel, doctorName, doctorID, doctorTel, coachName, coachID, coachTel, coachSex);
                    if(!(refereeName.isEmpty() || refereeID.isEmpty() || refereeTel.isEmpty())){
                        SQLiteJDBC.teamSignUpOptional(teamAccount, teamPassword, refereeName, refereeID, refereeTel);
                    }
                    JOptionPane.showMessageDialog(frame, "Finished Team Sign Up");
                }
            }
        });
        radioButtonMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonFemale.setSelected(false);
            }
        });
        radioButtonFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonMale.setSelected(false);
            }
        });
    }

    public static void main(String args[]){
        SQLiteJDBC.openConnection();
        new FormTeamSignUp();
    }
}
