package ohtu;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:tietokanta.db");
        PreparedStatement statement = connection.prepareStatement("SELECT 1");
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            System.out.println("Yhteys toimii");
        } else {
            System.out.println("Ongelma...");
        }

    }
}
