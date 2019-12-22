import java.sql.*;

public class MainDB {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;
    private static String url = "jdbc:MySQL://localhost:3306/university?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
    private static String username = "root";
    private static String password = "0401at77";

    public static void main(String[] args) {
        try {
            connect();
            createTable("students","name","score");
            // ResultSet rs = stmt.executeQuery("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT(50), score INTEGER");
            selectStudent("students","Petrovich");
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    public static void connect () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url,username, password);
        stmt = connection.createStatement();
   }

    public static void disconnect (){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable (String nameTable, String firstNameColunms, String secondNameColunms ){
        try {
            pstm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS ? (id INTEGER PRIMARY KEY AUTO_INCREMENT, ? TEXT(50), ? INTEGER");
            pstm.setString(1, nameTable);
            pstm.setString(2, firstNameColunms);
            pstm.setString(3, secondNameColunms);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateStudent (String nameTable,String nameUser,int scoreUser ){
        try {
            pstm = connection.prepareStatement("UPDATE ? SET rs.getInt(2) = ? WHERE rs.getInt(1) = ?)");
            pstm.setString(1, nameTable);
            pstm.setInt(3,scoreUser);
            pstm.setString(2, nameUser);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }
    public static void deleteStudent (String nameTable, String nameUser){
        try {
            pstm = connection.prepareStatement("DELETE FROM ? WHERE rs.getInt(1) = ? ");
            pstm.setString(1, nameTable);
            pstm.setString(2, nameUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertStudent (String nameTable, String nameUser, int scoreUser)  {
        try {
            pstm = connection.prepareStatement("INSERT INTO ? (rs.getInt(1), rs.getInt(2)) VALUES (?,?)");
            pstm.setString(1, nameTable);
            pstm.setString(2, nameUser);
            pstm.setInt(3,scoreUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectStudent (String nameTable, String nameUser){
        try {
            pstm = connection.prepareStatement("SELECT * FROM ? WHERE rs.getInt(1) = ?");
            pstm.setString(1, nameTable);
            pstm.setString(2, nameUser);
           } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
