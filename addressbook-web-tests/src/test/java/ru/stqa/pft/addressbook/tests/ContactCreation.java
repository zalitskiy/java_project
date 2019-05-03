package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoUserCreationPage();
        ContactData contact = new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.goTo().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
