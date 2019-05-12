package ru.stqa.pft.addressbook.w.practice.only;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class DeleteGroupWithIndex extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testGroupName1").withHeader("testGroupName1").withFooter("testGroupName1"));
        }
    }

    @Test
    public void testGroup1() throws Exception {

        app.goTo().groupPage();
        int before = app.group().getGroupCount();
        app.group().selectGroup(before - 1); //выбор и в последствии удаление последней группы (-1 потому что нумерация начинается с 0)
        app.group().deleteSelectedGroups();
        app.group().returnToGroupPage();
        int after = app.group().getGroupCount();

        Assert.assertEquals(after, before - 1);
    }
}
