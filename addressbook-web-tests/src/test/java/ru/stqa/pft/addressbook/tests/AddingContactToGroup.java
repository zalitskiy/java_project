package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
        app.goTo().homePage();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Serhii").withLastName("Zalitskyi").withAddress("Kharkiv").withHomePhone("12345678")
                    .withEmail("myemail@mail.ua").withPhoto(new File("src/test/resources/stru.png"))
                    .inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void testAddContactToGroup() throws Exception {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        ContactData newContact = new ContactData().withFirstName("FirstName1").withLastName("LastName1")
                .withAddress("Odessa").withHomePhone("111111111")
                .withEmail("xxxx@ffff.com").withPhoto(new File("src/test/resources/stru.png")).inGroup(groups.iterator().next());
        Contacts before = app.db().contacts();
        app.contact().create(newContact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
    }
}
