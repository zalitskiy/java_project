package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    @Test
    public void testMethod1() throws Exception {
        String[] langs = {"java", "php", "python", "c#"};
        for (int i = 0; i < langs.length; i++) {
            System.out.println(langs[i]);
        }
    }

    @Test
    public void testMethod2() throws Exception {
        List<String> arr = Arrays.asList("java", "php", "python", "c#");
        for (String l : arr) {
            System.out.println("Я хочу выучить " + l);
        }
    }
    @Test
    public void testMethod3() throws Exception {
    List<String> list = new ArrayList<>();
        list.add("hop");
        list.add("hey");
        list.add("la-la-ley");
        list.add("hop");
        System.out.println(list.get(0));
        System.out.println(list.get(2));

        Assert.assertEquals(list.get(0),list.get(2));
    }
}
