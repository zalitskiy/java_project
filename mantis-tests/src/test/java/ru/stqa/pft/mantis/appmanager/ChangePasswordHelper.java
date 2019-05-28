package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;

import java.sql.*;
import java.util.List;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.xpath("//input[@type='submit']"));
    }

    public void clickOnManageUsers() {
        click(By.xpath("//a[@href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public void clickOnUser(AccountData account) {
        click(By.xpath(String.format("//a[contains(text(),'%s')]", account.getUsername())));
    }

    public void clickOnReset() {
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public String saveValue(String locator) {
        String value = wd.findElement(By.xpath(locator)).getAttribute("value");
        return value;
    }

    public Accounts all() {
        Accounts accounts = new Accounts();
        List<WebElement> rows1 = wd.findElements(By.xpath("//tr[@class='row-1']"));
        List<WebElement> rows2 = wd.findElements(By.xpath("//tr[@class='row-2']"));
        for (WebElement cell : rows1) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            String username = cells.get(0).getText();
            String email = cells.get(2).getText();
            accounts.add(new AccountData().withUserName(username).withEmail(email));
        }
        for (WebElement cell : rows2) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            String username = cells.get(0).getText();
            String email = cells.get(2).getText();
            accounts.add(new AccountData().withUserName(username).withEmail(email));
        }
        return accounts;
    }

    public Accounts getAccountsFromDB() {
        Connection conn = null;
        Accounts accounts = new Accounts();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                    "user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");

            while (rs.next()) {
                accounts.add(new AccountData()
                        .withId(rs.getInt("id")).withUserName(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return accounts;
    }
}

