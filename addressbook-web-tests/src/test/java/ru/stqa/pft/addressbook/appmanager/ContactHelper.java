package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void clickOnEditButton() {
        click(By.xpath("//td[@class='center'][3]//img[@title='Edit']"));
    }

    public void submitContactUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteContact() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void markCheckbox() {
        click(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        gotoUserCreationPage();
        fillContactForm(contactData, creation);
        submitContactCreation();
        gotoHomePage();
    }

    private void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        get("http://localhost/addressbook/");
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
