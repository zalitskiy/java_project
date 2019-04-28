package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru", "[none]"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().clickOnEditButton();
        app.getContactHelper().fillContactForm(new ContactData("Andrey", "Ibragimovich", "Kiev", "333999666", "his@mail.ru", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHomePage();
    }
}
