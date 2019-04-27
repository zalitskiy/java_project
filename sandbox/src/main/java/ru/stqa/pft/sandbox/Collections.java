package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"java", "php", "python", "c#"};

        List<String> arr = Arrays.asList("java", "php", "python", "c#");

        for (String l: arr) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
