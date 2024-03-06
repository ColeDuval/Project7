import java.sql.*;
import java.util.Objects;
// Name: Cole Duval
// Date: 11/25/2023
public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://frodo.bentley.edu:3306/world";
        String username = "harry";
        String password = "harry";
        ResultSet Preparedresult = null;
        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT name, population, countryCode FROM city");
            boolean check = false;
            // sets the name of the city that you are looking for
            String cityname = "Lobito";
            while (result.next()) {
                // if there is a city with the same name as city name
                if (Objects.equals(result.getString("name"), cityname)) {
                    check = true;
                    // gets the country code
                    String x = result.getString("countryCode");
                    // prints the city name, city code, and city population
                    System.out.print("City: "+ cityname+ "\nCity Code: " +result.getString("countryCode")+ "\nCity Population: "+ result.getInt("population")+"\n" );
                    // if the population is greater than 5 million
                    if(result.getInt("population") > 5000000){
                        // close the database link
                        con.close();
                        //exits the program
                        System.exit(0);
                    }
                    else {
                        // makes a prepared statement that looks for name and continent
                        PreparedStatement Stat = con.prepareStatement("SELECT name, continent FROM country WHERE Code =?;");
                        // sets the value of code to the city code
                        Stat.setString(1, x);
                        // runs the query and saves the result
                        Preparedresult = Stat.executeQuery();
                        // for each of the results it prints the country name and the continent name
                        while (Preparedresult.next()) {
                            System.out.println("Country Name: "+Preparedresult.getString("name")+"\nContinent name: "+Preparedresult.getString("continent"));
                        }
                        // closes the database link
                        con.close();
                        // exits the program
                        System.exit(0);
                    }
                }
            }
            // if there is no city with the name given print out that there is no city with that name then closes the program
            if (check == false){
                System.out.println("There is no city called "+ cityname+ " \nthe program is now closing");
                con.close();
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}