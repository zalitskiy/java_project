package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("testGroupName1", "testGroupName1", "testGroupName1"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("testGroupName2", "testGroupName2", "testGroupName2"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        app.logout();
    }
}
