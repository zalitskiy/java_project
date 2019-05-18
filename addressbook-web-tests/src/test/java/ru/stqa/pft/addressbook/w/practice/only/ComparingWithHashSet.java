package ru.stqa.pft.addressbook.w.practice.only;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.HashSet;
import java.util.List;

public class ComparingWithHashSet extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
    }

    @Test
    public void testGroup3() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getGroupList();
        app.group().selectGroup(before.size() - 1); //выбор и в последствии удаление последней группы (-1 потому что нумерация начинается с 0)
        app.group().initGroupModification(); // клик на кнопку Edit
        GroupData group = new GroupData().withId(before.get(before.size() - 1).getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().fillGroupForm(group); //заполняем поля данными объекта groups
        app.group().submitGroupModification(); //кликаем  на кнопку update
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        //Так как в процессе проверки группа удаляется, то для сравнения колекций (до и после) нужно удалить из коллекции before удаленную группу
        before.remove(before.size() - 1);
        before.add(group); //добавляем в коллекцию объект groups
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        //создаем новые множества из списков и сравниваем их (сравниваются имена и id)
    }
}
