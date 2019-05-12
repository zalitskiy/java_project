package ru.stqa.pft.addressbook.w.practice.only;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class FormCollection extends TestBase {
    @Test
    public void testGroup() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getGroupList(); //Поиск элементов по селектору, выделение названия группы из
        //каждой группы, формирование коллеции из имен групп
        app.group().create(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        List<GroupData> after = app.group().getGroupList();//Поиск элементов по селектору, выделение названия группы из
        //каждой группы, формирование коллеции из имен групп

        Assert.assertEquals(after.size(), before.size() + 1); //Сравнение размера коллеций групп до добавления и после
    }
}
