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

public class TestClass extends TestBase {
    /* 1) получить полный список групп, посчитать их количество
2) получить полный список контактов, отфильтровать по условию "количество групп, в которые входит этот контакт, меньше общего количества групп"
если этот отфильтрованный список будет непустой -- берём из него контакт. а если пустой -- надо что-то делать, все контакты добавлены во все группы. ну или контактов нет вообще. или групп нет вообще.*/

 /* Хорошо, мы отфильтровали список и предположим один контакт не входит во все группы, а например в 3 из 4 входит, а в одну не входит, как нам её узнать?
1) взять общий список групп
2) взять список групп, в которые входит контакт
2) отфильтровать первый список по условию "второй список не содержит эту группу"

или поискать подходящий метод для списка, который выкидывает все элементы, входящие в другой список. кажется это метод removeAll, но я могу ошибаться, наугад сказал, в документацию не смотрел*/

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
            int groupsCount = app.db().groups().size(); //количество групп
            Groups beforeGroups = app.db().groups(); //формируем список всех групп
            Contacts beforeContacts = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groupsCount).collect(Collectors.toCollection(Contacts::new)); //фильтруем список
            System.out.println(beforeContacts);
            ContactData contact = beforeContacts.iterator().next(); //выбираем контакт
            System.out.println(contact);

            Groups listOfGroups = contact.getGroups();
            beforeGroups.removeAll(listOfGroups);// выбираем группу, в которой не состоит контакт
            System.out.println(beforeGroups);
            Groups theGroup = beforeGroups;
            System.out.println(theGroup);
            //.filter((s) -> !s.equals("")).


           // Contacts beforeContacts = app.db().contacts();


           /* ContactData selectContact = beforeContacts.iterator().next(); //выбрали контакт
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
            assertThat(groupsOfContactAfter, equalTo(groupsOfContactBefore.withOut(selectGroup))); */
        }
    }


