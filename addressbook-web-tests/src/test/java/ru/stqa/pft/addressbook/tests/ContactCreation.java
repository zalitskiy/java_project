package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().get("http://localhost/addressbook/");
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoUserCreationPage();
        app.getContactHelper().fillContactForm(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
