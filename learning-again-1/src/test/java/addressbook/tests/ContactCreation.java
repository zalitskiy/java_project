package addressbook.tests;

import org.testng.annotations.Test;
import addressbook.model.ContactData;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().gotoUserCreationPage();
        app.getContactHelper().fillContactInfo(new ContactData("Sergey", "Zalitskiy", "Kharkov", "12345678", "myemail@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        app.logout();
    }

}
