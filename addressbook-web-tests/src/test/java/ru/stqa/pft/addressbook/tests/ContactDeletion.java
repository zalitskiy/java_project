package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletion extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().markCheckbox(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.goTo().homePage();
        app.goTo().refreshPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }

}
