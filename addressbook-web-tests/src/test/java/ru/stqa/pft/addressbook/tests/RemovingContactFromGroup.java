package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;

public class RemovingContactFromGroup extends TestBase {

    private SessionFactory sessionFactory;

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
        Contacts beforeContacts = app.db().contacts();
        ContactData selectContact = beforeContacts.iterator().next(); //выбрали контакт
        Groups beforeGroups = app.db().groups();
        GroupData selectGroup = beforeGroups.iterator().next(); //выбрали группу

        app.contact().goToContactDetails(selectContact);
        app.group().clickOnGroup(selectGroup);
        app.contact().removeFromGroup(selectContact);

               }
               // String.format("footer %s", i)
        //Assert.assertNotEquals(new HashSet<Object>(afterDeletion), new HashSet<Object>(beforeDeletion));
        //assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

