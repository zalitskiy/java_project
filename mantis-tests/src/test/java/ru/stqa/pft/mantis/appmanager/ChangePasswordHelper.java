package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public final SessionFactory sessionFactory;



    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "%s@localhost");
        type(By.name("password"), "root");
        click(By.xpath("//input[@type='submit']"));
    }
    public void clickOnManageUsers() {
        click(By.xpath("//a[@href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public Accounts accounts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<AccountData> result = session.createQuery("from AccountData").list();
        session.getTransaction().commit();
        session.close();
        return new Accounts(result);
    }

}

