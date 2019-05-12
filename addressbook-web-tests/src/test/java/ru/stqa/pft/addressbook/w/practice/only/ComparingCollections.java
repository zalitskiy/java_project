package ru.stqa.pft.addressbook.w.practice.only;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;
import java.util.List;

public class ComparingCollections extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
    }

    @Test
    public void testGroup3() throws Exception {
        //для проверки нужно сгенерировать toString в GroupData (только для поля name)
        //так же нужно сгенерировать equals() и hashCode()(только для поля name) т.к. Java не знает как сравнивать объекты типа GroupData
        app.goTo().groupPage();
        List<GroupData> before = app.group().getGroupList();
        app.group().selectGroup(before.size() - 1); //выбор и в последствии удаление последней группы (-1 потому что нумерация начинается с 0)
        app.group().deleteSelectedGroups();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        //Так как в процессе проверки группа удаляется, то для сравнения колекций (до и после) нужно удалить из коллекции before удаленную группу
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after); //сравнение двух коллекций (сравниваются только имена)
    }
}
