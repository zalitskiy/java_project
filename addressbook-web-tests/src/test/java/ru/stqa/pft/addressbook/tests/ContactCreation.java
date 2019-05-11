package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new ContactData()
                .withFirstName("Serhii1").withLastName("Zalitskyi1")
                .withAddress("Kharkiv").withHomePhone("12345678")
                .withEmail("myemail@mail.ua").withGroup("[none]").withPhoto(new File("src/test/resources/stru.png"))});
        list.add(new Object[]{new ContactData()
                .withFirstName("Serhii2").withLastName("Zalitskyi2")
                .withAddress("Kharkiv").withHomePhone("12345678")
                .withEmail("myemail@mail.ua").withGroup("[none]").withPhoto(new File("src/test/resources/stru.png"))});
        list.add(new Object[]{new ContactData()
                .withFirstName("Serhii3").withLastName("Zalitskyi3")
                .withAddress("Kharkiv").withHomePhone("12345678")
                .withEmail("myemail@mail.ua").withGroup("[none]").withPhoto(new File("src/test/resources/stru.png"))});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contactFromDP = new ContactData()
                .withFirstName(contact.getFirstName()).withLastName(contact.getLastName())
                .withAddress(contact.getAddress()).withHomePhone(contact.getHomePhone())
                .withEmail(contact.getEmail()).withGroup(contact.getGroup()).withPhoto(contact.getPhoto());
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testDir() throws Exception {
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
