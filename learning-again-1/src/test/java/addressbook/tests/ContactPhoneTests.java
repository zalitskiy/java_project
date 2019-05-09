package addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Serhii")
                    .withLastName("Zalitskyi")
                    .withAddress("ave. Lvivska 75b, Kharkiv, Ukraine")
                    .withHomePhone("+38(066)12345678").withMobilePhone("+38(096)123-456-78").withWorkPhone("+3 8 (098) 123 456 78")
                    .withEmail("myemail@mail.ua").withEmail2("my-email1@mail.ua").withEmail3("my.email@mail.ua")
                    .withGroup("[none]"), true);
        }
    }

    @Test
    public void testContactPhones() throws Exception {
        app.goTo().homePage();
        app.contact().refreshPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        //assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        //assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        //assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        System.out.println(contact.getAllPhones());System.out.println(mergePhones(contactInfoFromEditForm));
        System.out.println(contact.getAllEmails());System.out.println(mergeEmails(contactInfoFromEditForm));
        System.out.println(contact.getAddress());System.out.println(contactInfoFromEditForm.getAddress());
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
