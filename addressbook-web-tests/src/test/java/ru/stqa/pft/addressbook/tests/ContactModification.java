package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase{

    @Test(enabled = false)
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().clickOnEditButton(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Andrey", "Ibragimovich", "Kiev", "333999666", "his@mail.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
