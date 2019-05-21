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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Serhii").withLastName("Zalitskyi").withAddress("Kharkiv").withHomePhone("12345678")
                    .withEmail("myemail@mail.ua").withPhoto(new File("src/test/resources/stru.png")), true);
        }
    }

    @Test
    public void testAddContactToGroup() throws Exception {
        app.goTo().homePage();
        Contacts beforeContacts = app.db().contacts();
        ContactData selectContact = beforeContacts.iterator().next(); //выбрали контакт
        Groups groupsOfContactBefore = selectContact.getGroups();// узнали список групп в которых состоит контакт до удаления из группы
        Groups beforeGroups = app.db().groups();
        GroupData selectGroup = beforeGroups.iterator().next(); //выбрали группу
        app.contact().markCheckboxById(selectContact.getId()); //отметили чекбокс контакта
        app.group().selectGroupFromList(selectGroup); // выбрали группу из списка
        app.group().clickOnAddTo(); // нажали кнопку add
        //app.contact().goToContactDetails(selectContact); // переход на страницу контакта для просмотра списка групп
        //app.group().clickOnGroup(selectGroup); //выбор группы
        //app.contact().removeFromGroup(selectContact); //нажатие на кнопку remove
        Groups groupsOfContactAfter = selectContact.getGroups();// узнали список групп в которых состоит контакт после удаления из группы
        System.out.println(groupsOfContactAfter);
        System.out.println(groupsOfContactBefore.withAdded(selectGroup));
        assertThat(groupsOfContactAfter, equalTo(groupsOfContactBefore.withOut(selectGroup)));
    }
}
