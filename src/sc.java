import java.sql.*;
public class sc {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://frodo.bentley.edu:3306/test";
        String username = "harry";
        String password = "harry";
        ResultSet Preparedresult = null;
        try (Connection con = DriverManager.getConnection(URL, username, password)){
            PreparedStatement Stat = con.prepareStatement("DELETE id, city FROM myTable Where id ='101' && city='Chicago';");
            Stat.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

