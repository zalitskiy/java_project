package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    app.getContactHelper().gotoUserCreationPage();
    app.getContactHelper().fillContactInfo(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.gotoHomePage();
    app.logout();
  }

}
