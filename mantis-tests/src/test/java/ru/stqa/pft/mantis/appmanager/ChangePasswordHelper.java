package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

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

    public void clickOnUser() {
        click(By.xpath("//td/a[@href='manage_user_edit_page.php?user_id=16']"));
    }

    public void clickOnReset() {
        click(By.xpath("//input[@value='Reset Password']"));
    }
    public String saveValue(String locator) {
        String value = wd.findElement(By.xpath(locator)).getAttribute("value");
        return value;
    }
}

