package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModification extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Serhii").withLastName("Zalitskyi").withCity("Kharkiv").withPhoneNumber("12345678")
                    .withEmail("myemail@mail.ua").withGroup("[none]"), true);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId())
                .withFirstName("Andrey").withLastName("Ibragimovich").withCity("Kyiv").withPhoneNumber("333999666")
                .withEmail("his@mail.ru").withGroup(null);
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
