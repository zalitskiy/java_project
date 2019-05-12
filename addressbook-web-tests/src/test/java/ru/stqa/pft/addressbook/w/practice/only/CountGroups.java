package ru.stqa.pft.addressbook.w.practice.only;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class CountGroups extends TestBase {
    @Test
    public void testGroup() throws Exception {
        app.goTo().groupPage();
        int before = app.group().getGroupCount(); //Подсчет количества групп на странице
        app.group().create(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        int after = app.group().getGroupCount(); //Подсчет количества групп на странице после создания новой группы
        Assert.assertEquals(after, before + 1); //Сравнение количества групп до добавления и после
    }
}
