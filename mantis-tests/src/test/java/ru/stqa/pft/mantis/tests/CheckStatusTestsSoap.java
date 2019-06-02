package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class CheckStatusTestsSoap extends TestBase {
    @Test
    public void testStatusNew() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000001);
        System.out.println("Hello, world!");
    }
}
