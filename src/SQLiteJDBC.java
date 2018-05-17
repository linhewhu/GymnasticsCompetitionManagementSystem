import java.sql.*;

public class SQLiteJDBC {
    //====================================
    private static String driver = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:GymnasticsCompetition.db";
    private static Connection conn;
    //====================================

    public static void openConnection(){
        try {
            Class.forName(driver);
             conn = DriverManager.getConnection(url);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void closeConnection(){
        try {
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Closed database successfully");
    }

    public static void adminSetTeamInfo(String teamName, String teamAccount, String teamPassword) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if TeamName exists");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(find){
            System.out.println("TeamName exists, trying to update table");
            try {
                executeSql = "UPDATE TeamEntry SET TeamAccount = ?, TeamPassword = ? WHERE TeamName = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, teamAccount);
                preparedStatement.setString(2, teamPassword);
                preparedStatement.setString(3, teamName);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("TeamName doesn't exist, trying to insert into table");
            try {
                executeSql = "INSERT INTO TeamEntry (TeamName, TeamAccount, TeamPassword) VALUES (?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, teamName);
                preparedStatement.setString(2, teamAccount);
                preparedStatement.setString(3, teamPassword);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Finished setting TeamInfo by admin");
    }

    public static void adminSetEventInfo(String eventName, String maxPeopleNumberPerTeam, String maxOnCourtPeopleNumberPerGame, String teamScoreThresholdPeopleNumber) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if EventName exists");
        try {
            executeSql = "SELECT * FROM EventInfo WHERE EventName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(find){
            System.out.println("EventName exists, trying to update table");
            try {
                executeSql = "UPDATE EventInfo SET MaxPeopleNumberPerTeam = ?, MaxOnCourtPeopleNumberPerGame = ?, TeamScoreThresholdPeopleNumber = ? WHERE EventName = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, maxPeopleNumberPerTeam);
                preparedStatement.setString(2, maxOnCourtPeopleNumberPerGame);
                preparedStatement.setString(3, teamScoreThresholdPeopleNumber);
                preparedStatement.setString(4, eventName);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("EventName doesn't exist, trying to insert into table");
            try {
                executeSql = "INSERT INTO EventInfo (EventName, MaxPeopleNumberPerTeam, MaxOnCourtPeopleNumberPerGame, TeamScoreThresholdPeopleNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, maxPeopleNumberPerTeam);
                preparedStatement.setString(3, maxOnCourtPeopleNumberPerGame);
                preparedStatement.setString(4, teamScoreThresholdPeopleNumber);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Finished setting EventInfo by admin");
    }

    public static boolean authenticateTeam(String teamAccount, String teamPassword){
        boolean ok = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Authenticating team account");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            ok = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(ok){
            System.out.println("Finished authenticating team");
        } else {
            System.out.println("Failed authenticating team");
        }
        return ok;
    }

    public static void teamSignUpCompulsive(
            String teamAccount,
            String teamPassword,
            String leaderName,
            String leaderID,
            String leaderTel,
            String doctorName,
            String doctorID,
            String doctorTel,
            String coachName,
            String coachID,
            String coachTel,
            int coachSex){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if TeamAccount and TeamPassword are right");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
            System.out.println("TeamAccount and TeamPassword are not right, cannot finish team compulsive signing up");
        } else {
            try {
                System.out.println("TeamAccount and TeamPassword are right, trying to update table");
                executeSql = "UPDATE TeamEntry SET LeaderName = ?, LeaderID = ?, LeaderTel = ?, DoctorName = ?, DoctorID = ?, DoctorTel = ?, CoachName = ?, CoachID = ?, CoachTel = ?, CoachSex = ? WHERE TeamAccount = ? AND TeamPassword = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, leaderName);
                preparedStatement.setString(2, leaderID);
                preparedStatement.setString(3, leaderTel);
                preparedStatement.setString(4, doctorName);
                preparedStatement.setString(5, doctorID);
                preparedStatement.setString(6, doctorTel);
                preparedStatement.setString(7, coachName);
                preparedStatement.setString(8, coachID);
                preparedStatement.setString(9, coachTel);
                preparedStatement.setInt(10, coachSex);
                preparedStatement.setString(11, teamAccount);
                preparedStatement.setString(12, teamPassword);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Finished setting compulsive information of team signing up");
        }
    }

    public static void teamSignUpOptional(
            String teamAccount,
            String teamPassword,
            String refereeName,
            String refereeID,
            String refereeTel){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if TeamAccount and TeamPassword are right");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
            System.out.println("TeamAccount and TeamPassword are not right, cannot finish team optional signing up");
        } else {
            try {
                System.out.println("TeamAccount and TeamPassword are right, trying to update table");
                executeSql = "UPDATE TeamEntry SET RefereeName = ?, RefereeID = ?, RefereeTel = ? WHERE TeamAccount = ? AND TeamPassword = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, refereeName);
                preparedStatement.setString(2, refereeID);
                preparedStatement.setString(3, refereeTel);
                preparedStatement.setString(4, teamAccount);
                preparedStatement.setString(5, teamPassword);
                preparedStatement.executeQuery();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Finished setting optional information of team signing up");
        }
    }

    // teamName must be registered before.
    public static void athleteSignUpCompulsive(
            String athleteName,
            String athleteID,
            int athleteAge,
            int athleteSex,
            String ageGroup,
            String teamName){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if TeamName exists");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
            System.out.println("TeamName doesn't exist, cannot finish athlete compulsive signing up");
        } else {
            try {
                System.out.println("TeamName exists, trying to update table");
                executeSql = "INSERT INTO AthleteEntry (AthleteName, AthleteID, AthleteAge, AthlegeSex, AgeGroup) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, athleteName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setInt(3, athleteAge);
                preparedStatement.setInt(4, athleteSex);
                preparedStatement.setString(5, ageGroup);
                preparedStatement.executeQuery();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Finished setting compulsive information of team signing up");
        }
    }

    public static ResultSet adminQueryTeamAccountInfo(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT TeamName, TeamAccount, TeamPassword FROM TeamEntry";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet adminQueryEventInfo(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT EventName, MaxPeopleNumberPerTeam, MaxOnCourtPeopleNumberPerGame, TeamScoreThresholdPeopleNumber FROM EventInfo";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}