package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class NewContactCreation extends TestBase{

  @Test
  public void testNewContactCreation() throws Exception {
    gotoUserCreationPage();
    fillContactInfo(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru"));
    submitContactCreation();
    gotoHomePage();
    logout();
  }

}
