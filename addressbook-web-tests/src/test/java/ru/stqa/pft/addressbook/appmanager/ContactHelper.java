package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        type(By.name("address"), contactData.getCity());
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void gotoUserCreationPage() {
        click(By.linkText("add new"));
    }

    public void clickOnEditButton(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteContact() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void markCheckboxById(int id) {
       wd.findElement(By.xpath("//tr[@name='entry']/td[@class='center']/input[@id='"+ id +"']")).click();
    }

    public void create(ContactData contactData, boolean creation) {
        gotoUserCreationPage();
        fillContactForm(contactData, creation);
        submitContactCreation();
        homePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    public void modify(ContactData contact) {
        clickOnEditButton(contact.getId());
        fillContactForm(contact, false);
        submitContactUpdate();
        homePage();
    }

    public void delete(ContactData contact) {
        markCheckboxById(contact.getId());
        deleteContact();
        acceptAlert();
        homePage();
        refreshPage();
    }

    public void create(ContactData contact) {
        gotoUserCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        homePage();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement cell : rows) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            //System.out.println(firstName);
            String lastName = cells.get(1).getText();
            //System.out.println(lastName);
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            //System.out.println(id);
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }
}
