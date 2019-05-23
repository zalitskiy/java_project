package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Serhii").withLastName("Zalitskyi").withAddress("Kharkiv").withHomePhone("12345678")
                    .withEmail("myemail@mail.ua").withPhoto(new File("src/test/resources/stru.png")), true);
        }
        int groupsCount = app.db().groups().size();
        Contacts beforeContacts = app.db().contacts().stream()
                .filter((s) -> s.getGroups().size() < groupsCount).collect(Collectors.toCollection(Contacts::new));
        if (beforeContacts.size() == 0) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("newTestGroupName2").withHeader("newTestGroupName2").withFooter("newTestGroupName2");
            app.group().create(newGroup);
        }
    }

    @Test
    public void testAddContactToGroup() throws Exception {
        app.goTo().homePage();
        int groupsCount = app.db().groups().size(); //количество групп
        Groups beforeGroups = app.db().groups(); //формируем список всех групп ДО
        Groups groups = new Groups(beforeGroups);
        Contacts beforeContacts = app.db().contacts();//формируем список всех контактов перед добавлением в группу
        Contacts filterContacts = app.db().contacts().stream()
                .filter((s) -> s.getGroups().size() < groupsCount).collect(Collectors.toCollection(Contacts::new)); //выбираем контакты, которые не добавлены хотябы в одну группу
        ContactData selectContact = filterContacts.iterator().next(); //выбираем контакт
        Groups listOfGroupsOfSelectedContact = selectContact.getGroups(); // определили список групп в которых состоит контакт
        groups.removeAll(listOfGroupsOfSelectedContact);// выбираем группу, в которой не состоит контакт
        GroupData theGroup = groups.iterator().next();//выбрали группу в для добавления контакта
        app.contact().markCheckboxById(selectContact.getId()); //отметили чекбокс контакта
        app.group().selectGroupFromList(theGroup); // выбрали группу из списка
        app.group().clickOnAddTo(); // нажали кнопку add
        Groups afterGroups = app.db().groups(); //формируем список всех групп после добавления контакта
        Contacts afterContacts = app.db().contacts(); //формируем список всех контактов после добавления в группу
        Groups groupsOfContactAfter = selectContact.getGroups();// узнали список групп в которых состоит контакт после добавления в группу

        assertThat(afterContacts, equalTo(beforeContacts));
        assertThat(afterGroups, equalTo(beforeGroups));
        assertThat(groupsOfContactAfter, equalTo(listOfGroupsOfSelectedContact.withOut(theGroup)));
    }
}

