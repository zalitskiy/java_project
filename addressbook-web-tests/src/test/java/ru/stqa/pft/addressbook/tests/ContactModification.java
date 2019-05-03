package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Andrey", "Ibragimovich", "Kiev", "333999666", "his@mail.ru", null);
        app.getContactHelper().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
