package org.example;

import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateText implements Callable {



    @Override
    public String call() throws Exception {
        return generateText("abc", 100_000);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
