package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.xpath("//span[@class='group']/input[@value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void returnToGroupPage() {
        get("http://localhost/addressbook/group.php");
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='groups']"));
        for (WebElement element : elements) {
            String name = element.getText();
            //System.out.println(name);
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //System.out.println(id);
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='groups']"));
        for(WebElement element : elements) {
            String name = element.getText();
            GroupData group = new GroupData().withName(name).withHeader(null).withFooter(null);
            groups.add(group);
        }
        return  groups;
    }

    public void clickOnGroup(GroupData group) {
        wd.findElement(By.xpath(String.format("//i/a[@href='./index.php?group=%s']", group.getId()))).click();
    }

    public void selectGroupFromList(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }
    public void clickOnAddTo() {
        wd.findElement(By.xpath("//input[@name='add']")).click();
    }

}
