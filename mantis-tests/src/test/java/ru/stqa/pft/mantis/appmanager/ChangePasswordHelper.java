package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;

import java.util.List;

public class ChangePasswordHelper extends HelperBase{

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

    public void clickOnUser(Accounts accounts) {
        for (AccountData account: accounts) {
            if (!account.getUsername().equals("administrator")) {
                click(By.xpath(String.format("//a[contains(text(),'%s')]", account.getUsername())));
                break;
            }
        }

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
}

