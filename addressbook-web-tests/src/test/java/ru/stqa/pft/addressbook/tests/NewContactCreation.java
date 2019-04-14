package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    app.gotoUserCreationPage();
    app.fillContactInfo(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru"));
    app.submitContactCreation();
    app.gotoHomePage();
    app.logout();
  }

}
