package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;

import java.util.List;

public class DbHelper {

    public final SessionFactory sessionFactory;

    public DbHelper() {
// A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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
