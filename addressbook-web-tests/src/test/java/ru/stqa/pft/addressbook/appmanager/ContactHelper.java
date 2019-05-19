package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.List;

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
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
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
        wd.findElement(By.xpath("//tr[@name='entry']/td[@class='center']/input[@id='" + id + "']")).click();
    }

    public void create(ContactData contactData, boolean creation) {
        gotoUserCreationPage();
        fillContactForm(contactData, creation);
        submitContactCreation();
        contactCache = null;
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
        contactCache = null;
        homePage();
    }

    public void delete(ContactData contact) {
        markCheckboxById(contact.getId());
        deleteContact();
        contactCache = null;
        acceptAlert();
        homePage();
        refreshPage();
    }

    public void create(ContactData contact) {
        gotoUserCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        homePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement cell : rows) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            String allPhones = cells.get(5).getText();
            //String[] phones = cells.get(5).getText().split("\n");
            //System.out.println(phones);
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            String firstName = cells.get(2).getText();
            //System.out.println(firstName);
            String lastName = cells.get(1).getText();
            //System.out.println(lastName);
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            //System.out.println(id);
            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");
        String lastName = wd.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
        String address = wd.findElement(By.xpath("//textarea[@name='address']")).getAttribute("value");
        String homePhone = wd.findElement(By.xpath("//input[@name='home']")).getAttribute("value");
        String mobilePhone = wd.findElement(By.xpath("//input[@name='mobile']")).getAttribute("value");
        String workPhone = wd.findElement(By.xpath("//input[@name='work']")).getAttribute("value");
        String email = wd.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        String email2 = wd.findElement(By.xpath("//input[@name='email2']")).getAttribute("value");
        String email3 = wd.findElement(By.xpath("//input[@name='email3']")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName)
                .withLastName(lastName).withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.xpath(String.format("//input[@value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a"))).click();
        //wd.findElement(By.xpath("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
    }
}
