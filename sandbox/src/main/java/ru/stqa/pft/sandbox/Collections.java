package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"java", "php", "python", "c#"};

        List<String> arr = Arrays.asList("java", "php", "python", "c#");

        for (String l : arr) {
            System.out.println("Я хочу выучить " + l);
        }

        List<String> list = new ArrayList<>();
        list.add("hop");
        list.add("hey");
        list.add("la-la-ley");
        list.add("hop");
        System.out.println(list.get(0));
        System.out.println(list.get(2));


        Assert.assertEquals(list.get(0), list.get(3));

    }
}
