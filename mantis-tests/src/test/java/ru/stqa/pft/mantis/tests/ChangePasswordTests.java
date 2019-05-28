package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.AccountData;
import ru.stqa.pft.mantis.model.Accounts;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer()  {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.changePassword().loginAsAdmin();
        app.changePassword().clickOnManageUsers();
        Accounts accountsFromDB = app.changePassword().getAccountsFromDB();// получаем список аккаунтов из БД
        AccountData administrator = accountsFromDB.findAdmin(accountsFromDB); //находим админа
        AccountData accountFromDB = accountsFromDB.withOut(administrator).iterator().next(); //выбираем произвольный аккаунт, но не админа
        app.changePassword().clickOnUser(accountFromDB); //кликаем на этот аккаунт
        String password = "password1";
        app.changePassword().clickOnReset(); //кликаем на кнопку reset
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

        String confirmationLink = findConfirmationLink(mailMessages, accountFromDB.getEmail());
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(accountFromDB.getUsername(), password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}