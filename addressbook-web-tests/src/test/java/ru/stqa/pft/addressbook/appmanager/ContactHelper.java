package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

    public void clickOnEditButton(int index) {
        wd.findElements(By.xpath("//td[@class='center']//img[@title='Edit']")).get(index).click();
    }

    public void submitContactUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteContact() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void markCheckbox(int index) {
        if (index <= 0){
            wd.findElements(By.name("selected[]")).get(0).click();
        } else {
            wd.findElements(By.name("selected[]")).get(index).click();
        }
    }

    public void createContact(ContactData contactData, boolean creation) {
        gotoUserCreationPage();
        fillContactForm(contactData, creation);
        submitContactCreation();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> fn = wd.findElements(By.xpath("//tr[@name='entry']//td[3]"));
        List<WebElement> ln = wd.findElements(By.xpath("//tr[@name='entry']//td[2]"));

        for (int i = 0; i < fn.size(); i++) {
            String firstName = fn.get(i).getText();
            String lastName = ln.get(i).getText();
            ContactData contact = new ContactData(firstName, lastName, null, null, null, null);
            contacts.add(contact);
        }
        /*for (WebElement element : fn){
            String firstName = element.getText();
            for (WebElement element1 : ln) {
                String lastName = element1.getText();
                ContactData contact = new ContactData(firstName, lastName, null, null, null, null);
                contacts.add(contact);
            }
        }*/

        return  contacts;
    }
}
