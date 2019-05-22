package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;
import java.util.stream.Collectors;

public class RemovingContactFromGroup extends TestBase {

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
        Contacts beforeContacts = app.db().contacts().stream()
                .filter((s) -> s.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new)); //выбираем контакты, которые не добавлены хотябы в одну группу
        if (beforeContacts.size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstName("Serhii").withLastName("Zalitskyi").withAddress("Kharkiv").withHomePhone("12345678")
                    .withEmail("myemail@mail.ua").withPhoto(new File("src/test/resources/stru.png"))
                    .inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void testDeleteContactFromGroup() throws Exception {
        app.goTo().homePage();
        Contacts beforeContacts = app.db().contacts().stream()
                .filter((s) -> s.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new)); //выбираем контакты, которые добавлены хотя бы в одну группу
        ContactData selectContact = beforeContacts.iterator().next(); //выбираем контакт
        Groups listOfGroups = selectContact.getGroups(); // определили список групп в которых состоит контакт
        GroupData theGroup = listOfGroups.iterator().next();//выбрали группу в для добавления контакта
        app.contact().goToContactDetails(selectContact);
        app.group().clickOnGroup(theGroup);
        app.contact().removeFromGroup(selectContact);
        Groups groupsOfContactAfter = selectContact.getGroups();// узнали список групп в которых состоит контакт после удаления из группы
        assertThat(groupsOfContactAfter, equalTo(listOfGroups.withAdded(theGroup)));
    }
}

