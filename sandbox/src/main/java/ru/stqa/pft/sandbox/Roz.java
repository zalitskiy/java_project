package ru.stqa.pft.sandbox;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Roz extends Helper {
    @Test
    public void rozetka() {
        init();
        List<WebElement> list = listOfCategories();
        printCategories(list);

        Assert.assertEquals(17, list.size());

        stop();
    }

}

