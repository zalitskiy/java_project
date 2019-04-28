package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().clickOnEditButton(before.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("Andrey", "Ibragimovich", "Kiev", "333999666", "his@mail.ru", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
    }
}
