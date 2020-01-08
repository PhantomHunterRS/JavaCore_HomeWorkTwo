import java.sql.*;

public class MainDB {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;
    private static ResultSet rS;
    private static String url = "jdbc:MySQL://localhost:3306/university?serverTimezone=Europe/Moscow";
    private static String username = "root";
    private static String password = "0401at77";

    public static void main(String[] args) {
        try {
            connect();
            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
            System.out.println("CREATE OR NOT CREATE, THAT IS THE QUESTION - CREATE");
            createTable("users11598","name","score");

            System.out.println("SHOW A CHOOSE TABLE WITH SPECIFIC DATA - SELECT");
            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
            selectStudent("students", "Petrovich");

            System.out.println("ADD NEW VALUES IN A CHOOSE TABLE - INSERT");
//            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
//            System.out.println("IT WAS");
//            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
//            printTables(rS);
//            insertStudent("students","Ivanych",15);
//            insertStudent("students","Semenych",5);
//            System.out.println("BECOME");
//            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
//            printTables(rS);

            System.out.println("CHANGE THE VALUE IN THE CHOOSE TABLE - UPDATE");
            System.out.println("IT WAS");
            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
            printTables(rS);
            updateStudent("students","Vasilich",11,2);
            updateStudent("students","Ivanych",8,12);
            updateStudent("students","Semenych",8,7);
            System.out.println("BECOME");
            rS = stmt.executeQuery("SELECT id, name, scope FROM students");
            printTables(rS);

            System.out.println("CHANGE THE VALUE IN THE CHOOSE TABLE - UPDATE");
            deleteStudent("students","Petrovich");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
            String sql = "CREATE TABLE IF NOT EXISTS "+ nameTable +" (id INT PRIMARY KEY AUTO_INCREMENT, "+ firstNameColunms +" VARCHAR(45), "+ secondNameColunms +" INT NULL);";
            stmt.executeUpdate(sql);
           } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateStudent (String nameTable,String nameUser,int scoreUser, int id){
        try {
            String sql = "UPDATE university." + nameTable + " SET " + rS.getMetaData().getColumnName(2) + " = ? , "+ rS.getMetaData().getColumnName(3)  +" = ? WHERE ("+rS.getMetaData().getColumnName(1) +" = ?);";
            System.out.println(sql);
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nameUser);
            pstm.setInt(2,scoreUser);
            pstm.setInt(3,id);
            System.out.println(pstm.executeUpdate());
            } catch (SQLException e) {
            e.printStackTrace();
        };
    }
    public static void deleteStudent (String nameTable, String nameUser){
        try {
            pstm = connection.prepareStatement("DELETE FROM "+ nameTable +" WHERE "+ rS.getMetaData().getColumnName(2) +" = ? ;");
            pstm.setString(1, nameUser);
            System.out.println(pstm.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertStudent (String nameTable, String nameUser, int scoreUser)  {
        try {
            pstm = connection.prepareStatement("INSERT INTO "+ nameTable +" ("+ rS.getMetaData().getColumnName(2) +","+ rS.getMetaData().getColumnName(3)+") VALUES (?,?);");
            pstm.setString(1, nameUser);
            pstm.setInt(2,scoreUser);
            System.out.println(pstm.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectStudent (String nameTable, String nameUser){
        try {
            String sql = "SELECT id, name, scope FROM "+ nameTable +" WHERE "+ rS.getMetaData().getColumnName(2) + " = ? AND "+ rS.getMetaData().getColumnName(3) + ">= ? ;";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nameUser);
            pstm.setInt(2, 5);
            System.out.println(pstm.executeQuery());
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printTables(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1) + " "+ resultSet.getString(2) +" "+resultSet.getInt(3) );
        }
    }

}
