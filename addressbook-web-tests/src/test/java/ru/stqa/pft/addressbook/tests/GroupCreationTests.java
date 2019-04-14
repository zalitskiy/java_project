package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("testGroupName1", "testGroupName1", "testGroupName1"));
        app.submitGroupCreation();
        app.gotoGroupPage();
        app.logout();
    }

}
