package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletion extends TestBase{
    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
        app.getContactHelper().markCheckbox();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
    }

}
