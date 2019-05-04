package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Serhii").withLastName("Zalitskyi")
                .withCity("Kharkiv").withPhoneNumber("12345678")
                .withEmail("myemail@mail.ua").withGroup("[none]");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
