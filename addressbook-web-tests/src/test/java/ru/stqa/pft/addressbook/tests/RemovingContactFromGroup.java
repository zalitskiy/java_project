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
        Groups beforeGroups = app.db().groups(); //формируем список всех групп ДО
        Contacts beforeContacts = app.db().contacts();//формируем список всех контактов перед добавлением в группу
        Contacts filterContacts = app.db().contacts().stream()
                .filter((s) -> s.getGroups().size() > 0).collect(Collectors.toCollection(Contacts::new)); //выбираем контакты, которые добавлены хотя бы в одну группу
        ContactData selectContact = filterContacts.iterator().next(); //выбираем контакт
        Groups listOfGroups = selectContact.getGroups(); // определили список групп в которых состоит контакт
        GroupData theGroup = listOfGroups.iterator().next();//выбрали группу в для добавления контакта
        app.contact().goToContactDetails(selectContact);
        app.group().clickOnGroup(theGroup);
        app.contact().removeFromGroup(selectContact);
        Groups afterGroups = app.db().groups(); //формируем список всех групп после добавления контакта
        Contacts afterContacts = app.db().contacts(); //формируем список всех контактов после добавления в группу
        Contacts filterContactsAfter = app.db().contacts().stream()
                .filter((s) -> s.getId() == selectContact.getId()).collect(Collectors.toCollection(Contacts::new)); //выбираем контакт с заданным id
        ContactData selectContactAfter = filterContactsAfter.iterator().next(); //выбираем контакт
        Groups groupsAfter = selectContactAfter.getGroups();// определили список групп в которых состоит контакт

        assertThat(afterContacts, equalTo(beforeContacts));
        assertThat(afterGroups, equalTo(beforeGroups));
        assertThat(groupsAfter, equalTo(listOfGroups.withOut(theGroup)));
    }
}

