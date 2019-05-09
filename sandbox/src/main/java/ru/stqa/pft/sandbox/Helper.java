package ru.stqa.pft.sandbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
    WebDriver wd = new FirefoxDriver();

    public int amountOfList() {
        return wd.findElements(By.xpath("//div[@class='menu-wrapper display-block menu-wrapper_state_static']/ul/li")).size();
    }

    public void init() {
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("https://rozetka.com.ua/");
    }

    public void stop() {
        wd.quit();
    }

    public void clickOnCategory(int index) {
        wd.findElements(By.xpath("//div[@class='menu-wrapper display-block menu-wrapper_state_static']/ul/li/a")).get(index).click();
    }

    public List<WebElement> listOfCategories() {
        List<WebElement> list = wd.findElements(By.xpath("//div[@class='menu-wrapper display-block menu-wrapper_state_static']/ul/li"));
        for (WebElement element : list) {
            String s = element.findElement(By.tagName("a")).getText();
        }
        return list;
    }

    public void printCategories(List<WebElement> list) {
        for (WebElement element : list) {
            String s = element.findElement(By.tagName("a")).getText();
            System.out.println(s);
        }
    }
    /*public List<GroupData> list() {
        List<WebElement> groups = new ArrayList<GroupData>();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement cell : rows) {
            List<WebElement> cells = cell.findElements(By.tagName("td"));
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, lastName, firstName, address, allEmails, allPhones);
            groups.add(group);
        }
        return new groups;
    }*/


}
