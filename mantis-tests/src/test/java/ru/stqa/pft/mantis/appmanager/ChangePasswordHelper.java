package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
       // type(By.name("username"), );
        //type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }
}

