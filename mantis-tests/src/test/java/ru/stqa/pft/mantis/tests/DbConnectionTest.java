package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;


import java.sql.*;

public class DbConnectionTest {
    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?"+
                    "user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
            Accounts accounts = new Accounts();
            while (rs.next()) {
                accounts.add(new AccountData()
                        .withId(rs.getInt("id")).withUserName(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(accounts);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
