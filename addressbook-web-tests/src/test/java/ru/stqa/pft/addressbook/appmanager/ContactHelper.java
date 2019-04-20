package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactInfo(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("address"),contactData.getCity());
        type(By.name("home"),contactData.getPhoneNumber());
        type(By.name("email"),contactData.getEmail());
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
        click(By.xpath("/html[1]/body[1]/div[1]/div[4]/form[2]/table[1]/tbody[1]/tr[2]/td[1]/input[1]"));
    }

}
